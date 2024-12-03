package net.forixaim.vfo.skill.battle_style.imperatrice_lumiere;


import com.brandon3055.draconicevolution.DraconicEvolution;
import com.brandon3055.draconicevolution.items.equipment.ModularChestpiece;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.mojang.logging.LogUtils;
import mekanism.common.Mekanism;
import mekanism.common.item.gear.ItemMekaSuitArmor;
import moze_intel.projecte.api.ProjectEAPI;
import moze_intel.projecte.gameObjs.items.armor.DMArmor;
import moze_intel.projecte.gameObjs.items.armor.GemArmorBase;
import moze_intel.projecte.gameObjs.items.armor.RMArmor;
import net.forixaim.bs_api.battle_arts_skills.battle_style.BattleStyle;
import net.forixaim.vfo.Config;
import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAerialAttacks;
import net.forixaim.vfo.animations.battle_style.imperatrice_lumiere.sword.LumiereSwordAnims;
import net.forixaim.vfo.capabilities.styles.LumiereStyles;
import net.forixaim.vfo.capabilities.weapons.OmneriaCategories;
import net.forixaim.vfo.registry.ItemRegistry;
import net.forixaim.vfo.registry.SoundRegistry;
import net.forixaim.vfo.skill.DatakeyRegistry;
import net.forixaim.vfo.skill.OmneriaSkills;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.ModList;
import yesman.epicfight.client.gui.BattleModeGui;
import yesman.epicfight.network.EpicFightNetworkManager;
import yesman.epicfight.network.server.SPChangeSkill;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;
import yesman.epicfight.world.entity.eventlistener.DealtDamageEvent;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

public class ImperatriceLumiere extends BattleStyle
{
	private int tick = 0;
	private int auraTick = 0;
	private static final UUID EVENT_UUID = UUID.fromString("fceabee5-64fc-40dd-a7a2-4470ed8ff00a");


	@Override
	public void onInitiate(SkillContainer container)
	{
		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.ATTACK_ANIMATION_END_EVENT, EVENT_UUID, event ->
		{
			if (event.getPlayerPatch().getSkill(SkillSlots.BASIC_ATTACK).hasSkill(OmneriaSkills.FLARE_BLITZ))
			{
				if (event.getPlayerPatch().getSkill(SkillSlots.BASIC_ATTACK).getDataManager().getDataValue(DatakeyRegistry.JAB.get()))
				{
					event.getPlayerPatch().getSkill(SkillSlots.BASIC_ATTACK).getDataManager().setData(DatakeyRegistry.JAB.get(), false);
					event.getPlayerPatch().getSkill(SkillSlots.BASIC_ATTACK).getDataManager().setData(DatakeyRegistry.BLAZE_COMBO.get(), 0);
				}
				if (event.getPlayerPatch().getSkill(SkillSlots.BASIC_ATTACK).getDataManager().getDataValue(DatakeyRegistry.FTILT.get()))
				{
					event.getPlayerPatch().getSkill(SkillSlots.BASIC_ATTACK).getDataManager().setData(DatakeyRegistry.FTILT.get(), false);
					event.getPlayerPatch().getSkill(SkillSlots.BASIC_ATTACK).getDataManager().setData(DatakeyRegistry.CERCLE_DE_FLAMME.get(), 0);
				}
			}
		});

		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_ATTACK, EVENT_UUID, event ->
		{
			if (event.getPlayerPatch().getOriginal().getItemInHand(InteractionHand.MAIN_HAND).is(ItemRegistry.ORIGIN_JOYEUSE.get()))
			{
				event.getDamageSource().addRuntimeTag(DamageTypeTags.BYPASSES_INVULNERABILITY);
				event.getDamageSource().addRuntimeTag(DamageTypeTags.BYPASSES_ENCHANTMENTS);
			}
			if (Config.triggerAntiCheese)
			{
				boolean cheeseFound = false;
				if (event.getTarget() instanceof Player player && !player.isCreative())
				{
					cheeseFound = isCheeseFound(event, cheeseFound);
					if (cheeseFound)
					{
						EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class).playSound(SoundEvents.ITEM_BREAK, 1f, 0, 0);
						EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class).playSound(SoundRegistry.CHEESE.get(), 0, 0);
						EpicFightCapabilities.getEntityPatch(player, PlayerPatch.class).playSound(SoundRegistry.IMPERATRICE_ANTI_CHEESE.get(), 0, 0);
					}
				}
				else if (!(event.getTarget() instanceof Player))
				{
					cheeseFound = isCheeseFound(event, cheeseFound);
					if (cheeseFound)
					{
						EpicFightCapabilities.getEntityPatch(event.getTarget(), LivingEntityPatch.class).playSound(SoundEvents.ITEM_BREAK, 1f, 0, 0);
						EpicFightCapabilities.getEntityPatch(event.getTarget(), LivingEntityPatch.class).playSound(SoundRegistry.CHEESE.get(), 0, 0);
						EpicFightCapabilities.getEntityPatch(event.getTarget(), LivingEntityPatch.class).playSound(SoundRegistry.IMPERATRICE_ANTI_CHEESE.get(), 0, 0);
					}
				}
			}
		});

		container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID,
				event ->
				{
					if (event.getDamageSource().getAnimation() == LumiereSwordAerialAttacks.IMPERATRICE_SWORD_NEUTRAL_AERIAL || event.getDamageSource().getAnimation() == LumiereSwordAerialAttacks.IMPERATRICE_SWORD_FORWARD_AERIAL)
					{
						event.getPlayerPatch().getOriginal().addEffect(new MobEffectInstance(MobEffects.LEVITATION, 5, 4, true, false, false));
						event.getPlayerPatch().getOriginal().addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 5, 20, true, false, false));
					}
					if (event.getDamageSource().getAnimation() == LumiereSwordAerialAttacks.IMPERATRICE_SWORD_FORWARD_AERIAL)
					{
						event.getPlayerPatch().playSound(SoundRegistry.SPIKE.get(), 0, 0);
					}

					if (container.getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get()))
					{
						event.getDamageSource().addExtraDamage(new ExtraDamageInstance(new ExtraDamageInstance.ExtraDamage(
								((a, b, c, d, e) -> 3),
								(a, b, c, d) -> {}
						)));
					}
				});
		super.onInitiate(container);
	}

	@Override
	public boolean canExecute(PlayerPatch<?> executor)
	{
		return super.canExecute(executor);
	}

	@Override
	public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args)
	{
		if (!executor.getSkill(SkillSlots.BASIC_ATTACK).hasSkill(OmneriaSkills.FLARE_BLITZ))
		{
			executor.getSkillCapability().skillContainers[SkillSlots.BASIC_ATTACK.universalOrdinal()].setSkill(OmneriaSkills.FLARE_BLITZ);
			EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.BASIC_ATTACK, OmneriaSkills.FLARE_BLITZ.toString(), SPChangeSkill.State.ENABLE), executor.getOriginal());
		}
		if (!executor.getSkill(SkillSlots.AIR_ATTACK).hasSkill(OmneriaSkills.IMPERATRICE_AERIALS))
		{
			executor.getSkillCapability().skillContainers[SkillSlots.AIR_ATTACK.universalOrdinal()].setSkill(OmneriaSkills.IMPERATRICE_AERIALS);
			EpicFightNetworkManager.sendToPlayer(new SPChangeSkill(SkillSlots.AIR_ATTACK, OmneriaSkills.IMPERATRICE_AERIALS.toString(), SPChangeSkill.State.ENABLE), executor.getOriginal());
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public boolean shouldDraw(SkillContainer container)
	{
		return container.getExecuter().getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(container.getExecuter()) == LumiereStyles.IMPERATRICE_SWORD || container.getExecuter().getHoldingItemCapability(InteractionHand.MAIN_HAND).getStyle(container.getExecuter()) == LumiereStyles.FORIXAIM_SWORD;
	}

	private ResourceLocation UIFlareBurst(SkillContainer container)
	{
		if (Minecraft.getInstance().getUser().getUuid().equals("42479ed5a8f04967bfb17500577896a6"))
		{
			return container.getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get()) ? new ResourceLocation(VisitorsOfOmneria.MOD_ID, "textures/gui/heat_icon.png") : new ResourceLocation(VisitorsOfOmneria.MOD_ID, "textures/gui/heat_icon_burst.png");
		}
		else
		{
			return container.getDataManager().getDataValue(DatakeyRegistry.FLARE_BURST.get()) ? new ResourceLocation(VisitorsOfOmneria.MOD_ID, "textures/gui/heat_icon_burst.png") : new ResourceLocation(VisitorsOfOmneria.MOD_ID, "textures/gui/heat_icon.png");
		}
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void drawOnGui(BattleModeGui gui, SkillContainer container, GuiGraphics guiGraphics, float x, float y) {
		PoseStack poseStack = guiGraphics.pose();
		poseStack.pushPose();
		poseStack.translate(0, (float)gui.getSlidingProgression(), 0);
		guiGraphics.blit(UIFlareBurst(container), (int)x-4, (int)y-4, 36, 36, 0, 0, 1, 1, 1, 1);
		Float Heat = container.getDataManager().getDataValue(DatakeyRegistry.HEAT.get());
		String Heat_Level = Heat >= 1000 ? String.format("%.2f", Heat / 1000) + "K" : String.format("%.0f", Heat);
		guiGraphics.drawString(gui.font, Heat_Level, x + 4, y + 6, 16777215, true);
		poseStack.popPose();
	}

	@Override
	public void updateContainer(SkillContainer container)
	{
	}

	private boolean isCheeseFound(DealtDamageEvent.Attack event, boolean cheeseFound)
	{
		for (ItemStack item : event.getTarget().getArmorSlots())
		{
			if (ModList.get().isLoaded(Mekanism.MODID) && item.getItem() instanceof ItemMekaSuitArmor)
			{
				//Trigger Anti-Invincibility Cheese
				item.copyAndClear();
				cheeseFound = true;
			}
			if (ModList.get().isLoaded(DraconicEvolution.MODID) && item.getItem() instanceof ModularChestpiece)
			{
				//Trigger Anti-Invincibility Cheese
				item.copyAndClear();
				cheeseFound = true;
			}
			if (ModList.get().isLoaded(ProjectEAPI.PROJECTE_MODID) && (item.getItem() instanceof DMArmor || item.getItem() instanceof RMArmor || item.getItem() instanceof GemArmorBase))
			{
				//Trigger Anti-Invincibility Cheese
				item.copyAndClear();
				cheeseFound = true;
			}
		}
		return cheeseFound;
	}

	@Override
	public void onRemoved(SkillContainer container)
	{
		super.onRemoved(container);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.ACTION_EVENT_SERVER, EVENT_UUID);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.BASIC_ATTACK_EVENT, EVENT_UUID);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_ATTACK, EVENT_UUID);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.DEALT_DAMAGE_EVENT_HURT, EVENT_UUID);
		container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.ACTION_EVENT_SERVER, EVENT_UUID);
	}

	public ImperatriceLumiere(Builder<? extends Skill> builder)
	{
		super(builder);
		modifiesAttacks = true;
		weaponDrawAnimations.add(Pair.of(OmneriaCategories.ORIGIN_JOYEUSE, () -> LumiereSwordAnims.IMPERATRICE_JOYEUSE_DRAW));
		immuneDamages.add(DamageTypes.LAVA);
		immuneDamages.add(DamageTypes.FIREBALL);
		immuneDamages.add(DamageTypes.IN_FIRE);
		immuneDamages.add(DamageTypes.ON_FIRE);
		immuneDamages.add(DamageTypes.HOT_FLOOR);
		immuneDamages.add(DamageTypes.FALL);
		immuneDamages.add(DamageTypes.UNATTRIBUTED_FIREBALL);
	}
}
