package com.billythenarwhal.satin;

import com.billythenarwhal.IsoMod;
import com.billythenarwhal.IsoModClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.WitherEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.boss.WitherEntity;
import org.jetbrains.annotations.Nullable;

public class RainbowWitherEntityRenderer extends WitherEntityRenderer {
     public RainbowWitherEntityRenderer(EntityRendererFactory.Context entityRenderDispatcher) {
        super(entityRenderDispatcher);
    }

    @Override
    public void render(WitherEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
//        SatinRenderLayer.rainbowProjMat.set(matrixStack.peek().getModel());
        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    @Nullable
    @Override
    protected RenderLayer getRenderLayer(WitherEntity entity, boolean showBody, boolean translucent, boolean glowing) {
        RenderLayer baseLayer = super.getRenderLayer(entity, showBody, translucent, glowing);
        return baseLayer == null ? null : IsoModClient.testShader2.getRenderLayer(baseLayer);
    }
}
