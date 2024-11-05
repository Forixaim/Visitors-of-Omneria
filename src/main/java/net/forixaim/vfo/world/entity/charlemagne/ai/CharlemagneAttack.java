package net.forixaim.vfo.world.entity.charlemagne.ai;

import com.google.common.collect.Lists;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;

import java.util.List;

public class CharlemagneAttack
{
	private final AnimationProvider<AttackAnimation> attackAnimation;

	//Primarily for multi-hit attacks
	private List<AttackAnimation.Phase> attacks = Lists.newArrayList();

	private float suspectConfidence;
	private boolean feint;

	private CharlemagneAttack(AttackAnimation attackAnimation)
	{
		this.attackAnimation = () -> attackAnimation;
		this.attacks.addAll(List.of(attackAnimation.phases));
		this.suspectConfidence = 1;
	}

	public static CharlemagneAttack createAttack(StaticAnimation animation)
	{
		if (animation instanceof AttackAnimation attack)
			return new CharlemagneAttack(attack);
		return null;
	}

	public float getSuspectConfidence()
	{
		return suspectConfidence;
	}

	public void Fire(CharlemagnePatch attacker)
	{
		attacker.playAnimationSynchronized(attackAnimation.get(), 0);
	}

	public void Feint(CharlemagnePatch attacker)
	{
		attacker.playAnimationSynchronized(attackAnimation.get(), 2);
	}
}
