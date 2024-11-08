package net.forixaim.vfo.animations;


import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.CharlemagneFlamiereAnims;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAnims;
import net.forixaim.vfo.animations.npc_interactions.charlemagne.FacialAnimations;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;

@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CoreRegistry
{
	@SubscribeEvent
	public static void Register(AnimationRegistryEvent Event)
	{
		Event.getRegistryMap().put(VisitorsOfOmneria.MOD_ID, CoreRegistry::Build);
	}

	public static void Build()
	{
		FacialAnimations.Build();
		LumiereSwordAnims.Build();
		CharlemagneFlamiereAnims.Build();
	}


}
