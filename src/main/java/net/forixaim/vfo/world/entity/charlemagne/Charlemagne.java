package net.forixaim.vfo.world.entity.charlemagne;

import com.mojang.logging.LogUtils;
import net.forixaim.vfo.world.entity.types.AbstractFriendlyNPC;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.ForgeMod;
import org.jetbrains.annotations.NotNull;

public class Charlemagne extends AbstractFriendlyNPC
{
	public CharlemagnePatch patch;
	public final TargetingConditions DefCond = TargetingConditions.forCombat().range(this.getAttributeValue(Attributes.FOLLOW_RANGE)).selector((pred) -> pred instanceof Enemy);


	public Charlemagne(EntityType<? extends AbstractFriendlyNPC> p_21683_, Level p_21684_)
	{
		super(p_21683_, p_21684_);
	}

	@Override
	public boolean canBeAffected(MobEffectInstance p_70687_1_) {
		return p_70687_1_.getEffect() != MobEffects.WITHER && super.canBeAffected(p_70687_1_);
	}

	@Override
	public boolean fireImmune()
	{
		return true;
	}

	@Override
	public boolean canStandOnFluid(FluidState p_204042_)
	{
		return p_204042_.is(Fluids.LAVA) || p_204042_.is(Fluids.FLOWING_LAVA);
	}
	@Override
	public void kill()
	{
		super.kill();
	}

	@Override
	protected void registerGoals()
	{
		goalSelector.addGoal(0, new FloatGoal(this));
	}

	public static AttributeSupplier.Builder createAttributes()
	{
		return createLivingAttributes()
				.add(Attributes.MAX_HEALTH, 2500)
				.add(Attributes.MOVEMENT_SPEED, 0.25D)
				.add(Attributes.ARMOR, 20)
				.add(Attributes.ARMOR_TOUGHNESS, 20)
				.add(Attributes.ATTACK_KNOCKBACK, 2)
				.add(Attributes.KNOCKBACK_RESISTANCE, 1f)
				.add(Attributes.ATTACK_DAMAGE, 10)
				.add(Attributes.FOLLOW_RANGE, 80.0)
				.add(ForgeMod.STEP_HEIGHT_ADDITION.get(), 1.5);

	}

	@Override
	public boolean isInvulnerableTo(@NotNull DamageSource p_20122_)
	{
		if ((this.patch.brain != null && (this.patch.brain.getMode().is(CharlemagneMode.FRIENDLY) || this.patch.brain.getMode().is(CharlemagneMode.DEFENSE))) && !p_20122_.is(DamageTypes.GENERIC_KILL))
			return true;
		return super.isInvulnerableTo(p_20122_);
	}

	@Override
	protected @NotNull InteractionResult mobInteract(@NotNull Player p_21472_, @NotNull InteractionHand p_21473_)
	{
		LogUtils.getLogger().debug("Interacted");
		if (p_21472_.getItemInHand(InteractionHand.MAIN_HAND).is(Items.DEBUG_STICK) && !this.level().isClientSide)
			this.patch.brain.debugFire();
		return InteractionResult.SUCCESS;
	}
}
