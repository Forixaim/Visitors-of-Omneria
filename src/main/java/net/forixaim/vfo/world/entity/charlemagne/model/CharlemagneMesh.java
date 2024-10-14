package net.forixaim.vfo.world.entity.charlemagne.model;

import net.minecraft.world.entity.EquipmentSlot;
import yesman.epicfight.api.client.model.*;
import yesman.epicfight.client.mesh.HumanoidMesh;

import java.util.List;
import java.util.Map;

public class CharlemagneMesh extends HumanoidMesh
{
	public CharlemagneMesh(Map<String, float[]> arrayMap, Map<MeshPartDefinition, List<AnimatedVertexBuilder>> parts, AnimatedMesh parent, RenderProperties properties)
	{
		super(arrayMap, parts, parent, properties);
		leftLeg.setHidden(true);
		rightLeg.setHidden(true);
	}

	@Override
	public AnimatedMesh getHumanoidArmorModel(EquipmentSlot slot) {
		return switch (slot)
		{
			case HEAD -> Meshes.HELMET_PIGLIN;
			case CHEST -> Meshes.CHESTPLATE;
			case LEGS -> Meshes.LEGGINS;
			case FEET -> Meshes.BOOTS;
			default -> null;
		};
	}
}
