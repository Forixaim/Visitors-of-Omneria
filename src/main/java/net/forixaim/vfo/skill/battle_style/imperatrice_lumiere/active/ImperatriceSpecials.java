package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active;

import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.bs_api.battle_arts_skills.active.combat_arts.CombatArt;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.SpecialArts;
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
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

public class ImperatriceSpecials extends CombatArt
{

	private static final UUID EVENT_UUID = UUID.fromString("a4deb3a3-2eb2-4e3b-8204-265e95cc4eaf");
	private static final AnimationProvider<AttackAnimation> provider = () -> (AttackAnimation) SpecialArts.IMPERATRICE_INCANDESCENT_FIREWORK.get();
	private static final AnimationProvider<AttackAnimation> INFERNO_ASTROLABE = () -> (AttackAnimation) SpecialArts.IMPERATRICE_SWORD_INFERNO_ASTROLABE;

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
		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, event ->
		{
			if (event.getAnimation().equals(provider.get()) || event.getAnimation().equals(INFERNO_ASTROLABE.get()))
			{
				event.getPlayerPatch().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.SPECIAL_EXECUTING.get(), false, event.getPlayerPatch().getOriginal());
			}
		});
		super.onInitiate(container);
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID);
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
		int forwardBack = args.readInt();
		int leftRight = args.readInt();

		executor.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.SPECIAL_EXECUTING.get(), true, executor.getOriginal());

		if (forwardBack == 1 && (executor.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executor) == LumiereStyles.IMPERATRICE_SWORD || executor.getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(executor) == LumiereStyles.FORIXAIM_SWORD))
		{
			executor.playAnimationSynchronized(INFERNO_ASTROLABE.get(), 0);
		}
		else
		{
			executor.playAnimationSynchronized(provider.get(), 0);
		}
	}
}
