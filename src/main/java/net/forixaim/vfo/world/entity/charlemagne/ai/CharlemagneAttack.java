package net.forixaim.vfo.world.entity.charlemagne.ai;

import com.google.common.collect.Lists;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;

import java.util.List;

public class CharlemagneAttack
{
	private final AnimationProvider<AttackAnimation> attackAnimation;

	//Primarily for multi-hit attacks
	private List<AttackAnimation.Phase> attacks = Lists.newArrayList();

	private float suspectConfidence;
	private boolean feint;

	public CharlemagneAttack(AttackAnimation attackAnimation)
	{
		this.attackAnimation = () -> attackAnimation;
		this.attacks.addAll(List.of(attackAnimation.phases));
	}
}
