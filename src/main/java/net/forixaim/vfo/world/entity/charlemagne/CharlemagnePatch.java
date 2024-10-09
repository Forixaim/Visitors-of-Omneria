package net.forixaim.vfo.world.entity.charlemagne;

import net.forixaim.vfo.world.entity.patches.FriendlyHumanoidNPCPatch;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.world.capabilities.entitypatch.Faction;

public class CharlemagnePatch extends FriendlyHumanoidNPCPatch<Charlemagne>
{
	public CharlemagnePatch()
	{
		super(Faction.NEUTRAL);
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
