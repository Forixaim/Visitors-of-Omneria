package net.forixaim.vfo.world.entity.charlemagne.ai;

import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.GroundAttacks;
import yesman.epicfight.world.capabilities.entitypatch.HumanoidMobPatch;
import yesman.epicfight.world.entity.ai.goal.CombatBehaviors;

public class CharlemagneBehaviors
{
	public static final CombatBehaviors.Builder<HumanoidMobPatch<?>> JOYEUSE_ATTACKS = CombatBehaviors.<HumanoidMobPatch<?>>builder()
			.newBehaviorSeries(
					CombatBehaviors.BehaviorSeries.<HumanoidMobPatch<?>>builder().weight(100f).canBeInterrupted(true).looping(true)
							.nextBehavior(CombatBehaviors.Behavior.<HumanoidMobPatch<?>>builder().animationBehavior(GroundAttacks.JAB_1).withinEyeHeight().withinDistance(0.0D, 2.5D))
							.nextBehavior(CombatBehaviors.Behavior.<HumanoidMobPatch<?>>builder().animationBehavior(GroundAttacks.JAB_2).withinEyeHeight().withinDistance(0.0D, 2.5D))
			);
}
