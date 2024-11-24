package net.forixaim.vfo.mixin;

import net.forixaim.vfo.registry.MeshRegistry;
import net.forixaim.vfo.special.SpecialPlayers;
import net.minecraft.client.player.AbstractClientPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.client.model.MeshProvider;
import yesman.epicfight.client.mesh.HumanoidMesh;
import yesman.epicfight.client.renderer.patched.entity.PPlayerRenderer;
import yesman.epicfight.client.world.capabilites.entitypatch.player.AbstractClientPlayerPatch;

@Mixin(value = PPlayerRenderer.class)
public abstract class MixinPPlayerRenderer
{
	@Inject(method = "getMeshProvider(Lyesman/epicfight/client/world/capabilites/entitypatch/player/AbstractClientPlayerPatch;)Lyesman/epicfight/api/client/model/MeshProvider;", at = @At("RETURN"), remap = false, cancellable = true)
	private void injectCharlemagne(AbstractClientPlayerPatch<AbstractClientPlayer> ep, CallbackInfoReturnable<MeshProvider<HumanoidMesh>> cir)
	{
		if (ep.getOriginal().getUUID().equals(SpecialPlayers.FORIXAIM))
		{
			cir.setReturnValue(() -> MeshRegistry.CHARLEMAGNE);
		}
	}
}
