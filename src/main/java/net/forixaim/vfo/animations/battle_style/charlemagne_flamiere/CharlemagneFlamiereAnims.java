package net.forixaim.vfo.animations.battle_style.charlemagne_flamiere;

import net.minecraft.sounds.SoundEvents;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.MovementAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.main.EpicFightMod;

public class CharlemagneFlamiereAnims
{
	public static StaticAnimation TRUE_JOYEUSE_DRAW;
	public static StaticAnimation TRUE_IMPERATRICE_IDLE;
	public static StaticAnimation TRUE_IMPERATRICE_WALK;
	public static StaticAnimation TRUE_IMPERATRICE_RUN;

	public static void Build()
	{
		GroundAttacks.build();
		TRUE_JOYEUSE_DRAW = new AttackAnimation(0.0f, 0.00f, 0.00f, 0.0f, 1.2f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/sword_draw", Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.85f, Animations.ReusableSources.PLAY_SOUND, AnimationEvent.Side.SERVER).params(SoundEvents.BLAZE_SHOOT));
		TRUE_IMPERATRICE_IDLE = new StaticAnimation(true, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/idle", Armatures.BIPED)
				.addState(EntityState.CAN_BASIC_ATTACK, true)
				.addState(EntityState.TURNING_LOCKED, true);
		TRUE_IMPERATRICE_WALK = new MovementAnimation(true, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/walk", Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a,b,c,d,e) -> 1.5f);
		TRUE_IMPERATRICE_RUN = new MovementAnimation(true, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/run", Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a,b,c,d,e) -> 2f);

	}
}
