package net.forixaim.vfo.events;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.registry.EntityRegistry;
import net.forixaim.vfo.registry.MeshRegistry;
import net.forixaim.vfo.registry.ModelLayers;
import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import net.forixaim.vfo.world.entity.charlemagne.PCharlemagneRenderer;
import net.forixaim.vfo.world.entity.charlemagne.model.CharlemagneModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.api.client.model.transformer.HumanoidModelTransformer;
import yesman.epicfight.api.forgeevent.EntityPatchRegistryEvent;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;


public class ModBusEvents
{
	@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class CommonEvents
	{
		@SubscribeEvent
		public static void onEntityPatchRegister(EntityPatchRegistryEvent event)
		{
			event.getTypeEntry().put(EntityRegistry.CHARLEMAGNE.get(), entity -> CharlemagnePatch::new);
		}

		@SubscribeEvent
		public static void onAttributeRegister(EntityAttributeCreationEvent event)
		{
			event.put(EntityRegistry.CHARLEMAGNE.get(), Charlemagne.createAttributes().build());
		}
	}

	@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, value = {Dist.CLIENT}, bus = Mod.EventBusSubscriber.Bus.MOD)
	public static class ClientEvents
	{
		@SubscribeEvent
		public static void onPatchRenderRegister(PatchedRenderersEvent.Add event)
		{
			event.addPatchedEntityRenderer(EntityRegistry.CHARLEMAGNE.get(), entityType -> new PCharlemagneRenderer(event.getContext(), entityType));
		}
	}

}
