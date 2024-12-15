package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active;

import com.google.common.collect.Lists;
import com.mojang.logging.LogUtils;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.bs_api.battle_arts_skills.active.combat_arts.CombatArt;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordSpecialArts;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.ArgumentGatherers;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.InteractionHand;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.client.world.capabilites.entitypatch.player.LocalPlayerPatch;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.List;
import java.util.UUID;

public class ImperatriceSpecials extends CombatArt
{

	private static final UUID EVENT_UUID = UUID.fromString("a4deb3a3-2eb2-4e3b-8204-265e95cc4eaf");
	private static final AnimationProvider<AttackAnimation> provider = () -> (AttackAnimation) LumiereSwordSpecialArts.IMPERATRICE_INCANDESCENT_FIREWORK.get();
	private static final AnimationProvider<AttackAnimation> INFERNO_ASTROLABE = () -> (AttackAnimation) LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNO_ASTROLABE_START;
	private static final AnimationProvider<?> INFERNO_ASTROLABE_MISS = () -> LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNO_ASTROLABE_MISS;
	private static final AnimationProvider<?> SLASHES = () -> LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNAL_ASTROLABE_SLASHES;

	private static final List<?> SPECIAL_ENDERS = Lists.newArrayList(
			LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNO_ASTROLABE_MISS
	);

	public ImperatriceSpecials(Builder<? extends Skill> builder)
	{
		super(builder);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public FriendlyByteBuf gatherArguments(LocalPlayerPatch executer, ControllEngine controllEngine)
	{
		return ArgumentGatherers.UniversalDirectionalInput(executer, controllEngine);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public Object getExecutionPacket(LocalPlayerPatch executer, FriendlyByteBuf args)
	{
		return ArgumentGatherers.DirectionalExecutionPacket(executer, args, this);
	}

	@Override
	public void onInitiate(SkillContainer container)
	{
		SkillDataManager dataManager = container.getDataManager();
		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, event ->
		{
			if (event.getAnimation().get() == LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNO_ASTROLABE_MISS || event.getAnimation().get() == LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNAL_ASTROLABE_SLASHES)
			{
				container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.SPECIAL_EXECUTING.get(), false, event.getPlayerPatch().getOriginal());
			}
			if (dataManager.getDataValue(DatakeyRegistry.ASTROLABE_EXECUTE.get()) && event.getAnimation() == LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNO_ASTROLABE_START && !dataManager.getDataValue(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get()))
			{
				dataManager.setDataSync(DatakeyRegistry.ASTROLABE_EXECUTE.get(), false, event.getPlayerPatch().getOriginal());
				event.getPlayerPatch().playAnimationSynchronized(INFERNO_ASTROLABE_MISS.get(), 0);
			}
			if (dataManager.getDataValue(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get()) && event.getAnimation() == SLASHES.get())
			{
				dataManager.setDataSync(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get(), false, event.getPlayerPatch().getOriginal());
			}
		});

		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID, hurt ->
		{
			if (!dataManager.getDataValue(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get()) && hurt.getDamageSource().getAnimation() == LumiereSwordSpecialArts.IMPERATRICE_SWORD_INFERNO_ASTROLABE_START)
			{
				dataManager.setDataSync(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get(), true, hurt.getPlayerPatch().getOriginal());
				executeOnServer(hurt.getPlayerPatch(), null);
			}
		});
		super.onInitiate(container);
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID);
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executer)
	{

		return executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE) &&
				!executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.SPECIAL_EXECUTING.get()) &&
				executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().hasData(DatakeyRegistry.CHARGE_EXECUTING.get()) && !executer.getSkill(SkillSlots.WEAPON_INNATE).getDataManager().getDataValue(DatakeyRegistry.CHARGE_EXECUTING.get()) &&
				!executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get());
	}

	@Override
	public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args)
	{
		LogUtils.getLogger().info("canExecute");
		int forwardBack = -2;
		int leftRight = -2;

		if (args != null)
		{
			forwardBack = args.readInt();
			leftRight = args.readInt();
		}

		if (executor.getSkill(this).getDataManager().getDataValue(DatakeyRegistry.ULTIMATE_ART_TRY_CONNECTED.get()))
		{
			executor.playAnimationSynchronized(SLASHES.get(), 0);
		}

		executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.SPECIAL_EXECUTING.get(), true, executor.getOriginal());

		if (forwardBack == 1 && (executor.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executor) == LumiereStyles.IMPERATRICE_SWORD || executor.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executor) == LumiereStyles.FORIXAIM_SWORD))
		{
			executor.getSkill(this).getDataManager().setDataSync(DatakeyRegistry.ASTROLABE_EXECUTE.get(), true, executor.getOriginal());
			executor.playAnimationSynchronized(INFERNO_ASTROLABE.get(), 0);
		}
	}
}
