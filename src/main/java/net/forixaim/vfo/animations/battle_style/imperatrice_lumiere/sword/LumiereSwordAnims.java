package net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword;

import net.minecraft.sounds.SoundEvents;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;

/**
 * Module containing living motions.
 */
public class LumiereSwordAnims
{
	public static StaticAnimation IMPERATRICE_JOYEUSE_DRAW;
	public static StaticAnimation IMPERATRICE_SWORD_EN_GARDE;
	public static StaticAnimation IMPERATRICE_SWORD_CROUCH;
	public static StaticAnimation IMPERATRICE_SWORD_CROUCH_WALK;
	public static StaticAnimation IMPERATRICE_SWORD_WALK;
	public static StaticAnimation IMPERATRICE_SWORD_RUN;
	public static StaticAnimation IMPERATRICE_SWORD_JUMP;

	public static StaticAnimation IMPERATRICE_GUARD;

	//Trailblaze
	public static StaticAnimation IMPERATRICE_TRAILBLAZE_FWD;
	public static StaticAnimation IMPERATRICE_TRAILBLAZE_LEFT;
	public static StaticAnimation IMPERATRICE_TRAILBLAZE_RIGHT;
	public static StaticAnimation IMPERATRICE_TRAILBLAZE_BACK;
	public static StaticAnimation IMPERATRICE_TRAILBLAZE_SPOT;

	public static void Build()
	{
		//Build all of its module classes
		LumiereSwordAerialAttacks.Build();
		LumiereSwordGroundAttacks.Build();
		LumiereSwordSmashAttacks.Build();
		LumiereSwordSpecialArts.Build();
		LumiereSwordUltimates.Build();

		//Main
		IMPERATRICE_JOYEUSE_DRAW = new AttackAnimation(0.0f, 0.00f, 0.00f, 0.0f, 1.2f, null, Armatures.BIPED.toolR, "battle_style/legendary/imperatrice_lumiere/sword/joyeuse_draw", Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, Animations.ReusableSources.CONSTANT_ONE)
				.addEvents(AnimationEvent.TimeStampedEvent.create(0.85f, Animations.ReusableSources.PLAY_SOUND, AnimationEvent.Side.SERVER).params(SoundEvents.BLAZE_SHOOT));
		IMPERATRICE_SWORD_EN_GARDE = new StaticAnimation(true, "battle_style/legendary/imperatrice_lumiere/sword/idle", Armatures.BIPED)
				.addState(EntityState.CAN_BASIC_ATTACK, true);
		IMPERATRICE_SWORD_JUMP = new StaticAnimation(false, "battle_style/legendary/imperatrice_lumiere/sword/movement/neutral_jump", Armatures.BIPED);

		IMPERATRICE_GUARD = new StaticAnimation(true, "battle_style/legendary/imperatrice_lumiere/sword/guard", Armatures.BIPED)
				.addState(EntityState.MOVEMENT_LOCKED, true);

		IMPERATRICE_SWORD_CROUCH = new StaticAnimation(true, "battle_style/legendary/imperatrice_lumiere/sword/crouch", Armatures.BIPED)
				.addStateRemoveOld(EntityState.MOVEMENT_LOCKED, true);
		IMPERATRICE_SWORD_CROUCH_WALK = new MovementAnimation(true, "battle_style/legendary/imperatrice_lumiere/sword/crouch_walk", Armatures.BIPED);
		IMPERATRICE_SWORD_WALK = new MovementAnimation(true, "battle_style/legendary/imperatrice_lumiere/sword/walk", Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a,b,c,d,e) -> 1.5f);
		IMPERATRICE_SWORD_RUN = new MovementAnimation(true, "battle_style/legendary/imperatrice_lumiere/sword/run", Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, (a,b,c,d,e) -> 1.5f);

		IMPERATRICE_TRAILBLAZE_FWD = new DodgeAnimation(0.2F, 1.4F, "battle_style/legendary/imperatrice_lumiere/sword/skills/trailblaze_fwd", 0.6F, 1.65F, Armatures.BIPED)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2f, 1.0f))
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> {
					return 1.5f;
				}))
				.addState(EntityState.LOCKON_ROTATE, true)
				.newTimePair(0.0F, 1F)
				.addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, false)
				.addStateRemoveOld(EntityState.CAN_SKILL_EXECUTION, false);
		IMPERATRICE_TRAILBLAZE_SPOT = new DodgeAnimation(0.1F, 0.5F, "battle_style/legendary/imperatrice_lumiere/sword/skills/trailblaze_spot", 0.6F, 1.65F, Armatures.BIPED)
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> {
					return 1.5f;
				}))
				.addState(EntityState.LOCKON_ROTATE, true)
				.newTimePair(0.0F, 0.7F)
				.addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, false)
				.addStateRemoveOld(EntityState.CAN_SKILL_EXECUTION, false);
		IMPERATRICE_TRAILBLAZE_BACK = new DodgeAnimation(0.1F, 1.4F, "battle_style/legendary/imperatrice_lumiere/sword/skills/trailblaze_back", 0.6F, 1.65F, Armatures.BIPED)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2f, 1.0f))
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> {
					return 1.5f;
				}))
				.addState(EntityState.LOCKON_ROTATE, true)
				.newTimePair(0.0F, 1F)
				.addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, false)
				.addStateRemoveOld(EntityState.CAN_SKILL_EXECUTION, false);
		IMPERATRICE_TRAILBLAZE_LEFT = new DodgeAnimation(0.1F, 1.4F, "battle_style/legendary/imperatrice_lumiere/sword/skills/trailblaze_left", 0.6F, 1.65F, Armatures.BIPED)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2f, 1.0f))
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> {
					return 1.5f;
				}))
				.addState(EntityState.LOCKON_ROTATE, true)
				.newTimePair(0.0F, 1F)
				.addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, false)
				.addStateRemoveOld(EntityState.CAN_SKILL_EXECUTION, false);
		IMPERATRICE_TRAILBLAZE_RIGHT = new DodgeAnimation(0.1F, 1.4F, "battle_style/legendary/imperatrice_lumiere/sword/skills/trailblaze_right", 0.6F, 1.65F, Armatures.BIPED)
				.addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
				.addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.2f, 1.0f))
				.addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> {
					return 1.5f;
				}))
				.addState(EntityState.LOCKON_ROTATE, true)
				.newTimePair(0.0F, 1F)
				.addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, false)
				.addStateRemoveOld(EntityState.CAN_SKILL_EXECUTION, false);
	}
}
