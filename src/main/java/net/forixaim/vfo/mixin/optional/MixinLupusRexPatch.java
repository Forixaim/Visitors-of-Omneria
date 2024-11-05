package net.forixaim.vfo.mixin.optional;

import net.forixaim.vfo.combat.VFOExtDamageTags;
import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import reascer.wom.world.entity.mobpatch.LupusRexPatch;
import yesman.epicfight.api.utils.AttackResult;
import yesman.epicfight.world.damagesource.EpicFightDamageType;

@Mixin(value = LupusRexPatch.class)
public class MixinLupusRexPatch
{
	@Inject(method = "tryHurt", at = @At("RETURN"), cancellable = true, remap = false)
	private void tryHurt(DamageSource source, float amount, CallbackInfoReturnable<AttackResult> ci)
	{
		if (source.is(VFOExtDamageTags.ULTIMATE_DAMAGE) || source.is(EpicFightDamageType.BYPASS_DODGE))
		{
			ci.setReturnValue(AttackResult.success(amount));
		}
	}
}
