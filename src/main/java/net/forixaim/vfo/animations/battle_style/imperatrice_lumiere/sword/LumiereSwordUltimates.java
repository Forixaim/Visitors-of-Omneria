package net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword;

import net.forixaim.vfo.animations.AdditionalEffects;
import net.forixaim.vfo.colliders.LumiereColliders;
import net.forixaim.vfo.registry.ItemRegistry;
import net.forixaim.vfo.registry.SoundRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Set;

public class LumiereSwordUltimates
{
	public static StaticAnimation IMPERATRICE_ULTIMATE_TRY;
	public static StaticAnimation IMPERATRICE_FLARE_BLADE_CLEAVE;
	public static StaticAnimation IMPERATRICE_SWORD_FLARIAN_IGNITION;
	public static void Build()
	{
		IMPERATRICE_ULTIMATE_TRY = new AttackAnimation(0.05f, 0.0f, 0.85f, 1f, 5f, LumiereColliders.IMPERATRICE_ULTIMATE_TRY, Armatures.BIPED.rootJoint, "battle_style/legendary/imperatrice_lumiere/sword/ultimate_arts/try", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundEvents.GENERIC_EXPLODE)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(500f))
				.addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
				.addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.GUARD_PUNCTURE, DamageTypeTags.BYPASSES_INVULNERABILITY, EpicFightDamageType.WEAPON_INNATE))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.85f, (livingEntityPatch, staticAnimation, objects) -> {
							livingEntityPatch.playSound(SoundRegistry.IMPERATRICE_FLASH.get(), 1, 0,0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.85f, AdditionalEffects.EXPLODE_DIRECTION, AnimationEvent.Side.CLIENT).params(5f, 4f));

		IMPERATRICE_SWORD_FLARIAN_IGNITION =  new AttackAnimation(0.05f, 0.0f, 2.15f, 2.3f, 5f, LumiereColliders.IMPERATRICE_ULTIMATE_TRY, Armatures.BIPED.rootJoint, "battle_style/legendary/imperatrice_lumiere/sword/ultimate_arts/flarian_ignition", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(5f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_FINISHER.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(2f))
				.addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.setter(1))
				.addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.GUARD_PUNCTURE, DamageTypeTags.BYPASSES_INVULNERABILITY, EpicFightDamageType.WEAPON_INNATE))
				.addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.85f, (livingEntityPatch, staticAnimation, objects) -> {
							livingEntityPatch.playSound(SoundRegistry.IMPERATRICE_FLASH.get(), 1, 0,0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(2.15f, (a, b, c) -> {
							if (a instanceof PlayerPatch<?> playerPatch && !playerPatch.getOriginal().isCreative())
							{
								if (!playerPatch.getOriginal().getItemInHand(InteractionHand.MAIN_HAND).is(ItemRegistry.ORIGIN_JOYEUSE.get()))
								{
									a.playSound(SoundEvents.ITEM_BREAK, 0, 0);
									a.getOriginal().getItemInHand(InteractionHand.MAIN_HAND).copyAndClear();
								}
							}
						}, AnimationEvent.Side.SERVER));
		IMPERATRICE_FLARE_BLADE_CLEAVE = new AttackAnimation(0f, 0f, 2.85f, 3f, 10f, LumiereColliders.IMPERATRICE_FLARE_BLADE_CLEAVE, Armatures.BIPED.rootJoint, "battle_style/legendary/imperatrice_lumiere/sword/ultimate_arts/flare_blade_cleave", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(500))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.NEUTRALIZE)
				.addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.BYPASS_DODGE, EpicFightDamageType.GUARD_PUNCTURE))
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_HEAVY_SWING.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_FINISHER.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(500f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(3.5f, AdditionalEffects.EXPLODE_DIRECTION, AnimationEvent.Side.CLIENT).params(20f, 10f));
	}
}
