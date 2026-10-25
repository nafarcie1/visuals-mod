package com.example;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

public class ChinaHatRenderer extends FeatureRenderer<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> {
    public ChinaHatRenderer(FeatureRendererContext<AbstractClientPlayerEntity, PlayerEntityModel<AbstractClientPlayerEntity>> context) {
        super(context);
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, AbstractClientPlayerEntity player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
        if (!VisualsMod.chinaHat || player.isInvisible()) return;

        matrices.push();
        this.getContextModel().getHead().rotate(matrices);
        matrices.translate(0.0, -0.65, 0.0);

        Matrix4f matrix = matrices.peek().getPositionMatrix();
        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getLightning());

        int segments = 24;
        float radius = 0.6f;
        float height = 0.25f;

        for (int i = 0; i < segments; i++) {
            float a1 = (float) (i * 2 * Math.PI / segments);
            float a2 = (float) ((i + 1) * 2 * Math.PI / segments);

            float x1 = (float) Math.cos(a1) * radius;
            float z1 = (float) Math.sin(a1) * radius;
            float x2 = (float) Math.cos(a2) * radius;
            float z2 = (float) Math.sin(a2) * radius;

            buffer.vertex(matrix, 0.0f, -height, 0.0f).color(1.0f, 0.2f, 0.5f, 0.8f);
            buffer.vertex(matrix, x1, 0.0f, z1).color(1.0f, 0.2f, 0.5f, 0.3f);
            buffer.vertex(matrix, x2, 0.0f, z2).color(1.0f, 0.2f, 0.5f, 0.3f);
        }

        matrices.pop();
    }
}