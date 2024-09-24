package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere;

import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.skill.OmneriaSkills;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.guard.GuardSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.HurtEvent;

import java.util.UUID;

/**
 * This class represents Inferno Bulwark
 */
public class InfernoBulwark extends GuardSkill
{
	private static final UUID EVENT_UUID = UUID.fromString("c5547250-4aa6-44c4-a01e-cf4bd4f8e93b");

	public static Builder createActiveGuardBuilder()
	{
		return GuardSkill.createGuardBuilder()
				.addAdvancedGuardMotion(CapabilityItem.WeaponCategories.SWORD, (itemCap, playerpatch) -> itemCap.getStyle(playerpatch) == LumiereStyles.IMPERATRICE_SWORD ?
						new StaticAnimation[] { Animations.SWORD_GUARD_ACTIVE_HIT1, Animations.SWORD_GUARD_ACTIVE_HIT2 } :
						new StaticAnimation[] { Animations.SWORD_GUARD_ACTIVE_HIT2, Animations.SWORD_GUARD_ACTIVE_HIT3 })
				.addAdvancedGuardMotion(CapabilityItem.WeaponCategories.LONGSWORD, (itemCap, playerpatch) ->
						new StaticAnimation[] { Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2 })
				.addAdvancedGuardMotion(CapabilityItem.WeaponCategories.TACHI, (itemCap, playerpatch) ->
						new StaticAnimation[] { Animations.LONGSWORD_GUARD_ACTIVE_HIT1, Animations.LONGSWORD_GUARD_ACTIVE_HIT2 });
	}
	public InfernoBulwark(Builder builder)
	{
		super(builder);
	}

	public void onInitiate(SkillContainer container) {

	}

	public void guard(SkillContainer container, CapabilityItem itemCapability, HurtEvent.Pre event, float knockback, float impact, boolean advanced) {
	}

	@Override
	public void updateContainer(SkillContainer container)
	{
		if (!container.getExecuter().isLogicalClient())
		{
			ServerPlayerPatch serverPlayerPatch = (ServerPlayerPatch) container.getExecuter();


		}
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executor)
	{
		return executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE);
	}
}
