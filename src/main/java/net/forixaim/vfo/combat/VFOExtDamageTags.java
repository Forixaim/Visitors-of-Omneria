package net.forixaim.vfo.combat;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

public class VFOExtDamageTags
{
	public static final TagKey<DamageType> ULTIMATE_DAMAGE = create("ultimate_damage");
	public static final TagKey<DamageType> BURST_DAMAGE = create("burst_damage");

	private static TagKey<DamageType> create(final String pName)
	{
		return TagKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(VisitorsOfOmneria.MOD_ID, pName));
	}
}
