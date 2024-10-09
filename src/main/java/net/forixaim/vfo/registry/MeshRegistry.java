package net.forixaim.vfo.registry;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.world.entity.charlemagne.model.CharlemagneMesh;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.ModelBuildEvent;
import yesman.epicfight.client.mesh.HumanoidMesh;

@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class MeshRegistry
{
	public static CharlemagneMesh CHARLEMAGNE;

	@SubscribeEvent
	public static void onMeshBuild(ModelBuildEvent.MeshBuild event)
	{
		CHARLEMAGNE = event.getAnimated(VisitorsOfOmneria.MOD_ID, "entity/charlemagne", CharlemagneMesh::new);
	}
}
