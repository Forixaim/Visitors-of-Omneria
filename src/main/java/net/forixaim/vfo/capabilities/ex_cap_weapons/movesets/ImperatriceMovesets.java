package net.forixaim.vfo.capabilities.ex_cap_weapons.movesets;

import net.forixaim.efm_ex.api.moveset.MoveSet;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAerialAttacks;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAnims;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordGroundAttacks;
import net.forixaim.vfo.skill.OmneriaSkills;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.skill.guard.GuardSkill;

public class ImperatriceMovesets
{
    public static MoveSet IMPERATRICE_SWORD_PRIMARY = MoveSet.builder()
            .addAutoAttacks(
                    LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB1,
                    LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB2,
                    LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB3,
                    LumiereSwordGroundAttacks.IMPERATRICE_SWORD_DASH_ATTACK,
                    LumiereSwordAerialAttacks.IMPERATRICE_SWORD_FORWARD_AERIAL
            )
            .addInnateSkill(itemStack -> OmneriaSkills.FIRE_DRIVER)
            .setPassiveSkill(OmneriaSkills.IMPERATRICE_WP)
            .addLivingMotionModifier(LivingMotions.IDLE, LumiereSwordAnims.IMPERATRICE_SWORD_EN_GARDE)
            .addLivingMotionModifier(LivingMotions.WALK, LumiereSwordAnims.IMPERATRICE_SWORD_WALK)
            .addLivingMotionModifier(LivingMotions.RUN, LumiereSwordAnims.IMPERATRICE_SWORD_RUN)
            .addLivingMotionModifier(LivingMotions.KNEEL, LumiereSwordAnims.IMPERATRICE_SWORD_CROUCH)
            .addLivingMotionModifier(LivingMotions.SNEAK, LumiereSwordAnims.IMPERATRICE_SWORD_CROUCH_WALK)
            .addLivingMotionModifier(LivingMotions.JUMP, LumiereSwordAnims.IMPERATRICE_SWORD_JUMP)
            .addLivingMotionModifier(LivingMotions.BLOCK, LumiereSwordAnims.IMPERATRICE_GUARD)
            .addGuardAnimations(EpicFightSkills.GUARD, GuardSkill.BlockType.GUARD, LumiereSwordAnims.IMPERATRICE_GUARD_HIT_1)
            .addGuardAnimations(EpicFightSkills.GUARD, GuardSkill.BlockType.GUARD_BREAK, LumiereSwordAnims.IMPERATRICE_GUARD_BROKEN)
            .addGuardAnimations(EpicFightSkills.PARRYING, GuardSkill.BlockType.GUARD, LumiereSwordAnims.IMPERATRICE_GUARD_HIT_1)
            .addGuardAnimations(EpicFightSkills.PARRYING, GuardSkill.BlockType.GUARD_BREAK, LumiereSwordAnims.IMPERATRICE_GUARD_BROKEN)
            .addGuardAnimations(EpicFightSkills.PARRYING, GuardSkill.BlockType.ADVANCED_GUARD, LumiereSwordAnims.IMPERATRICE_GUARD_PARRY_1, LumiereSwordAnims.IMPERATRICE_GUARD_PARRY_2)
            .build();
}
