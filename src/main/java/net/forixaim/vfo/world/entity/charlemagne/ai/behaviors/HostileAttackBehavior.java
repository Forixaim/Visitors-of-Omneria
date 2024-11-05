package net.forixaim.vfo.world.entity.charlemagne.ai.behaviors;

import net.forixaim.vfo.animations.battle_style.charlemagne_flamiere.CharlemagneFlamiereAnims;
import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import net.forixaim.vfo.world.entity.charlemagne.ai.CharlemagneBrain;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;
import org.lwjgl.system.MathUtil;
import reascer.wom.gameasset.WOMAnimationsEntity;
import reascer.wom.world.entity.mob.LupusRex;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;

import java.util.Objects;
import java.util.Random;

public class HostileAttackBehavior
{
	//Flags
	private boolean bEncirclement = false;
	private int timer;
	private int cooldown;
	private boolean lR;
	private final CharlemagneBrain brain;
	private final Charlemagne mob;
	private final CharlemagnePatch mobPatch;

	//Movement Flags
	public boolean hyperChase = false;
	public boolean chasing = false;

	public HostileAttackBehavior(final CharlemagnePatch patch, final CharlemagneBrain brain, final Charlemagne mob)
	{
		this.mob = mob;
		this.mobPatch = patch;
		this.brain = brain;
	}

	private void closeIn(LivingEntity opponent, float distance)
	{
		double theta = MathUtils.getYRotOfVector(opponent.position().subtract(mob.position()));
		mobPatch.rotateTo(opponent, 360f, true);
		Vec3 closeInPosition = new Vec3(distance * Math.cos(theta), opponent.position().y(), distance * Math.sin(theta));
		mob.getNavigation().moveTo(closeInPosition.x(), closeInPosition.y(), closeInPosition.z(), 1);
	}

	public void selectLivingMotion(CharlemagnePatch patch, boolean considerInaction)
	{
		if (hyperChase)
		{
			patch.currentLivingMotion = LivingMotions.RUN;
		}
		else if (mob.getNavigation().isInProgress())
		{
			patch.currentLivingMotion = LivingMotions.WALK;
		}
		else
		{
			mobPatch.currentLivingMotion = LivingMotions.IDLE;
		}
	}

	private void fireAttacks()
	{

	}

	public void onReceiveHostileAttack(LivingEntity opponent)
	{

		if (opponent == null || !opponent.isAlive())
		{
			return;
		}
		mobPatch.rotateTo(opponent, 360f, true);
		if (mob.distanceTo(opponent) > 10f && (!mob.getNavigation().isInProgress() || (opponent instanceof PathfinderMob pathfinderMob && pathfinderMob.getNavigation().isInProgress()) || !hyperChase))
		{
			chasing = true;
			hyperChase = true;
			mob.getNavigation().moveTo(opponent, 1.5f);
		}
		if (mob.distanceTo(opponent) > 4f && mob.distanceTo(opponent) <= 10 && (!mob.getNavigation().isInProgress() ||
				(opponent instanceof PathfinderMob pathfinderMob &&
						pathfinderMob.getNavigation().isInProgress()) || hyperChase))
		{
			hyperChase = false;
			chasing = true;
			mob.getNavigation().moveTo(opponent, 1);
		}
		if (mob.distanceTo(opponent) < 3f && mob.getNavigation().isInProgress())
		{
			chasing = false;
			mob.setTarget(null);
			mob.getNavigation().stop();
		}
	}
}
