package net.forixaim.vfo.world.entity.patches;

import net.forixaim.vfo.world.entity.types.AbstractFriendlyNPC;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.world.capabilities.entitypatch.Faction;
import yesman.epicfight.world.capabilities.entitypatch.HumanoidMobPatch;

public class FriendlyHumanoidNPCPatch<T extends AbstractFriendlyNPC> extends HumanoidMobPatch<T>
{

	public FriendlyHumanoidNPCPatch(Faction faction)
	{
		super(faction);
	}

	@Override
	public void initAnimator(Animator animator)
	{

	}

	@Override
	public void updateMotion(boolean b)
	{

	}
}
