package net.forixaim.vfo.animations.npc_interactions.charlemagne;

import net.forixaim.vfo.registry.ArmatureRegistry;
import net.forixaim.vfo.world.entity.charlemagne.model.CharlemagneArmature;
import yesman.epicfight.api.animation.types.StaticAnimation;

public class FacialAnimations
{
	public static StaticAnimation CHARLEMAGNE_SERIOUS;
	public static StaticAnimation CHARLEMAGNE_NEUTRAL;

	public static void Build()
	{
		CharlemagneArmature charlemagneArmature = ArmatureRegistry.CHARLEMAGNE;

		CHARLEMAGNE_SERIOUS = new StaticAnimation(true, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/facial_animations/serious", charlemagneArmature);
		CHARLEMAGNE_NEUTRAL = new StaticAnimation(true, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/facial_animations/neutral", charlemagneArmature);

	}
}
