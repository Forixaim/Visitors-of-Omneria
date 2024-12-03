package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere.active;

import com.mojang.blaze3d.vertex.PoseStack;
import net.forixaim.bs_api.battle_arts_skills.BattleArtsSkillSlots;
import net.forixaim.bs_api.battle_arts_skills.active.burst_arts.BurstArt;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordSpecialArts;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.registry.SoundRegistry;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.AnimationProvider;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;
import yesman.epicfight.world.entity.eventlistener.SkillExecuteEvent;

import java.util.UUID;
import java.util.function.Consumer;

/**
 * Skill Description: Determination increases with the amount of damage dealt, decreases with damage taken. When activating, blows back every opponent in a 3 block radius and go into a defensive stance. While in this stance, as long as you are landing hits, damage will be reduced by 1/4 or nullified when the attacker is in front of you, and any attacker will be set on fire. You also attack faster. (25% efficiency when landing hits while skill is active)
 */
public class BlazingDetermination extends BurstArt
{
	private static final UUID EVENT_UUID = UUID.fromString("1f198bcc-af51-45ae-a5f0-78496ec47408");
	private static final AnimationProvider<AttackAnimation> FLARE_BURST_ACTIVATE = () -> (AttackAnimation) LumiereSwordSpecialArts.IMPERATRICE_SWORD_FLARE_BURST;

	private final Consumer<SkillExecuteEvent> skillExecuteEvent = event ->
	{
	};

	public BlazingDetermination(Builder<? extends Skill> builder)
	{
		super(builder);
	}

	@Override
	public void onInitiate(SkillContainer container)
	{
		super.onInitiate(container);
		container.setResource(container.getMaxResource());
		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID, event ->
		{

		});
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executer)
	{
		return executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).hasSkill(OmneriaSkills.IMPERATRICE_LUMIERE) &&
				executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) &&
				!(executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get())) &&
				!executer.getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.ULTIMATE_ART_ACTIVE.get());
	}

	@Override
	public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args)
	{
		executor.playSound(SoundRegistry.BURST_ART_READY.get(), 0, 0);
		executor.playAnimationSynchronized(FLARE_BURST_ACTIVATE.get(), 0.0f);
		super.executeOnServer(executor, args);
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean shouldDraw(SkillContainer container)
	{
		return (container.getExecuter().getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(container.getExecuter()) == LumiereStyles.IMPERATRICE_SWORD || container.getExecuter().getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(container.getExecuter()) == LumiereStyles.FORIXAIM_SWORD) && container.getResource() > 0 && container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) && !container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get());
	}


	@OnlyIn(Dist.CLIENT)
	@Override
	public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics guiGraphics, float x, float y) {
		PoseStack poseStack = guiGraphics.pose();
		poseStack.pushPose();
		poseStack.translate(0, (float)gui.getSlidingProgression(), 0);
		guiGraphics.blit(this.getSkillTexture(), (int)x-4, (int)y-4, 36, 36, 0, 0, 1, 1, 1, 1);
		float CD = container.getResource();
		String Cooldown_Time = (container.getMaxResource() - CD) > 100 ? String.format("%.0f", container.getMaxResource() - CD) : String.format("%.1f", container.getMaxResource() - CD);
		guiGraphics.drawString(gui.font, Cooldown_Time, x + 4, y + 6, 16777215, true);
		poseStack.popPose();
	}

	@Override
	public void updateContainer(SkillContainer container)
	{
		super.updateContainer(container);
		if (container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.FLARE_BURST.get()) && container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get()))
		{
			container.setResource(0);
		}
		if (container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().hasData(DatakeyRegistry.HEAT.get()) && !(container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.HEAT.get()) >= 200) && container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get()))
		{
			if (!container.getExecuter().isLogicalClient())
			{
				container.getExecuter().getSkill(BattleArtsSkillSlots.BATTLE_STYLE).getDataManager().setDataSync(DatakeyRegistry.FLARE_BURST.get(), false, (ServerPlayer) container.getExecuter().getOriginal());
			}

		}
	}

	@Override
	public void onReset(SkillContainer container)
	{
		super.onReset(container);
	}

	@Override
	public float getCooldownRegenPerSecond(PlayerPatch<?> player)
	{
		float multiplier = 1;
		if (player.getOriginal().isOnFire())
		{
			multiplier+=.5f;
		}
		else if (player.getOriginal().isInLava())
		{
			multiplier+=1f;
		}
		if (player.getOriginal().level().dimension() == Level.NETHER)
		{
			multiplier+=.5f;
		}
		return 1 * multiplier;
	}
}
