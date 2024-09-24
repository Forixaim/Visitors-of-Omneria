package net.forixaim.vfo.item.weapons.legendary.imperatrice_lumiere;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.item.OmneriaRarities;
import net.forixaim.vfo.item.VisitorsOfOmneriaTiers;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.item.WeaponItem;

import javax.annotation.Nullable;
import java.util.List;

public class OriginJoyeuse extends WeaponItem
{
	public OriginJoyeuse()
	{
		super(VisitorsOfOmneriaTiers.ORIGIN_JOYEUSE, 5, -3f, new Properties().durability(0).defaultDurability(0).rarity(OmneriaRarities.IMPERATRICE_LUMIERE).fireResistant());
	}

	@Override
	public void appendHoverText(@NotNull ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, @NotNull TooltipFlag flagIn) {
		tooltip.add(Component.literal(""));
		tooltip.add(Component.translatable("item." + VisitorsOfOmneria.MOD_ID + ".origin_joyeuse.tooltip").withStyle(ChatFormatting.DARK_RED));
	}
}
