package net.forixaim.vfo.events;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.events.advanced_bosses.DamageDealtEvent;
import net.forixaim.vfo.registry.EntityRegistry;
import net.forixaim.vfo.world.entity.charlemagne.Charlemagne;
import net.forixaim.vfo.world.entity.charlemagne.CharlemagnePatch;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.HurtableEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;
import yesman.epicfight.world.capabilities.projectile.ProjectilePatch;
import yesman.epicfight.world.damagesource.EpicFightDamageSource;
import yesman.epicfight.world.damagesource.EpicFightDamageType;
import yesman.epicfight.world.damagesource.ExtraDamageInstance;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID)
public class LivingEntityEvents
{
	@SubscribeEvent
	public static void onLivingEntityHurt(LivingHurtEvent event)
	{
		EpicFightDamageSource epicFightDamageSource = null;
		Entity trueSource = event.getSource().getEntity();

		if (trueSource != null)
		{
			LivingEntityPatch<?> attackerEntityPatch = EpicFightCapabilities.getEntityPatch(trueSource, LivingEntityPatch.class);

			if (event.getSource() instanceof EpicFightDamageSource instance)
			{
				epicFightDamageSource = instance;
			} else if (event.getSource().isIndirect() && event.getSource().getDirectEntity() != null)
			{
				ProjectilePatch<?> projectileCap = EpicFightCapabilities.getEntityPatch(event.getSource().getDirectEntity(), ProjectilePatch.class);

				if (projectileCap != null)
				{
					epicFightDamageSource = projectileCap.getEpicFightDamageSource(event.getSource());
				}
			} else if (attackerEntityPatch != null)
			{
				epicFightDamageSource = attackerEntityPatch.getEpicFightDamageSource();
			}

			if (epicFightDamageSource != null && !epicFightDamageSource.is(EpicFightDamageType.PARTIAL_DAMAGE))
			{
				LivingEntity hitEntity = event.getEntity();

				if (attackerEntityPatch instanceof CharlemagnePatch charlemagnePatch)
				{
					DamageDealtEvent DamageDealtEvent = new DamageDealtEvent(charlemagnePatch, hitEntity, epicFightDamageSource, event);
					charlemagnePatch.fireDamageDealtEvent(DamageDealtEvent);
				}
			}
		}
	}
}
