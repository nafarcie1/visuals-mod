package com.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.EntityType;
import org.lwjgl.glfw.GLFW;

public class VisualsMod implements ClientModInitializer {
    public static boolean trails = true;
    public static boolean chinaHat = true;
    public static boolean targetHud = true;
    private static KeyBinding menuKey;

    @Override
    public void onInitializeClient() {
        menuKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "Visuals Menu",
            InputUtil.Type.KEYSYM,
            GLFW.GLFW_KEY_N,
            "Visuals"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (menuKey.wasPressed()) {
                if (client.currentScreen == null) {
                    client.setScreen(new VisualsScreen());
                }
            }

            if (trails && client.player != null && client.player.getVelocity().lengthSquared() > 0.005) {
                client.particleManager.addParticle(
                    net.minecraft.particle.ParticleTypes.END_ROD,
                    client.player.getX(), client.player.getY() + 0.1, client.player.getZ(),
                    0.0, 0.01, 0.0
                );
            }
        });

        LivingEntityFeatureRendererRegistrationCallback.EVENT.register((entityType, entityRenderer, registrationHelper, context) -> {
            if (entityType == EntityType.PLAYER && entityRenderer instanceof PlayerEntityRenderer playerRenderer) {
                registrationHelper.register(new ChinaHatRenderer(playerRenderer));
            }
        });

        HudRenderCallback.EVENT.register(new TargetHud());
    }
}