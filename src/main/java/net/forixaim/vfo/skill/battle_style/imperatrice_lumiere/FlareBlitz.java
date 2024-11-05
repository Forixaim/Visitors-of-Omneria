package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import net.forixaim.bs_api.AnimationHelpers;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordGroundAttacks;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.PlayerRideableJumping;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.AttackAnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.*;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.entity.eventlistener.BasicAttackEvent;
import yesman.epicfight.world.entity.eventlistener.ComboCounterHandleEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;
import yesman.epicfight.world.entity.eventlistener.SkillConsumeEvent;

import java.util.List;
import java.util.UUID;

/**
 * This class replaces the basic attack motions when created.
 */
public class FlareBlitz extends BasicAttack
{
	private static final UUID EVENT_UUID = UUID.fromString("bb4af80f-603a-4b52-a92d-1d4a444749af");
	private static final List<AnimationProvider<?>> IMPERATRICE_SWORD_JAB_SET = Lists.newArrayList(
			() -> LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB1,
			() -> LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB2,
			() -> LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB3
	);

	public static Builder<FlareBlitz> createImperatriceAttackSet()
	{

		return (new Builder<FlareBlitz>()).setCategory(SkillCategories.BASIC_ATTACK).setActivateType(ActivateType.ONE_SHOT).setResource(Resource.NONE);
	}
	private static final AttackAnimationProvider DASH_ATTACK = () -> (AttackAnimation) LumiereSwordGroundAttacks.IMPERATRICE_SWORD_DASH_ATTACK;
	private static final AttackAnimationProvider CROUCH_LIGHT = () -> (AttackAnimation) LumiereSwordGroundAttacks.IMPERATRICE_SWORD_CROUCH_LIGHT;
	private static final AttackAnimationProvider FTILT = () -> (AttackAnimation) LumiereSwordGroundAttacks.IMPERATRICE_SWORD_FTILT;
	private static final AttackAnimationProvider CERCLE_DE_FEU = () -> (AttackAnimation) LumiereSwordGroundAttacks.IMPERATRICE_SWORD_CERCLE_DE_FLAMME;
	private static final AttackAnimationProvider RTILT = () -> (AttackAnimation) LumiereSwordGroundAttacks.IMPERATRICE_SWORD_RTILT;
	private static final AttackAnimationProvider LTILT = () -> (AttackAnimation) LumiereSwordGroundAttacks.IMPERATRICE_SWORD_LTILT;
	private static final AttackAnimationProvider BTILT = () -> (AttackAnimation) LumiereSwordGroundAttacks.IMPERATRICE_SWORD_BTILT;
	public FlareBlitz(Builder<? extends Skill> builder)
	{
		super(builder);
	}

	public static void setComboCounterWithEvent(ComboCounterHandleEvent.Causal reason, ServerPlayerPatch playerpatch, SkillContainer container, StaticAnimation causalAnimation, int value)
	{
		int prevValue = container.getDataManager().getDataValue(DatakeyRegistry.BLAZE_COMBO.get());
		ComboCounterHandleEvent comboResetEvent = new ComboCounterHandleEvent(reason, playerpatch, causalAnimation, prevValue, value);
		container.getExecuter().getEventListener().triggerEvents(PlayerEventListener.EventType.COMBO_COUNTER_HANDLE_EVENT, comboResetEvent);
		container.getDataManager().setData(DatakeyRegistry.BLAZE_COMBO.get(), comboResetEvent.getNextValue());
	}

	public static void setFtiltCombo(ComboCounterHandleEvent.Causal reason, ServerPlayerPatch playerpatch, SkillContainer container, StaticAnimation causalAnimation, int value)
	{
		int prevValue = container.getDataManager().getDataValue(DatakeyRegistry.CERCLE_DE_FLAMME.get());
		ComboCounterHandleEvent comboResetEvent = new ComboCounterHandleEvent(reason, playerpatch, causalAnimation, prevValue, value);
		container.getExecuter().getEventListener().triggerEvents(PlayerEventListener.EventType.COMBO_COUNTER_HANDLE_EVENT, comboResetEvent);
		container.getDataManager().setData(DatakeyRegistry.CERCLE_DE_FLAMME.get(), comboResetEvent.getNextValue());
	}

	@Override
	public boolean isExecutableState(PlayerPatch<?> executer)
	{
		EntityState playerState = executer.getEntityState();
		Player player = (Player)executer.getOriginal();
		if (executer.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executer) == LumiereStyles.IMPERATRICE_SWORD)
		{
			return !player.isSpectator() && !executer.isAirborneState() && !AnimationHelpers.isInAir(executer) && playerState.canBasicAttack();
		}
		return !(player.isSpectator() || executer.isInAir()|| !playerState.canBasicAttack());
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executor)
	{
		if (executor.getAdvancedHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executor) == LumiereStyles.IMPERATRICE_SWORD)
			return executor.getOriginal().onGround() && executor.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().hasData(DatakeyRegistry.CHARGE_EXECUTING.get()) && !executor.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue(DatakeyRegistry.CHARGE_EXECUTING.get());
		return super.canExecute(executor);
	}

	@Override
	public void onInitiate(SkillContainer container)
	{
		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_ATTACK, EVENT_UUID, event ->
		{
			if (event.getDamageSource().getAnimation() == LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB1 || event.getDamageSource().getAnimation() == LumiereSwordGroundAttacks.IMPERATRICE_SWORD_JAB2)
			{
				container.getDataManager().setDataSync(DatakeyRegistry.BLAZE_COMBO.get(), container.getDataManager().getDataValue(DatakeyRegistry.BLAZE_COMBO.get())+1, event.getPlayerPatch().getOriginal());
			}
		});
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_ATTACK, EVENT_UUID);
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
	public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args)
	{
		if (executer.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executer).equals(LumiereStyles.IMPERATRICE_SWORD) || executer.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executer).equals(LumiereStyles.FORIXAIM_SWORD))
		{
			if (!executer.getOriginal().onGround())
				return;
			SkillConsumeEvent event = new SkillConsumeEvent(executer, this, this.resource);
			executer.getEventListener().triggerEvents(PlayerEventListener.EventType.SKILL_CONSUME_EVENT, event);

			if (!event.isCanceled())
			{
				event.getResourceType().consumer.consume(this, executer, event.getAmount());
			}

			if (executer.getEventListener().triggerEvents(PlayerEventListener.EventType.BASIC_ATTACK_EVENT, new BasicAttackEvent(executer)))
			{
				return;
			}

			CapabilityItem cap = executer.getHoldingItemCapability(InteractionHand.MAIN_HAND);
			StaticAnimation attackMotion = null;
			ServerPlayer player = executer.getOriginal();
			SkillContainer skillContainer = executer.getSkill(this);
			SkillDataManager dataManager = skillContainer.getDataManager();
			int comboCounter = dataManager.getDataValue(DatakeyRegistry.BLAZE_COMBO.get());
			int cercleDeFeu = dataManager.getDataValue(DatakeyRegistry.CERCLE_DE_FLAMME.get());

			if (player.isPassenger())
			{
				Entity entity = player.getVehicle();

				if ((entity instanceof PlayerRideableJumping ridable && ridable.canJump()) && cap.availableOnHorse() && cap.getMountAttackMotion() != null)
				{
					comboCounter %= cap.getMountAttackMotion().size();
					attackMotion = cap.getMountAttackMotion().get(comboCounter).get();
					comboCounter++;
				}
			} else
			{
				int fw = args.readInt();
				int sw = args.readInt();
				int comboSize;
				boolean dashAttack = player.isSprinting();

				if (dashAttack)
				{
					// Dash Attack
					LogUtils.getLogger().debug("Dash Attack");
					attackMotion = DASH_ATTACK.get();
					comboCounter = 0;
					cercleDeFeu = 0;

				}
				else if (sw == -1)
				{
					// Right Attack
					LogUtils.getLogger().debug("Right Tilt");
					comboCounter = 0;
					attackMotion = RTILT.get();
					cercleDeFeu = 0;
				}
				else if (sw == 1)
				{
					// Left Attack
					LogUtils.getLogger().debug("Left Tilt");
					comboCounter = 0;
					attackMotion = LTILT.get();
				}
				else if (fw == -1)
				{
					// Back Attack
					LogUtils.getLogger().debug("Back Tilt");
					attackMotion = BTILT.get();
					comboCounter = 0;
					cercleDeFeu = 0;
				}
				else if (fw == 1)
				{
					//Forward Tilt
					executer.getSkill(this).getDataManager().setData(DatakeyRegistry.FTILT.get(), true);
					LogUtils.getLogger().debug("Forward Tilt");
					if (cercleDeFeu == 0)
					{
						attackMotion = FTILT.get();
						cercleDeFeu = 1;
					}
					else if (cercleDeFeu == 1)
					{
						attackMotion = CERCLE_DE_FEU.get();
						cercleDeFeu = 0;
					}
					comboCounter = 0;
				}
				else if (player.isShiftKeyDown())
				{
					//Down Tilt
					LogUtils.getLogger().debug("Down Tilt");
					attackMotion = CROUCH_LIGHT.get();
					comboCounter = 0;
					cercleDeFeu = 0;
				}
				else
				{
					// Normal Attack
					executer.getSkill(this).getDataManager().setData(DatakeyRegistry.JAB.get(), true);
					comboSize = IMPERATRICE_SWORD_JAB_SET.size();
					comboCounter %= comboSize;
					LogUtils.getLogger().debug("Jab");
					LogUtils.getLogger().debug("Combo Counter: {}", comboCounter);
					attackMotion = IMPERATRICE_SWORD_JAB_SET.get(comboCounter).get();
					cercleDeFeu = 0;
				}
			}

			setFtiltCombo(ComboCounterHandleEvent.Causal.TIME_EXPIRED, executer, skillContainer, attackMotion, cercleDeFeu);
			setComboCounterWithEvent(ComboCounterHandleEvent.Causal.TIME_EXPIRED, executer, skillContainer, attackMotion, comboCounter);

			if (attackMotion != null)
			{
				executer.playAnimationSynchronized(attackMotion, 0);

				if (executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.HEAT.get()) && executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.HEAT.get()) < 50)
				{
					executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.HEAT.get(), executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.HEAT.get()) + 5, executer.getOriginal());
					if (executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.HEAT.get()) >= 50f)
					{
						executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.HEAT.get(), 50f, executer.getOriginal());
					}
				}
				LogUtils.getLogger().debug("Heat Level: {}", executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.HEAT.get()));
			}

			executer.updateEntityState();
		}
		else
		{
			super.executeOnServer(executer, args);
		}

	}
}
