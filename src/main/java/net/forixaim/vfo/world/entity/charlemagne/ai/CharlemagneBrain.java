package net.forixaim.vfo.world.entity.charlemagne.ai;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.GroundAttacks;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.ImperatriceLumiereAnims;
import net.forixaim.vfo.events.advanced_bosses.DamageDealtEvent;
import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagneMode;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.EntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;

import java.util.List;
import java.util.Map;


/**
 * The core class that defines most of Charlemagne's AI
 */
public class CharlemagneBrain
{
	private final Charlemagne target;
	public CharlemagnePatch patch;
	private CharlemagneMode mode;
	private LivingEntity nearestMonster;
	private boolean attacking;
	private LivingEntity opponent;
	private final List<CharlemagneAttackString> charlemagneAttackStrings = Lists.newArrayList(
	);

	private final Map<AttackAnimation, Float> StaminaDamageMap = Maps.newHashMap();

	private int tick = 0;
	private int seconds = 0;

	public CharlemagneBrain(final Charlemagne target, final CharlemagnePatch patch)
	{
		this.target = target;
		this.mode = CharlemagneMode.FRIENDLY;
		this.patch = patch;
		AttackStrings ASinstance = new AttackStrings();
		charlemagneAttackStrings.add(
				ASinstance.AttackString1
		);

	}

	public CharlemagneMode getMode()
	{
		return mode;
	}

	public void onReceiveAttackConnection(DamageDealtEvent event)
	{
		//Searches for all attack strings to find the one which fired.
		if (EpicFightCapabilities.getEntityPatch(event.getHurtEntity(), EntityPatch.class) instanceof LivingEntityPatch<?> livingEntityPatch)
		{
			if (livingEntityPatch instanceof PlayerPatch<?> playerPatch)
			{
				if (event.getDamageSource().getAnimation() instanceof AttackAnimation attackAnimation)
				{
					if (StaminaDamageMap.get(attackAnimation) != null)
					{
						playerPatch.consumeForSkill(null, Skill.Resource.STAMINA, StaminaDamageMap.get(attackAnimation));
					}
				}
			}
			//Check if the defender is stunned
			for (CharlemagneAttackString s : charlemagneAttackStrings)
			{
				if (s.firing)
				{
					//Fire
					if (s.position < s.Attacks.size())
					{
						s.connected = true;
						s.fire();
						s.position++;
					}
					else
					{
						s.reset();
					}
				}
			}
		}
	}

	public void backOff(LivingEntity nearestMonster)
	{
		//move backwards until the distance has been reached.

	}

	public void onAttackAnimationEnd()
	{
		for (CharlemagneAttackString charlemagneAttackString : charlemagneAttackStrings)
		{
			if (charlemagneAttackString.firing)
			{
				charlemagneAttackString.reset();
			}
		}
	}

	protected AABB getTargetSearchArea(double pTargetDistance) {
		return this.target.getBoundingBox().inflate(pTargetDistance, 4.0, pTargetDistance);
	}

	private Vec3 withinDist(LivingEntity opponent, float distance)
	{
		//Distance is the vector magnitude
		Vec3 oppPos = opponent.position();
		float theta;
	}

	public void receiveTickFire()
	{
		tick++;
		if (mode.is(CharlemagneMode.DEFENSE))
			defenseTick();
		if (mode.is(CharlemagneMode.FRIENDLY))
			friendlyTick();
		if (mode.is(CharlemagneMode.DUELING))
			battleTick();
		if (tick >= 20)
		{
			tick = 0;
			seconds++;
			if (!target.level().isClientSide)
			{
				LogUtils.getLogger().debug("a second has passed;");
				LogUtils.getLogger().debug("Nearest monster: {}", nearestMonster);
				LogUtils.getLogger().debug("Current Mode: {}", mode);
			}

			//Check for any enemy mobs nearby unless in defense mode;
			nearestMonster = this.target.level().getNearestEntity(this.target.level().getEntitiesOfClass(Mob.class, this.getTargetSearchArea(8.0f)), target.DefCond, this.target, this.target.getX(), this.target.getY(), this.target.getZ());
			if (nearestMonster != null && !mode.is(CharlemagneMode.DUELING))
			{
				this.mode = CharlemagneMode.DEFENSE;
			}

		}
		if (seconds >= 60)
		{
			seconds = 0;
		}
	}

	public boolean fireCheck()
	{
		for (CharlemagneAttackString string : charlemagneAttackStrings)
		{
			if (string.firing)
			{
				return false;
			}
		}
		return true;
	}

	public void debugFire()
	{
		this.charlemagneAttackStrings.get(0).fire();
		this.charlemagneAttackStrings.get(0).position++;
	}

	private void battleTick()
	{

	}

	private void friendlyTick()
	{

	}

	private void defenseTick()
	{
		if (nearestMonster != null && !nearestMonster.isDeadOrDying() )
		{
			this.target.lookAt(nearestMonster, 0, 0);
			nearestMonster.setSecondsOnFire(2);
			if (this.target.distanceTo(nearestMonster) < 1.5f)
			{
				this.target.lookAt(nearestMonster, 0, 0);
				patch.playAnimationSynchronized(ImperatriceLumiereAnims.IMPERATRICE_TRAILBLAZE_BACK, 0);
			}
			else if (this.target.distanceTo(nearestMonster) < 2.5f && fireCheck())
			{
				debugFire();
			}
			else if (fireCheck())
			{
				target.getNavigation().moveTo();
			}
		}
		else
		{
			this.mode = CharlemagneMode.FRIENDLY;
		}
	}

	private class AttackStrings
	{
		public final CharlemagneAttackString AttackString1 = new CharlemagneAttackString(
				patch,
				Lists.newArrayList(
						new CharlemagneAttack((AttackAnimation) GroundAttacks.JAB_1)
				),
				Lists.newArrayList(
						new CharlemagneAttack((AttackAnimation) GroundAttacks.JAB_2)
				),
				Lists.newArrayList(
						new CharlemagneAttack((AttackAnimation) GroundAttacks.JAB_3)
				)
		);
	}

	private class MovementPatterns
	{
		private final Charlemagne target = CharlemagneBrain.this.target;

	}
}
