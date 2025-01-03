package net.forixaim.vfo.capabilities.ex_cap_weapons;

import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.efm_ex.api.providers.ProviderConditional;
import net.forixaim.efm_ex.api.providers.ProviderConditionalType;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.forixaim.vfo.special.SpecialPlayers;
import net.minecraft.client.Minecraft;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.Style;

import java.util.UUID;

public class OmneriaProviders
{
    public static ProviderConditional IMPERATRICE_SWORD_PROVIDER = ProviderConditional.builder()
            .setType(ProviderConditionalType.SKILL_EXISTENCE)
            .setSlot(BattleArtsSkillSlots.BATTLE_STYLE)
            .setSkillToCheck(OmneriaSkills.IMPERATRICE_LUMIERE)
            .setWieldStyle(LumiereStyles.IMPERATRICE_SWORD)
            .isVisibleOffHand(false)
            .build();

    public static ProviderConditional CUSTOM_USERNAME(UUID username, Style ws, Boolean offhand)
    {
        return ProviderConditional.builder()
                .setType(ProviderConditionalType.CUSTOM)
                .isVisibleOffHand(offhand)
                .setWieldStyle(ws)
                .setCustomFunction(
                        livingEntityPatch ->
                        {
                            if (livingEntityPatch instanceof PlayerPatch<?> pP)
                            {
                                if (pP.isLogicalClient())
                                {
                                    return Minecraft.getInstance().getUser().getUuid().equals(username.toString());
                                }
                                if (!pP.isLogicalClient())
                                {
                                    return ((ServerPlayerPatch) pP).getOriginal().getUUID().equals(username);
                                }
                            }
                            return false;
                        }
                )
                .build();
    }

    //Added later
    public static ProviderConditional FORIXAIM_SWORD_PROVIDER = ProviderConditional.builder()
            .setType(ProviderConditionalType.COMPOSITE)
            .setWieldStyle(LumiereStyles.FORIXAIM_SWORD)
            .isVisibleOffHand(false)
            .setProviderConditionals(
                    IMPERATRICE_SWORD_PROVIDER,
                    CUSTOM_USERNAME(SpecialPlayers.FORIXAIM, LumiereStyles.FORIXAIM_SWORD, false)
            )
            .build();
}
