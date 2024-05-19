package com.resm.registry.screens;


import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.font.MultilineText;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ModConfigScreen extends Screen {
    private final Screen parent;

    public ModConfigScreen(Screen parent) {
        super(Text.translatable("screen.redstone_more.mod_config_screen"));
        this.parent = parent;
    }

    @Override
    public void close() {
        client.setScreen(parent);
    }

    public ButtonWidget testButton1;
    public ButtonWidget testButton2;

    @Override
    protected void init() {
        testButton1 = ButtonWidget.builder(Text.literal("按钮 1"), button -> System.out.println("你点击了按钮 1！")).dimensions(width / 2 - 205, 20, 200, 20).tooltip(Tooltip.of(Text.literal("按钮 1 的提示"))).build();
        testButton2 = ButtonWidget.builder(Text.literal("按钮 2"), button -> System.out.println("你点击了按钮 2！")).dimensions(width / 2 + 5, 20, 200, 20).tooltip(Tooltip.of(Text.literal("按钮 2 的提示"))).build();

        addDrawableChild(testButton1);
        addDrawableChild(testButton2);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        final MultilineText multilineText = MultilineText.create(textRenderer, Text.literal("X:" + mouseX + ",Y:" + mouseY));
        multilineText.drawWithShadow(context, 10, height / 2, 16, 0xffffff);
    }
}
