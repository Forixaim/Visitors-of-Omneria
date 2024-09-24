package net.forixaim.vfo.capabilities.weapons;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.capabilities.weapon_attacks.JoyeuseAttacks;
import net.forixaim.vfo.capabilities.weapon_attacks.LongswordAttacks;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class WeaponTypeInjection
{
	@SubscribeEvent
	public static void inject(FMLCommonSetupEvent event)
	{
		LongswordAttacks.injectAttacks();
		JoyeuseAttacks.injectAttacks();
	}
}
