package net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword;

import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.efm_ex.api.animation.types.KnockbackAttackAnimation;
import net.forixaim.vfo.colliders.LumiereColliders;
import net.forixaim.vfo.registry.SoundRegistry;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.minecraft.world.entity.Entity;
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
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.StunType;

public class LumiereSwordGroundAttacks
{
	/* Jab */
	public static StaticAnimation IMPERATRICE_SWORD_JAB1;
	public static StaticAnimation IMPERATRICE_SWORD_JAB2;
	public static StaticAnimation IMPERATRICE_SWORD_JAB3;

	/* Tilts */
	public static StaticAnimation IMPERATRICE_SWORD_FTILT;
	public static StaticAnimation IMPERATRICE_SWORD_CERCLE_DE_FLAMME;

	public static StaticAnimation IMPERATRICE_SWORD_RTILT;

	public static StaticAnimation IMPERATRICE_SWORD_BTILT;

	public static StaticAnimation IMPERATRICE_SWORD_LTILT;

	public static StaticAnimation IMPERATRICE_SWORD_CROUCH_LIGHT;

	/* Dash Attack */
	public static StaticAnimation IMPERATRICE_SWORD_DASH_ATTACK;
	public static StaticAnimation BLAZE_WHEEL;

	public static void Build()
	{
		HumanoidArmature biped = Armatures.BIPED;
		BLAZE_WHEEL = new BasicAttackAnimation(0.1f, "battle_style/legendary/imperatrice_lumiere/sword/infernal_wheel", biped,
				new AttackAnimation.Phase(0.0f, 0.0f, 0.0f, 0.2f, 0.4f, 0.4f, biped.rootJoint, null),
				new AttackAnimation.Phase(0.4f, 0.0f, 0.5f, 0.8f, 1.3f, 1.6f, biped.toolR, null)
		)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(0.5f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a,b,c,d, e) -> 1.4f)
				.addEvents(
						AnimationEvent.TimeStampedEvent.create(0.1F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.2F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.3F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.4F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.5F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.6F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.7F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.8F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(0.9F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(1.0F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(1.1F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT),
						AnimationEvent.TimeStampedEvent.create(1.2F, (entitypatch, animation, params) ->
						{
							Entity entity = entitypatch.getOriginal();
							entity.level().addParticle(EpicFightParticles.ENTITY_AFTER_IMAGE.get(), entity.getX(), entity.getY(), entity.getZ(), Double.longBitsToDouble(entity.getId()), 0, 0);
						}, AnimationEvent.Side.CLIENT)
				);
		IMPERATRICE_SWORD_JAB1 = new BasicAttackAnimation(0.05f, 0.25f, 0.4f, 0.5f, 1f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/jab1", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.25f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.1f))
				.addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) ->
				{
					float baseSpd = 2.2f;
					if (livingEntityPatch instanceof PlayerPatch<?> playerPatch)
					{
						if (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) && (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get())))
						{
							return baseSpd * 1.1f;
						}

					}
					return baseSpd;
				})
				.addState(EntityState.TURNING_LOCKED, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true);
		IMPERATRICE_SWORD_JAB2 = new BasicAttackAnimation(0.05f, 0.25f, 0.8f, 0.9f, 1f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/jab2", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.25f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING2.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(0.1f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) ->
				{
					float baseSpd = 2.2f;
					if (livingEntityPatch instanceof PlayerPatch<?> playerPatch)
					{
						if (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) && (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get())))
						{
							return baseSpd * 1.1f;
						}

					}
					return baseSpd;
				})				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);
		IMPERATRICE_SWORD_JAB3 = new BasicAttackAnimation(0.05f, 0.05f, 0.5f, 0.75f, 2f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/jab3", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(4f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) ->
				{
					float baseSpd = 2.2f;
					if (livingEntityPatch instanceof PlayerPatch<?> playerPatch)
					{
						if (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) && (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get())))
						{
							return baseSpd * 1.1f;
						}

					}
					return baseSpd;
				})				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);
		IMPERATRICE_SWORD_CROUCH_LIGHT = new AttackAnimation(0.02f, 0.0f, 0.7f, 0.8f, 1.75f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/crouch_light", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) ->
				{
					float baseSpd = 4f;
					if (livingEntityPatch instanceof PlayerPatch<?> playerPatch)
					{
						if (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) && (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get())))
						{
							return baseSpd * 1.1f;
						}

					}
					return baseSpd;
				})				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);

		IMPERATRICE_SWORD_FTILT = new BasicAttackAnimation(0.2f, 0.0f, 0.4f, 0.6f, 1f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/forward_tilt", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.3f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f))
				.addProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS, 10)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 2f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);
		IMPERATRICE_SWORD_CERCLE_DE_FLAMME = new KnockbackAttackAnimation(0.1f, 0.0f, 0.4f, 0.6f, 2.75f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/forward_tilt_2", Armatures.BIPED, 2f, 2f)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.6f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING2.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_L.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(4f))
				.addProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS, 10)
				.addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 2f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);

		IMPERATRICE_SWORD_RTILT = new AttackAnimation(0.02f, "battle_style/legendary/imperatrice_lumiere/sword/right_tilt", Armatures.BIPED,
				new AttackAnimation.Phase(0f, 0f, 0.15f, 0.35f, 3.75f, 3.75f, Armatures.BIPED.legR, LumiereColliders.RTILT_LEG),
				new AttackAnimation.Phase(0f, 0f, 0.15f, 0.35f, 3.75f, 3.75f, Armatures.BIPED.thighR, LumiereColliders.RTILT_THIGH))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(4))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, EpicFightSounds.WHOOSH.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_KICK_IMPACT_M.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLUNT)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);

		IMPERATRICE_SWORD_LTILT = new AttackAnimation(0.2f, "battle_style/legendary/imperatrice_lumiere/sword/left_tilt", Armatures.BIPED,
				new AttackAnimation.Phase(0f, 0f, 0.6f, .8f, 1.6f, 1.8f, Armatures.BIPED.toolR, null))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.4f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.PARTICLE, EpicFightParticles.HIT_BLADE)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(2f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.5f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);

		IMPERATRICE_SWORD_BTILT = new AttackAnimation(0.1f, 0.0f, 0.1f, 0.2f, 0.75f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/back_tilt", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.SHORT)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING1.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_S.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true);

		IMPERATRICE_SWORD_DASH_ATTACK = new AttackAnimation(0.2f, "battle_style/legendary/imperatrice_lumiere/sword/dash_attack", Armatures.BIPED,
				new AttackAnimation.Phase(0.0f, 0.0f, 0.2f, 0.4f, 0.8f, 0.8f, Armatures.BIPED.rootJoint, LumiereColliders.DASH_LIGHT))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.7f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(2f))
				.addProperty(AnimationProperty.AttackAnimationProperty.EXTRA_COLLIDERS, 8)
				.addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, false)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_ON_LINK, false)
				.addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addState(EntityState.TURNING_LOCKED, true)
				.addState(EntityState.LOCKON_ROTATE, true)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a, b, c, d, e) -> 1.5f)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true);

	}
}
