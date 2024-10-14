package net.forixaim.vfo.world.entity.charlemagne;

import net.forixaim.vfo.registry.MeshRegistry;
import net.forixaim.vfo.world.entity.charlemagne.model.CharlemagneMesh;
import net.forixaim.vfo.world.entity.charlemagne.model.CharlemagneModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import yesman.epicfight.api.client.model.MeshProvider;
import yesman.epicfight.client.renderer.patched.entity.PHumanoidRenderer;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;

public class PCharlemagneRenderer extends PHumanoidRenderer<Charlemagne, CharlemagnePatch, CharlemagneModel, CharlemagneRenderer, CharlemagneMesh>
{
	public PCharlemagneRenderer(EntityRendererProvider.Context context, EntityType<?> entityType)
	{
		super(()-> MeshRegistry.CHARLEMAGNE, context, entityType);
	}

	@Override
	public MeshProvider<CharlemagneMesh> getDefaultMesh()
	{
		return () -> MeshRegistry.CHARLEMAGNE;
	}
}
