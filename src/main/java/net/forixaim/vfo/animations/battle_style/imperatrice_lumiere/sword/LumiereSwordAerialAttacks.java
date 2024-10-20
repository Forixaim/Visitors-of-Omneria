package net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword;

import net.forixaim.bs_api.AnimationHelpers;
import net.forixaim.efm_ex.api.animation.types.KnockbackAttackAnimation;
import net.forixaim.vfo.colliders.LumiereColliders;
import net.forixaim.vfo.registry.SoundRegistry;
import net.minecraft.world.InteractionHand;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.damagesource.StunType;

public class LumiereSwordAerialAttacks
{
	public static StaticAnimation IMPERATRICE_SWORD_FORWARD_AERIAL;
	public static StaticAnimation IMPERATRICE_SWORD_NEUTRAL_AERIAL;
	public static StaticAnimation IMPERATRICE_SWORD_DOWN_AERIAL;
	public static StaticAnimation IMPERATRICE_BACK_AERIAL;
	public static StaticAnimation IMPERATRICE_UP_AERIAL;

	public static void Build()
	{
		IMPERATRICE_SWORD_NEUTRAL_AERIAL = new KnockbackAttackAnimation(0.0f, "battle_style/legendary/imperatrice_lumiere/sword/neutral_aerial", Armatures.BIPED, 2f, 2f,
				new KnockbackAttackAnimation.KnockbackPhase(0.0f, 0.0f, 0.1f, 0.2f, 0.2f, 0.2f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_NEUTRAL_AERIAL, 2f, 2f, false),
				new KnockbackAttackAnimation.KnockbackPhase(0.2f, 0.0f, 0.35f, 0.4f, 0.4f, 0.4f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_NEUTRAL_AERIAL, 2f, 2f, false),
				new AttackAnimation.Phase(0.4f, 0.0f, 0.5f, 0.55f, 1.2f, 1.2f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_NEUTRAL_AERIAL))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD, 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING2.get(), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get(), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(2f), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.45f), 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG, 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get(), 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get(), 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(6f), 2)
				.addProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS, 2)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_ON_LINK, false)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addStateRemoveOld(EntityState.MOVEMENT_LOCKED, false)
				.addState(EntityState.TURNING_LOCKED, true)
				.addState(EntityState.LOCKON_ROTATE, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a, b, c, d, e) -> 1.25f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addEvents(AnimationProperty.StaticAnimationProperty.EVENTS, AnimationEvent.create((livingEntityPatch, staticAnimation, objects) ->
				{
					if (!AnimationHelpers.isInAir(livingEntityPatch))
					{
						livingEntityPatch.playAnimationSynchronized(livingEntityPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getLivingMotionModifier(livingEntityPatch, InteractionHand.MAIN_HAND).get(LivingMotions.IDLE).get(), 0);
					}
				}, AnimationEvent.Side.SERVER));

		IMPERATRICE_BACK_AERIAL = new AttackAnimation(0.05f, 0.25f, 0.6f, 0.8f, 1.25f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/back_air", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3f))
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
				.addState(EntityState.TURNING_LOCKED, true)
				.addState(EntityState.LOCKON_ROTATE, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a, b, c, d, e) -> 1.2f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addEvents(AnimationProperty.StaticAnimationProperty.EVENTS, AnimationEvent.create((livingEntityPatch, staticAnimation, objects) ->
				{
					if (!AnimationHelpers.isInAir(livingEntityPatch))
					{
						livingEntityPatch.playAnimationSynchronized(livingEntityPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getLivingMotionModifier(livingEntityPatch, InteractionHand.MAIN_HAND).get(LivingMotions.IDLE).get(), 0);
					}
				}, AnimationEvent.Side.SERVER));

		IMPERATRICE_UP_AERIAL = new KnockbackAttackAnimation(0.05f, "battle_style/legendary/imperatrice_lumiere/sword/up_air", Armatures.BIPED, 2f, 2f,
				new KnockbackAttackAnimation.KnockbackPhase(0.0f, 0.0f, 0.1f, 0.3f, 0.4f, 0.4f, Armatures.BIPED.toolR, null, 2, 2, false))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3f))
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
				.addState(EntityState.TURNING_LOCKED, true)
				.addState(EntityState.LOCKON_ROTATE, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a, b, c, d, e) -> 1.2f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addEvents(AnimationProperty.StaticAnimationProperty.EVENTS, AnimationEvent.create((livingEntityPatch, staticAnimation, objects) ->
				{
					if (!AnimationHelpers.isInAir(livingEntityPatch))
					{
						livingEntityPatch.playAnimationSynchronized(livingEntityPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getLivingMotionModifier(livingEntityPatch, InteractionHand.MAIN_HAND).get(LivingMotions.IDLE).get(), 0);
					}
				}, AnimationEvent.Side.SERVER));

		IMPERATRICE_SWORD_FORWARD_AERIAL = new KnockbackAttackAnimation(0.2f, "battle_style/legendary/imperatrice_lumiere/sword/forward_aerial", Armatures.BIPED, 2f, 2f,
				new KnockbackAttackAnimation.KnockbackPhase(0.0f, 0.0f, 0.15f, 0.3f, 1.5f, 1.5f, Armatures.BIPED.toolR, null, 2, 2, true))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.FALL)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(10f))
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
				.addState(EntityState.TURNING_LOCKED, true)
				.addState(EntityState.LOCKON_ROTATE, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a, b, c, d, e) -> 1.5f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addEvents(AnimationProperty.StaticAnimationProperty.EVENTS, AnimationEvent.create((livingEntityPatch, staticAnimation, objects) ->
				{
					if (!AnimationHelpers.isInAir(livingEntityPatch))
					{
						livingEntityPatch.playAnimationSynchronized(livingEntityPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getLivingMotionModifier(livingEntityPatch, InteractionHand.MAIN_HAND).get(LivingMotions.IDLE).get(), 0);
					}
				}, AnimationEvent.Side.SERVER));
		IMPERATRICE_SWORD_DOWN_AERIAL = new KnockbackAttackAnimation(0.05f, "battle_style/legendary/imperatrice_lumiere/sword/down_air", Armatures.BIPED, 2, 2,
				new KnockbackAttackAnimation.KnockbackPhase(0, 0, 0.2f, 0.5f, 1.2f, 1.2f, Armatures.BIPED.toolR, null, 2, 2, false),
				new KnockbackAttackAnimation.KnockbackPhase(0.1f, 0.1f, 0.3f, 0.4f, 1.2f, 1.2f, Armatures.BIPED.toolR, LumiereColliders.IMPERATRICE_SWORD_DOWN_AIR_SPIKE, 2f, 2f, true))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.25f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.FALL, 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.SILENCE.get(), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get(), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(3f), 1)
				.addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addEvents(AnimationProperty.StaticAnimationProperty.EVENTS, AnimationEvent.create((livingEntityPatch, staticAnimation, objects) ->
				{
					if (!AnimationHelpers.isInAir(livingEntityPatch))
					{
						livingEntityPatch.playAnimationSynchronized(livingEntityPatch.getHoldingItemCapability(InteractionHand.MAIN_HAND).getLivingMotionModifier(livingEntityPatch, InteractionHand.MAIN_HAND).get(LivingMotions.IDLE).get(), 0);
					}
				}, AnimationEvent.Side.SERVER));;
	}
}
