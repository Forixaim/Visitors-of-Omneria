package net.forixaim.vfo.capabilities.weapon_attacks;

import com.mojang.datafixers.util.Pair;
import net.forixaim.efm_ex.capabilities.weaponcaps.EXWeaponCapability;
import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.CharlemagneFlamiereAnims;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAerialAttacks;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordGroundAttacks;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAnims;
import net.forixaim.vfo.skill.OmneriaSkills;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.function.Function;

public class ImperatriceWeaponAttacks
{
	public static Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> imperatriceSword = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.IDLE, LumiereSwordAnims.IMPERATRICE_SWORD_EN_GARDE);
		builder.livingMotionModifier(style, LivingMotions.WALK, LumiereSwordAnims.IMPERATRICE_SWORD_WALK);
		builder.livingMotionModifier(style, LivingMotions.RUN, LumiereSwordAnims.IMPERATRICE_SWORD_RUN);
		builder.livingMotionModifier(style, LivingMotions.KNEEL, LumiereSwordAnims.IMPERATRICE_SWORD_CROUCH);
		builder.livingMotionModifier(style, LivingMotions.SNEAK, LumiereSwordAnims.IMPERATRICE_SWORD_CROUCH_WALK);
		builder.livingMotionModifier(style, LivingMotions.JUMP, LumiereSwordAnims.IMPERATRICE_SWORD_JUMP);
		builder.livingMotionModifier(style, LivingMotions.BLOCK, LumiereSwordAnims.IMPERATRICE_GUARD);
		builder.newStyleCombo(style,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB1,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB2,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB3,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_DASH_ATTACK,
				LumiereSwordAerialAttacks.IMPERATRICE_SWORD_FORWARD_AERIAL
		);
		builder.innateSkill(style, (itemstack) -> OmneriaSkills.FIRE_DRIVER);
		return builder;
	};

	public static Function<Pair<Style, EXWeaponCapability.Builder>, EXWeaponCapability.Builder> imperatriceForixaimSword = (main) ->
	{
		EXWeaponCapability.Builder builder = main.getSecond();
		Style style = main.getFirst();
		builder.livingMotionModifier(style, LivingMotions.IDLE, CharlemagneFlamiereAnims.TRUE_IMPERATRICE_IDLE);
		builder.livingMotionModifier(style, LivingMotions.WALK, CharlemagneFlamiereAnims.TRUE_IMPERATRICE_WALK);
		builder.livingMotionModifier(style, LivingMotions.RUN, CharlemagneFlamiereAnims.TRUE_IMPERATRICE_RUN);
		builder.livingMotionModifier(style, LivingMotions.KNEEL, LumiereSwordAnims.IMPERATRICE_SWORD_CROUCH);
		builder.livingMotionModifier(style, LivingMotions.SNEAK, LumiereSwordAnims.IMPERATRICE_SWORD_CROUCH_WALK);
		builder.livingMotionModifier(style, LivingMotions.JUMP, LumiereSwordAnims.IMPERATRICE_SWORD_JUMP);
		builder.livingMotionModifier(style, LivingMotions.BLOCK, LumiereSwordAnims.IMPERATRICE_GUARD);
		builder.newStyleCombo(style,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB1,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB2,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB3,
				LumiereSwordGroundAttacks.IMPERATRICE_SWORD_DASH_ATTACK,
				LumiereSwordAerialAttacks.IMPERATRICE_SWORD_FORWARD_AERIAL
		);
		builder.innateSkill(style, (itemstack) -> OmneriaSkills.FIRE_DRIVER);
		return builder;
	};
}
