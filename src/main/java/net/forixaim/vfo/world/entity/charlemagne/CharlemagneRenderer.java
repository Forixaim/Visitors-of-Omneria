package net.forixaim.vfo.world.entity.charlemagne;

import com.mojang.blaze3d.vertex.PoseStack;
import net.forixaim.vfo.VisitorsOfOmneria;
import net.forixaim.vfo.registry.ModelLayers;
import net.forixaim.vfo.world.entity.charlemagne.model.CharlemagneModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CharlemagneRenderer extends MobRenderer<Charlemagne, CharlemagneModel>
{

	public CharlemagneRenderer(EntityRendererProvider.Context p_174304_)
	{
		super(p_174304_, new CharlemagneModel(p_174304_.bakeLayer(ModelLayers.CHARLEMAGNE_LAYER)), 1f);
	}

	@Override
	public @NotNull ResourceLocation getTextureLocation(@NotNull Charlemagne charlemagne)
	{
		return new ResourceLocation(VisitorsOfOmneria.MOD_ID, "textures/entity/charlemagne.png");
	}

	@Override
	public void render(@NotNull Charlemagne p_115455_, float p_115456_, float p_115457_, @NotNull PoseStack p_115458_, @NotNull MultiBufferSource p_115459_, int p_115460_)
	{
		super.render(p_115455_, p_115456_, p_115457_, p_115458_, p_115459_, p_115460_);
	}
}
