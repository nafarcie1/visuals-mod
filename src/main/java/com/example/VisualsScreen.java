package com.example;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

public class VisualsScreen extends Screen {
    public VisualsScreen() {
        super(Text.literal("Visuals Menu"));
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;
        int y = this.height / 3;

        this.addDrawableChild(ButtonWidget.builder(
            getStatus("Trails", VisualsMod.trails),
            btn -> {
                VisualsMod.trails = !VisualsMod.trails;
                btn.setMessage(getStatus("Trails", VisualsMod.trails));
            }
        ).dimensions(x, y, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            getStatus("China Hat", VisualsMod.chinaHat),
            btn -> {
                VisualsMod.chinaHat = !VisualsMod.chinaHat;
                btn.setMessage(getStatus("China Hat", VisualsMod.chinaHat));
            }
        ).dimensions(x, y + 26, 200, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            getStatus("Target HUD", VisualsMod.targetHud),
            btn -> {
                VisualsMod.targetHud = !VisualsMod.targetHud;
                btn.setMessage(getStatus("Target HUD", VisualsMod.targetHud));
            }
        ).dimensions(x, y + 52, 200, 20).build());
    }

    private Text getStatus(String name, boolean val) {
        return Text.literal(name + ": " + (val ? "§aWŁĄCZONE" : "§cWYŁĄCZONE"));
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 30, 0xFFFFFF);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }
}