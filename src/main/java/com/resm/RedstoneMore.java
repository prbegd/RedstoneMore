package com.resm;

import com.resm.registry.ModBlocks;
import com.resm.registry.ModItemGroups;
import com.resm.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedstoneMore implements ModInitializer {
    /**
     * This logger is used to write text to the console and the log file.
     * It is considered best practice to use your mod id as the logger's name.
     * That way, it's clear which mod wrote info, warnings, and errors.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger("redstone_more");
    public static final String MOD_ID = "redstone_more";

    /**
     * This code runs as soon as Minecraft is in a mod-load-ready state.
     * However, some things (like resources) may still be uninitialized.
     * Proceed with mild caution.
     */
    @Override
    public void onInitialize() {
        LOGGER.info("Load");
        //注意要在注册物品前注册物品组
        ModItemGroups.registerModItemGroup();
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
    }
}