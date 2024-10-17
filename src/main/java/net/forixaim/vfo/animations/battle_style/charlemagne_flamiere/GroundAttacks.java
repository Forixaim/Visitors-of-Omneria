package net.forixaim.vfo.animations.battle_style.charlemagne_flamiere;

import net.forixaim.vfo.registry.SoundRegistry;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.damagesource.StunType;

public class GroundAttacks
{
	public static StaticAnimation JAB_1;
	public static StaticAnimation JAB_2;
	public static StaticAnimation JAB_3;

	public static void build()
	{
		JAB_1 = new AttackAnimation(0.1f, 0.0f, 0.5f, 0.6f, 1f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/jab1", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.25f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(5))
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addState(EntityState.TURNING_LOCKED, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(
						0.5f, (livingEntityPatch, staticAnimation, objects) -> {
							livingEntityPatch.playSound(SoundRegistry.IMPERATRICE_SWING1.get(), 0, 0);
						}, AnimationEvent.Side.CLIENT
				));

		JAB_2 = new AttackAnimation(0.1f, 0.0f, 0.5f, 0.6f, 1f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/jab2", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(5))
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addState(EntityState.TURNING_LOCKED, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(
						0.5f, (livingEntityPatch, staticAnimation, objects) -> {
							livingEntityPatch.playSound(SoundRegistry.IMPERATRICE_SWING2.get(), 0, 0);
						}, AnimationEvent.Side.CLIENT
				));

		JAB_3 = new AttackAnimation(0.3f, 0.0f, 0.45f, 0.8f, 2f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/forixaim_sword_living/jab3", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.NO_SOUND.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(5))
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get())
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addState(EntityState.TURNING_LOCKED, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(
						0.45f, (livingEntityPatch, staticAnimation, objects) -> {
							livingEntityPatch.playSound(SoundRegistry.IMPERATRICE_SWING3.get(), 0, 0);
						}, AnimationEvent.Side.CLIENT
				));

	}
}
