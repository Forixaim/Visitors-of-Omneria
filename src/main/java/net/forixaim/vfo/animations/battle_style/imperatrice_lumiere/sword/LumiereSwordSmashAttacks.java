package net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword;

import net.forixaim.efm_ex.api.animation.types.KnockbackAttackAnimation;
import net.forixaim.vfo.colliders.LumiereColliders;
import net.forixaim.vfo.registry.SoundRegistry;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Set;

public class LumiereSwordSmashAttacks
{
	public static StaticAnimation IMPERATRICE_SWORD_FIRE_DRIVER;
	public static StaticAnimation IMPERATRICE_SWORD_FLARE_ERUPTION;
	public static StaticAnimation IMPERATRICE_SWORD_SOLAR_FLARE;
	public static StaticAnimation IMPERATRICE_SWORD_FLAMING_ATMOSPHERE;

	public static void Build()
	{
		IMPERATRICE_SWORD_FIRE_DRIVER = new AttackAnimation(0.2f, 0.0f, 0.7f, 0.85f, 3.4f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/chargeattack", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_THRUST_L.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_L.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(6f))
				.addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(DamageTypeTags.BYPASSES_RESISTANCE))
				.addProperty(AnimationProperty.ActionAnimationProperty.RESET_PLAYER_COMBO_COUNTER, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 2f))
				.addState(EntityState.TURNING_LOCKED, true)
				.addState(EntityState.LOCKON_ROTATE, true);
		IMPERATRICE_SWORD_FLARE_ERUPTION = new KnockbackAttackAnimation(0.05f, 0.4f, 0.4f, 0.5f, 1.25f, LumiereColliders.IMPERATRICE_DOWN_SMASH, Armatures.BIPED.rootJoint, "battle_style/legendary/imperatrice_lumiere/sword/down_smash", Armatures.BIPED, 0f, 45f)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH_BIG.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundEvents.GENERIC_EXPLODE)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(5f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.4f, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.0F, -2.0F), Armatures.BIPED.legL, 3D, 0.55F));
		IMPERATRICE_SWORD_SOLAR_FLARE = new KnockbackAttackAnimation(0.1f, 0.0f, 0.05f, 0.15f, 2.75f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/solar_flare", Armatures.BIPED, 2f, 2f)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_FINISHER.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(4f))
				.addProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS, 10)
				.addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);
		IMPERATRICE_SWORD_FLAMING_ATMOSPHERE = new AttackAnimation(0.0f, "battle_style/legendary/imperatrice_lumiere/sword/neutral_heavy_aerial", Armatures.BIPED,
				new AttackAnimation.Phase(0.0f, 0.0f, 0.85f, 0.9f, 2f, 2f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_FLAMING_ATMOSPHERE))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_FINISHER.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(2f))
				.addProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS, 2)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_ON_LINK, false)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addState(EntityState.TURNING_LOCKED, true)
				.addState(EntityState.LOCKON_ROTATE, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a, b, c, d, e) -> 1.5f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true);
	}
}
