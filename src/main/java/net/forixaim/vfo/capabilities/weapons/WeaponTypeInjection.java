package net.forixaim.vfo.capabilities.weapons;

import net.forixaim.efm_ex.api.events.ExCapWeaponRegistryEvent;
import net.forixaim.efm_ex.capabilities.weapon_presets.CoreMovesets;
import net.forixaim.efm_ex.capabilities.weapon_presets.ExCapWeapons;
import net.forixaim.efm_ex.capabilities.weapon_presets.MainConditionals;
import net.forixaim.efm_ex.capabilities.weapon_presets.MovesetMappings;
import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.capabilities.ex_cap_weapons.OmneriaProviders;
import net.forixaim.vfo.capabilities.ex_cap_weapons.movesets.ImperatriceMovesets;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponTypeInjection
{
	@SubscribeEvent
	public static void inject(ExCapWeaponRegistryEvent event)
	{
		event.addProvider(OmneriaExCapWeapons.ORIGIN_JOYEUSE, MainConditionals.default2HWieldStyle, MainConditionals.LiechtenauerCondition, MainConditionals.SwordShieldLS, OmneriaProviders.IMPERATRICE_SWORD_PROVIDER);
		event.addMSEZ(OmneriaExCapWeapons.ORIGIN_JOYEUSE, MovesetMappings.longswordMovesets);
		event.addMoveset(OmneriaExCapWeapons.ORIGIN_JOYEUSE, LumiereStyles.IMPERATRICE_SWORD, ImperatriceMovesets.IMPERATRICE_SWORD_PRIMARY);

		event.addProvider(ExCapWeapons.LONGSWORD, OmneriaProviders.IMPERATRICE_SWORD_PROVIDER);
		event.addMoveset(ExCapWeapons.LONGSWORD, LumiereStyles.IMPERATRICE_SWORD, ImperatriceMovesets.IMPERATRICE_SWORD_PRIMARY);

		event.addProvider(ExCapWeapons.TACHI, OmneriaProviders.IMPERATRICE_SWORD_PROVIDER);
		event.addMoveset(ExCapWeapons.TACHI, LumiereStyles.IMPERATRICE_SWORD, ImperatriceMovesets.IMPERATRICE_SWORD_PRIMARY);
	}
}
