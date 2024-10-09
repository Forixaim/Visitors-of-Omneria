package net.forixaim.vfo.world.entity.charlemagne;

import net.forixaim.vfo.world.entity.types.AbstractFriendlyNPC;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class Charlemagne extends AbstractFriendlyNPC
{
	private CharlemagneMode currentMode;

	public Charlemagne(EntityType<? extends AbstractFriendlyNPC> p_21683_, Level p_21684_)
	{
		super(p_21683_, p_21684_);
		currentMode = CharlemagneMode.FRIENDLY;
	}

	@Override
	public boolean canBeAffected(MobEffectInstance p_70687_1_) {
		return p_70687_1_.getEffect() != MobEffects.WITHER && super.canBeAffected(p_70687_1_);
	}

	@Override
	protected void registerGoals()
	{
		goalSelector.addGoal(0, new FloatGoal(this));
		goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 2500)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ARMOR, 20)
				.add(Attributes.ARMOR_TOUGHNESS, 20)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1f)
				.add(Attributes.ATTACK_DAMAGE, 10)
				.add(Attributes.FOLLOW_RANGE, 8.0);

	}

	@Override
	public boolean isInvulnerableTo(@NotNull DamageSource p_20122_)
	{
		if (currentMode.is(CharlemagneMode.FRIENDLY) || currentMode.is(CharlemagneMode.DEFENSE))
			return true;
		return super.isInvulnerableTo(p_20122_);
	}
}
