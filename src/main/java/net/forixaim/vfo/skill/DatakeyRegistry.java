package net.forixaim.vfo.skill;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.FireDriver;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.FlareBlitz;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.ImperatriceLumiere;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.Trailblaze;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active.ImperatriceSpecials;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class DatakeyRegistry
{
	public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation(EpicFightMod.MODID, "skill_data_keys"), VisitorsOfOmneria.MOD_ID);

	public static final RegistryObject<SkillDataKey<Float>> HEAT =
			DATA_KEYS.register("heat", () -> SkillDataKey.createFloatKey
					(
							0,
							true,
							ImperatriceLumiere.class
					));
	public static final RegistryObject<SkillDataKey<Integer>> BLAZE_COMBO =
			DATA_KEYS.register("blaze_combo", () -> SkillDataKey.createIntKey
					(
							0,
							false,
							FlareBlitz.class));
	public static final RegistryObject<SkillDataKey<Boolean>> FLARE_BURST =
			DATA_KEYS.register("flare_burst", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							ImperatriceLumiere.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> SPOT_DODGE =
			DATA_KEYS.register("spot_dodge", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							Trailblaze.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> JAB =
			DATA_KEYS.register("jab", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							FlareBlitz.class
					));
	public static final RegistryObject<SkillDataKey<Integer>> CERCLE_DE_FLAMME =
			DATA_KEYS.register("cercle_de_feu", () -> SkillDataKey.createIntKey
					(
							0,
							false,
							FlareBlitz.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> FTILT =
			DATA_KEYS.register("ftilt", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							FlareBlitz.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> ULTIMATE_ART_ACTIVE =
			DATA_KEYS.register("ultimate_art_active", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							ImperatriceLumiere.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> ULTIMATE_ART_TRY_CONNECTED =
			DATA_KEYS.register("ultimate_art_try_connect", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							ImperatriceLumiere.class,
							ImperatriceSpecials.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> ULTIMATE_ART_READY =
			DATA_KEYS.register("ultimate_art_ready", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							ImperatriceLumiere.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> CHARGE_EXECUTING =
			DATA_KEYS.register("charge_executing", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							FireDriver.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> CHARGE_AERIAL =
			DATA_KEYS.register("charge_aerial_used", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							FireDriver.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> SPECIAL_EXECUTING =
			DATA_KEYS.register("imperatrice_sp_executing", () -> SkillDataKey.createBooleanKey
					(
							false,
							true,
							ImperatriceLumiere.class
					));
	public static final RegistryObject<SkillDataKey<Boolean>> ASTROLABE_EXECUTE =
			DATA_KEYS.register("astrolabe_executing", () -> SkillDataKey.createBooleanKey(
					false,
					true,
					ImperatriceSpecials.class
			));
}
