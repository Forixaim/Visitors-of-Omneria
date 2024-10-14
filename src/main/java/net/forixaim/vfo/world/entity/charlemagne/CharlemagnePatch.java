package net.forixaim.vfo.world.entity.charlemagne;

import net.forixaim.vfo.world.entity.patches.FriendlyHumanoidNPCPatch;
import net.minecraftforge.event.entity.living.LivingEvent;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.world.capabilities.entitypatch.Faction;

public class CharlemagnePatch extends FriendlyHumanoidNPCPatch<Charlemagne>
{
	Charlemagne entity = this.original;
	public CharlemagnePatch()
	{
		super(Faction.NEUTRAL);
	}

	@Override
	public void initAnimator(Animator animator)
	{
		animator.addLivingAnimation(LivingMotions.IDLE, Animations.BIPED_IDLE);
		animator.addLivingAnimation(LivingMotions.WALK, Animations.BIPED_IDLE);
	}

	@Override
	public void updateMotion(boolean b)
	{
		super.updateMotion(b);
	}

	@Override
	public void tick(LivingEvent.LivingTickEvent event)
	{
		super.tick(event);
	}
}
