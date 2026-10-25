package com.example;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;

public class TargetHud implements HudRenderCallback {
    @Override
    public void onHudRender(DrawContext context, RenderTickCounter tickCounter) {
        if (!VisualsMod.targetHud) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.crosshairTarget == null) return;

        if (client.crosshairTarget instanceof EntityHitResult eHit && eHit.getEntity() instanceof LivingEntity target) {
            int x = (client.getWindow().getScaledWidth() / 2) + 15;
            int y = (client.getWindow().getScaledHeight() / 2) + 15;
            int width = 120;
            int height = 36;

            context.fill(x, y, x + width, y + height, 0x90000000);
            context.drawBorder(x, y, width, height, 0xFF444444);
            context.drawTextWithShadow(client.textRenderer, target.getName().getString(), x + 5, y + 5, 0xFFFFFF);

            float hpPct = Math.max(0.0f, Math.min(target.getHealth() / target.getMaxHealth(), 1.0f));
            context.fill(x + 5, y + 20, x + width - 5, y + 28, 0xFF330000);
            context.fill(x + 5, y + 20, x + 5 + (int)((width - 10) * hpPct), y + 28, 0xFF00FF88);
        }
    }
}