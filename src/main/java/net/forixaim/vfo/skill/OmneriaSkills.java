package net.forixaim.vfo.skill;

import net.forixaim.bs_api.BattleArtsAPI;
import net.forixaim.bs_api.battle_arts_skills.active.burst_arts.BurstArt;
import net.forixaim.bs_api.battle_arts_skills.active.combat_arts.CombatArt;
import net.forixaim.bs_api.battle_arts_skills.active.ultimate_arts.UltimateArt;
import net.forixaim.bs_api.battle_arts_skills.battle_style.BattleStyle;
import net.forixaim.efm_ex.skill.ExCapWeaponPassive;
import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAnims;
import net.forixaim.vfo.registry.CreativeTabRegistry;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.*;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active.BlazingDetermination;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active.ImperatriceSpecials;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active.ImperatriceUltimateArt;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.dodge.DodgeSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.StunType;

import java.util.Set;

@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class OmneriaSkills
{
	public static Skill IMPERATRICE_LUMIERE;
	public static Skill FLARE_BLITZ;
	public static Skill FIRE_DRIVER;
	public static Skill IMPERATRICE_AERIALS;
	public static Skill IMPERATRICE_SPECIALS;
	public static Skill FLARE_BURST;
	public static Skill IMPERATRICE_ULTIMATE;
	public static Skill IMPERATRICE_WP;
	public static Skill TRAILBLAZE;


	@SubscribeEvent
	public static void BuildSkillEvent(SkillBuildEvent OnBuild)
	{
		SkillBuildEvent.ModRegistryWorker registryWorker = OnBuild.createRegistryWorker(VisitorsOfOmneria.MOD_ID);
			IMPERATRICE_LUMIERE = registryWorker.build("imperatrice_lumiere", ImperatriceLumiere::new, BattleStyle.CreateBattleStyle().setCreativeTab(CreativeTabRegistry.VISITORS_OF_OMNERIA.get()));
			FLARE_BLITZ = registryWorker.build("imperatrice_attack", FlareBlitz::new, FlareBlitz.createImperatriceAttackSet().setCreativeTab(CreativeTabRegistry.VISITORS_OF_OMNERIA.get()));
			IMPERATRICE_AERIALS = registryWorker.build("imperatrice_aerials", BurningSky::new, BurningSky.createImperatriceAerialBuilder().setCreativeTab(CreativeTabRegistry.VISITORS_OF_OMNERIA.get()));
			IMPERATRICE_WP = registryWorker.build("imperatrice_weapon_passive", ImperatriceWeaponPassive::new, ExCapWeaponPassive.createBuilder());
			FLARE_BURST = registryWorker.build("flare_burst", BlazingDetermination::new, BurstArt.createBurstArt().setResource(Skill.Resource.COOLDOWN).setCreativeTab(CreativeTabRegistry.VISITORS_OF_OMNERIA.get()));
			IMPERATRICE_ULTIMATE = registryWorker.build("imperatrice_ultimate", ImperatriceUltimateArt::new, UltimateArt.createUltimateArt().setResource(Skill.Resource.COOLDOWN).setCreativeTab(CreativeTabRegistry.VISITORS_OF_OMNERIA.get()));
			IMPERATRICE_SPECIALS = registryWorker.build("imperatrice_specials", ImperatriceSpecials::new, CombatArt.createCombatArt().setCreativeTab(CreativeTabRegistry.VISITORS_OF_OMNERIA.get()));
			TRAILBLAZE = registryWorker.build(
					"trailblaze",
					Trailblaze::new,
					DodgeSkill.createDodgeBuilder().setAnimations(
							() -> LumiereSwordAnims.IMPERATRICE_TRAILBLAZE_FWD,
							() -> LumiereSwordAnims.IMPERATRICE_TRAILBLAZE_BACK,
							() -> LumiereSwordAnims.IMPERATRICE_TRAILBLAZE_LEFT,
							() -> LumiereSwordAnims.IMPERATRICE_TRAILBLAZE_RIGHT
					)
			);
		WeaponInnateSkill BlazeStinger = registryWorker.build(
				"blaze_stinger",
				FireDriver::new,
				WeaponInnateSkill.createWeaponInnateBuilder().setResource(Skill.Resource.NONE)
		);
		BlazeStinger.newProperty()
				.addProperty(AnimationProperty.AttackPhaseProperty.MAX_STRIKES_MODIFIER, ValueModifier.multiplier(2))
				.addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.05f))
				.addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER, ValueModifier.multiplier(1.5f))
				.addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG)
				.addProperty(AnimationProperty.AttackPhaseProperty.SOURCE_TAG, Set.of(EpicFightDamageType.WEAPON_INNATE, EpicFightDamageType.GUARD_PUNCTURE));
		FIRE_DRIVER = BlazeStinger;


	}
}
