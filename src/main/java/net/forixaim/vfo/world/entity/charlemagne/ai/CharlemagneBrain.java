package net.forixaim.vfo.world.entity.charlemagne.ai;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.GroundAttacks;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordSmashAttacks;
import net.forixaim.vfo.events.advanced_bosses.DamageDealtEvent;
import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagneMode;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import net.forixaim.vfo.world.entity.charlemagne.ai.behaviors.HostileAttackBehavior;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.utils.AttackResult;
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
	private LivingEntity opponent;

	//Behaviors
	public HostileAttackBehavior hostileAttackBehavior;

	private final List<CharlemagneAttackString> charlemagneAttackStrings = Lists.newArrayList(
	);

	private final Map<AttackAnimation, Float> StaminaDamageMap = Maps.newHashMap();

	private int tick = 0;
	private int seconds = 0;

	//Defense flags
	private boolean backingOff = false;

	public CharlemagneBrain(final Charlemagne target, final CharlemagnePatch patch)
	{
		this.target = target;
		this.mode = CharlemagneMode.FRIENDLY;
		this.patch = patch;
		hostileAttackBehavior = new HostileAttackBehavior(patch, this, target);
		StaminaDamageMap.put((AttackAnimation) LumiereSwordSmashAttacks.IMPERATRICE_SWORD_FIRE_DRIVER, 7.1f);
	}

	public AttackResult handleWhenAttacked(DamageSource damageSource, float amount)
	{
		return AttackResult.of(patch.getEntityState().attackResult(damageSource), amount);
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
						s.fire(patch);
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

	public void circleAround(LivingEntity target, float distance)
	{

	}

	//TODO: Implement a backtrack
	public void backOff(LivingEntity nearestMonster, float distance)
	{
		backingOff = true;
		//move backwards until the distance has been reached.
		target.getLookControl().setLookAt(nearestMonster);
		Vec3f pos = new Vec3f(0, 0, distance);
		OpenMatrix4f posRotation = new OpenMatrix4f().rotate(-(float)Math.toRadians(target.getYRot()), new Vec3f(0.0F, 1.0F, 0.0F));
		OpenMatrix4f.transform3v(posRotation, pos, pos);
		Vec3 movePos = pos.toDoubleVector();
		target.getNavigation().moveTo(movePos.x(), movePos.y(), movePos.z(), 1);
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

	protected AABB getTargetSearchArea() {
		return this.target.getBoundingBox().inflate(80.0, 4.0, 80.0);
	}

	private void rotateTo(LivingEntity livingEntity)
	{
		double distanceX = livingEntity.getX() - target.getX();
		double distanceY = livingEntity.getY() - target.getY();
		double distanceHypo = target.distanceTo(livingEntity);
		double rAngle = 0;

		if (distanceX == 0)
		{
			double searchAngle = distanceY/distanceHypo;
			if (searchAngle <= Math.PI/2)
				rAngle = Math.asin(distanceY);
		}
		else
		{
			double searchAngle = distanceY/distanceX;
			rAngle = Math.atan(searchAngle);
		}



		target.setYRot((float) rAngle);
	}

	public void receiveTickFire()
	{
		tick++;
		if (mode.is(CharlemagneMode.DEFENSE))
			hostileAttackBehavior.onReceiveHostileAttack(nearestMonster);
		if (mode.is(CharlemagneMode.FRIENDLY))
			friendlyTick();
		if (mode.is(CharlemagneMode.DUELING))
			battleTick();
		if (tick >= 20)
		{
			tick = 0;
			seconds++;
			//Check for any enemy mobs nearby unless in defense mode;
			nearestMonster = this.target.level().getNearestEntity(this.target.level().getEntitiesOfClass(Mob.class, this.getTargetSearchArea()), target.DefCond, this.target, this.target.getX(), this.target.getY(), this.target.getZ());
			if (nearestMonster != null && !mode.is(CharlemagneMode.DUELING))
			{
				this.mode = CharlemagneMode.DEFENSE;
			}
			printDebugList();
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

	private void printDebugList()
	{
		if (!target.level().isClientSide)
		{
			LogUtils.getLogger().debug("a second has passed;");
			LogUtils.getLogger().debug("Nearest monster: {}", nearestMonster);
			LogUtils.getLogger().debug("Current Mode: {}", mode);
			LogUtils.getLogger().debug("Backing off: {}", backingOff);
			LogUtils.getLogger().debug("WalkSpeed: {}", target.walkAnimation.speed());
			LogUtils.getLogger().debug("Living Motion: {}", patch.currentLivingMotion);
		}
	}

	public void debugFire()
	{
		this.charlemagneAttackStrings.get(0).fire(patch);
		this.charlemagneAttackStrings.get(0).position++;
	}

	private void battleTick()
	{

	}

	private void friendlyTick()
	{

	}

	private class MovementPatterns
	{
		private final Charlemagne target = CharlemagneBrain.this.target;

	}
}
