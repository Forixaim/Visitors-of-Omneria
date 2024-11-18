package net.forixaim.vfo.capabilities.weapon_attacks;


import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.types.LongswordType;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.minecraft.client.Minecraft;

public class LongswordAttacks
{
	public static void injectAttacks()
	{
		LongswordType.getInstance().getStyleComboProviderRegistry().add(
				CoreCapability.COMBO_PROVIDER_REGISTRY.add(
						"imperatrice_lumiere_sword",
						ProviderConditionalType.SKILL_EXISTENCE,
						BattleArtsSkillSlots.BATTLE_STYLE,
						OmneriaSkills.IMPERATRICE_LUMIERE,
						LumiereStyles.IMPERATRICE_SWORD,
						false,
						OmneriaSkills.IMPERATRICE_WP
				)
		);
		LongswordType.getInstance().getStyleComboProviderRegistry().add(
				CoreCapability.COMBO_PROVIDER_REGISTRY.add(
						"forixaim_lumiere_sword",
						LumiereStyles.FORIXAIM_SWORD,
						false,
						OmneriaSkills.IMPERATRICE_WP,
						CoreCapability.COMBO_PROVIDER_REGISTRY.add(
								"sub_1",
								ProviderConditionalType.SKILL_EXISTENCE,
								BattleArtsSkillSlots.BATTLE_STYLE,
								OmneriaSkills.IMPERATRICE_LUMIERE,
								LumiereStyles.IMPERATRICE_SWORD,
								false,
								OmneriaSkills.IMPERATRICE_WP
						),
						CoreCapability.COMBO_PROVIDER_REGISTRY.add(
								"sub_2",
								LumiereStyles.IMPERATRICE_SWORD,
								false,
								OmneriaSkills.IMPERATRICE_WP,
								entityPatch -> entityPatch.isLogicalClient() && Minecraft.getInstance().getUser().getUuid().equals("42479ed5a8f04967bfb17500577896a6")
						)
				)
		);

		LongswordType.getInstance().getAttackCombinationRegistry().add(
				CoreCapability.COMBO_PROVIDER_REGISTRY.add(LumiereStyles.IMPERATRICE_SWORD, ImperatriceWeaponAttacks.imperatriceSword)
		);
		LongswordType.getInstance().getAttackCombinationRegistry().add(
				CoreCapability.COMBO_PROVIDER_REGISTRY.add(LumiereStyles.FORIXAIM_SWORD, ImperatriceWeaponAttacks.imperatriceForixaimSword)
		);
	}
}
