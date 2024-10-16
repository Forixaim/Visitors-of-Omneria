package net.forixaim.vfo.world.entity.charlemagne.ai;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mojang.logging.LogUtils;
import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.GroundAttacks;
import net.forixaim.vfo.events.advanced_bosses.DamageDealtEvent;
import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagneMode;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import yesman.epicfight.api.animation.types.AttackAnimation;
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
				LogUtils.getLogger().debug("a second has passed;");
			//Check for any enemy mobs nearby

		}
		if (seconds >= 60)
		{
			seconds = 0;
		}
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
}
