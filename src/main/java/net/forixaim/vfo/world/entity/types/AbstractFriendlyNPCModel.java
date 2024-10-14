package net.forixaim.vfo.world.entity.types;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;

public abstract class AbstractFriendlyNPCModel<T extends AbstractFriendlyNPC> extends HumanoidModel<T>
{
	public AbstractFriendlyNPCModel(ModelPart pRoot)
	{
		super(pRoot);
	}
}
