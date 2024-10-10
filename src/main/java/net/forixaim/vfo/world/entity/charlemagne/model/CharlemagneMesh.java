package net.forixaim.vfo.world.entity.charlemagne.model;

import yesman.epicfight.api.client.model.AnimatedMesh;
import yesman.epicfight.api.client.model.AnimatedVertexBuilder;
import yesman.epicfight.api.client.model.MeshPartDefinition;
import yesman.epicfight.api.client.model.MeshProvider;
import yesman.epicfight.client.mesh.HumanoidMesh;

import java.util.List;
import java.util.Map;

public class CharlemagneMesh extends HumanoidMesh implements MeshProvider<CharlemagneMesh>
{

	public CharlemagneMesh(Map<String, float[]> arrayMap, Map<MeshPartDefinition, List<AnimatedVertexBuilder>> parts, AnimatedMesh parent, RenderProperties properties)
	{
		super(arrayMap, parts, parent, properties);
	}

	@Override
	public CharlemagneMesh get()
	{
		return this;
	}
}
