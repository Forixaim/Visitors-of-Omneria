package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere;

import com.mojang.logging.LogUtils;
import io.netty.buffer.Unpooled;
import net.forixaim.bs_api.AnimationHelpers;
import net.forixaim.bs_api.battle_arts_skills.aerials.MidAirAttack;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAerialAttacks;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.network.client.CPExecuteSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillCategories;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class BurningSky extends MidAirAttack
{
	private static final AnimationProvider<AttackAnimation> NEUTRAL_AERIAL = () -> (AttackAnimation) LumiereSwordAerialAttacks.IMPERATRICE_SWORD_NEUTRAL_AERIAL;
	private static final AnimationProvider<AttackAnimation> FORWARD_AERIAL = () -> (AttackAnimation) LumiereSwordAerialAttacks.IMPERATRICE_SWORD_FORWARD_AERIAL;
	private static final AnimationProvider<AttackAnimation> DOWN_AERIAL = () -> (AttackAnimation) LumiereSwordAerialAttacks.IMPERATRICE_SWORD_DOWN_AERIAL;
	private static final AnimationProvider<AttackAnimation> BACK_AERIAL = () -> (AttackAnimation) LumiereSwordAerialAttacks.IMPERATRICE_BACK_AERIAL;
	private static final AnimationProvider<AttackAnimation> UP_AERIAL = () -> (AttackAnimation) LumiereSwordAerialAttacks.IMPERATRICE_UP_AERIAL;


	public BurningSky(Builder<? extends Skill> builder)
	{
		super(builder);
	}


	public static Builder<BurningSky> createImperatriceAerialBuilder() {
		return new Builder<BurningSky>().setCategory(SkillCategories.AIR_ATTACK).setActivateType(ActivateType.ONE_SHOT).setResource(Resource.STAMINA);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public FriendlyByteBuf gatherArguments(LocalPlayerPatch executer, ControllEngine controllEngine)
	{
		return ArgumentGatherers.UniversalDirectionalInput(executer, null);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public Object getExecutionPacket(LocalPlayerPatch executer, FriendlyByteBuf args)
	{
		return ArgumentGatherers.DirectionalExecutionPacket(executer, args, this);
	}

	@Override
	public boolean isExecutableState(PlayerPatch<?> executer) {
		EntityState playerState = executer.getEntityState();
		Player player = executer.getOriginal();
		return (!player.isPassenger() && AnimationHelpers.isInAir(executer) && playerState.canBasicAttack());
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executor)
	{
		if (executor.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executor) == LumiereStyles.IMPERATRICE_SWORD)
			return super.canExecute(executor) && executor.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().hasData(DatakeyRegistry.CHARGE_EXECUTING.get()) && !executor.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue(DatakeyRegistry.CHARGE_EXECUTING.get());
		return super.canExecute(executor);
	}

	@Override
	public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
		if (executer.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executer) == LumiereStyles.IMPERATRICE_SWORD || executer.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executer).equals(LumiereStyles.FORIXAIM_SWORD))
		{
			int fw = args.readInt();
			int sw = args.readInt();
			StaticAnimation attackMotion = null;

			if (executer.getOriginal().isShiftKeyDown())
			{
				LogUtils.getLogger().debug("Down Aerial");
				attackMotion = DOWN_AERIAL.get();
			}
			else if (fw == 1)
			{
				LogUtils.getLogger().debug("Forward Aerial");
				attackMotion = FORWARD_AERIAL.get();
			}
			else if (fw == -1)
			{
				LogUtils.getLogger().debug("Back Aerial");
				attackMotion = BACK_AERIAL.get();
			}
			else if (sw == 1)
			{
				LogUtils.getLogger().debug("Left Aerial");
				attackMotion = UP_AERIAL.get();
			}
			else if (sw == -1)
			{
				LogUtils.getLogger().debug("Right Aerial");
			}
			else
			{
				LogUtils.getLogger().debug("Neutral Aerial");
				attackMotion = NEUTRAL_AERIAL.get();
			}

			if (attackMotion != null) {
				executer.playAnimationSynchronized(attackMotion, 0);
			}
		}
		else
		{
			super.executeOnServer(executer, args);
		}
	}
}
