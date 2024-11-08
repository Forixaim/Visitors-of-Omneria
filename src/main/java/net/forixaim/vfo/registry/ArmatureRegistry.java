package net.forixaim.vfo.registry;

import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.world.entity.charlemagne.model.CharlemagneArmature;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.ModelBuildEvent;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.model.armature.HumanoidArmature;

@Mod.EventBusSubscriber(modid = VisitorsOfOmneria.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ArmatureRegistry
{
	public static CharlemagneArmature CHARLEMAGNE;

	@SubscribeEvent
	public static void RegisterArmature(ModelBuildEvent.ArmatureBuild event)
	{
		CHARLEMAGNE = event.get(VisitorsOfOmneria.MOD_ID, "entity/charlemagne", CharlemagneArmature::new);
		Armatures.registerEntityTypeArmature(EntityRegistry.CHARLEMAGNE.get(), CHARLEMAGNE);
	}
}
