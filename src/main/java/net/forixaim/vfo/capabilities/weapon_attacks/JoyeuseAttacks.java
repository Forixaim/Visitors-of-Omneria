package net.forixaim.vfo.capabilities.weapon_attacks;

import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.efm_ex.capabilities.CoreCapability;
import net.forixaim.efm_ex.capabilities.weapon_presets.attacks.LongswordAttacks;
import net.forixaim.efm_ex.capabilities.weapon_presets.attacks.MountedAttacks;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.capabilities.weapon_types.JoyeuseType;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.forixaim.vfo.special.SpecialPlayers;
import net.minecraft.client.Minecraft;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.players.PlayerList;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;

import java.util.UUID;
import java.util.function.Function;

public class JoyeuseAttacks
{
	public static Function<LivingEntityPatch<?>, Boolean> usernameCheck(String user)
	{
		UUID uuid = UUID.fromString(user);
		return (entityPatch -> {
			//Client side
			if (entityPatch instanceof PlayerPatch<?> pP)
			{
				if (pP.isLogicalClient() && Minecraft.getInstance().getUser().getUuid().equals(uuid.toString()))
					return true;
				return pP instanceof ServerPlayerPatch sPP && sPP.getOriginal().getUUID().equals(uuid);
			}
			return false;
		});
	}
	public static void injectAttacks()
	{
		JoyeuseType.getInstance().getStyleComboProviderRegistry().add(
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
		JoyeuseType.getInstance().getStyleComboProviderRegistry().add(
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
								entityPatch ->
								{
									if (entityPatch instanceof ServerPlayerPatch spp && spp.getOriginal().getUUID().equals(SpecialPlayers.FORIXAIM))
									{
										return true;
									}
									return entityPatch.isLogicalClient() && Minecraft.getInstance().getUser().getUuid().equals("42479ed5a8f04967bfb17500577896a6");
								}
						)
				)
		);
		JoyeuseType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.OCHS, LongswordAttacks.LiechtenauerAttackCycle));
		JoyeuseType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.ONE_HAND, LongswordAttacks.defaultTwoHandAttackCycle));
		JoyeuseType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.TWO_HAND, LongswordAttacks.defaultTwoHandAttackCycle));
		JoyeuseType.getInstance().getAttackCombinationRegistry().add(CoreCapability.COMBO_PROVIDER_REGISTRY.add(CapabilityItem.Styles.MOUNT, MountedAttacks.mountedSwordAttack));
		JoyeuseType.getInstance().getAttackCombinationRegistry().add(
				CoreCapability.COMBO_PROVIDER_REGISTRY.add(LumiereStyles.IMPERATRICE_SWORD, ImperatriceWeaponAttacks.imperatriceSword)
		);
		JoyeuseType.getInstance().getAttackCombinationRegistry().add(
				CoreCapability.COMBO_PROVIDER_REGISTRY.add(LumiereStyles.FORIXAIM_SWORD, ImperatriceWeaponAttacks.imperatriceForixaimSword)
		);
	}
}
