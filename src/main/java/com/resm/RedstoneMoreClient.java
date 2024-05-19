package com.resm;

import com.resm.registry.ModBlocks;
import com.resm.registry.screens.ModConfigScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;

public class RedstoneMoreClient implements ClientModInitializer {
    private static KeyBinding configKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.redstone_more.config_key",
            InputUtil.Type.KEYSYM,
            -1,
            "category.redstone_more.resm_key"
    ));
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (configKeyBinding.wasPressed()) {
                client.setScreen(new ModConfigScreen(client.currentScreen));
            }
        });
    }
}
