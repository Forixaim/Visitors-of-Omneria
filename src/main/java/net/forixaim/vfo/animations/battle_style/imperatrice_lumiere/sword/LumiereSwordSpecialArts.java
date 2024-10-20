package net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword;

import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.vfo.animations.AdditionalEffects;
import net.forixaim.vfo.colliders.LumiereColliders;
import net.forixaim.vfo.registry.SoundRegistry;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Set;

public class LumiereSwordSpecialArts
{
	public static StaticAnimation IMPERATRICE_INCANDESCENT_FIREWORK;
	public static StaticAnimation IMPERATRICE_PROMINENCE_BLAZE;
	public static StaticAnimation IMPERATRICE_SWORD_INFERNO_ASTROLABE;

	public static StaticAnimation IMPERATRICE_SWORD_FLARE_BURST;
	public static void Build()
	{
		IMPERATRICE_PROMINENCE_BLAZE = new AttackAnimation(0.2f, 0.0f, 0.3f, 0.4f, 0.9f, null, Armatures.BIPED.handL, "battle_style/legendary/imperatrice_lumiere/sword/combat_arts/prominence_blaze", Armatures.BIPED);
		IMPERATRICE_INCANDESCENT_FIREWORK = new AttackAnimation(0.2f, 0.0f, 0.3f, 0.4f, 0.9f, null, Armatures.BIPED.handL, "battle_style/legendary/imperatrice_lumiere/sword/combat_arts/incandescent_firework", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(1))
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.7f, AdditionalEffects.SHOOT_FIREWISP, AnimationEvent.Side.SERVER));
		IMPERATRICE_SWORD_INFERNO_ASTROLABE = new AttackAnimation(0.05f, "battle_style/legendary/imperatrice_lumiere/sword/combat_arts/inferno_astrolabe", Armatures.BIPED,
				new AttackAnimation.Phase(0f, 1f, .75f, .9f, 1f, 1f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_INFERNO_ASTROLABE),
				new AttackAnimation.Phase(1f, 1.45f, 1.05f, 1.25f, 1.45f, 1.45f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_INFERNO_ASTROLABE),
				new AttackAnimation.Phase(1.45f, 2f, 1.6f, 1.75f, 2f, 2f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_INFERNO_ASTROLABE),
				new AttackAnimation.Phase(2f, 2.5f, 2.1f, 2.25f, 2.5f, 2.5f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_INFERNO_ASTROLABE),
				new AttackAnimation.Phase(2.5f, 2.6f, 2.5f, 2.6f, 4f, 4f, Armatures.BIPED.rootJoint, LumiereColliders.IMPERATRICE_INFERNO_ASTROLABE))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get())
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG, 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get(), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get(), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f), 1)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f), 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG, 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get(), 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get(), 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f), 2)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f), 3)
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG, 3)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get(), 3)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get(), 3)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(1f), 3)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.35f), 4)
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG, 4)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundRegistry.IMPERATRICE_SWING3.get(), 4)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundRegistry.IMPERATRICE_HIT_M.get(), 4)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(4f), 4)
				.addProperty(AnimationProperty.AttackAnimationProperty.FIXED_MOVE_DISTANCE, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.75f, 3f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (dynamicAnimation, livingEntityPatch, v, v1, v2) ->
				{
					float baseSpd = 2f;
					if (livingEntityPatch instanceof PlayerPatch<?> playerPatch)
					{
						if (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) && (playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get())))
						{
							return baseSpd * 1.1f;
						}

					}
					return baseSpd;
				})				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true)
				.addEvents(AnimationEvent.TimeStampedEvent.create(1.05f, Animations.ReusableSources.PLAY_SOUND, AnimationEvent.Side.CLIENT).params(SoundRegistry.IMPERATRICE_SPOT_DODGE.get())
						, AnimationEvent.TimeStampedEvent.create(1.5f, Animations.ReusableSources.PLAY_SOUND, AnimationEvent.Side.CLIENT).params(SoundRegistry.IMPERATRICE_SPOT_DODGE.get())
						, AnimationEvent.TimeStampedEvent.create(2.05f, Animations.ReusableSources.PLAY_SOUND, AnimationEvent.Side.CLIENT).params(SoundRegistry.IMPERATRICE_SPOT_DODGE.get())
						, AnimationEvent.TimeStampedEvent.create(2.55f, Animations.ReusableSources.PLAY_SOUND, AnimationEvent.Side.CLIENT).params(SoundRegistry.IMPERATRICE_SPOT_DODGE.get())
						, AnimationEvent.TimeStampedEvent.create(1f, AdditionalEffects.BLINK, AnimationEvent.Side.CLIENT)
						, AnimationEvent.TimeStampedEvent.create(1.45f, AdditionalEffects.BLINK, AnimationEvent.Side.CLIENT)
						, AnimationEvent.TimeStampedEvent.create(2f, AdditionalEffects.BLINK, AnimationEvent.Side.CLIENT)
						, AnimationEvent.TimeStampedEvent.create(2.5f, AdditionalEffects.BLINK, AnimationEvent.Side.CLIENT));


		IMPERATRICE_SWORD_FLARE_BURST = new AttackAnimation(0.2f, 1.1f, 1.1f, 1.2f, 2f, LumiereColliders.IMPERATRICE_FLARE_BURST, Armatures.BIPED.rootJoint, "battle_style/legendary/imperatrice_lumiere/sword/burst_art/flare_burst", Armatures.BIPED)
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.setter(19))
				.addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(DamageTypeTags.BYPASSES_ENCHANTMENTS, DamageTypeTags.BYPASSES_RESISTANCE, DamageTypeTags.BYPASSES_SHIELD, DamageTypeTags.BYPASSES_ARMOR, EpicFightDamageType.GUARD_PUNCTURE))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.KNOCKDOWN)
				.addProperty(AnimationProperty.AttackPhaseProperty.SWING_SOUND, SoundEvents.BLAZE_SHOOT)
				.addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, SoundEvents.GENERIC_EXPLODE)
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.setter(2f))
				.addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addProperty(AnimationProperty.StaticAnimationProperty.FIXED_HEAD_ROTATION, true)
				.addState(EntityState.TURNING_LOCKED, true)
				.addEvents(
						AnimationEvent.TimeStampedEvent.create(1.1F, (entitypatch, animation, params) ->
						{
							entitypatch.playSound(SoundRegistry.FLARE_BURST_AURA.get(), 0, 0);
							Entity entity = entitypatch.getOriginal();
							entity.level().explode(entity, entity.getX(), entity.getY(), entity.getZ(), 4f, Level.ExplosionInteraction.NONE);
							if (entitypatch instanceof PlayerPatch<?> playerPatch && playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.HEAT.get()))
							{
								if (!playerPatch.isLogicalClient())
								{
									playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.HEAT.get(), 1000f, (ServerPlayer) playerPatch.getOriginal());
									playerPatch.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.FLARE_BURST.get(), true, (ServerPlayer) playerPatch.getOriginal());
								}
							}
						}, AnimationEvent.Side.SERVER),
						AnimationEvent.TimeStampedEvent.create(1.05f, Animations.ReusableSources.FRACTURE_GROUND_SIMPLE, AnimationEvent.Side.CLIENT).params(new Vec3f(0.0F, -0.0F, -2.0F), Armatures.BIPED.legL, 4D, 3F));
	}
}
