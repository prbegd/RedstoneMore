package com.resm;

import com.resm.registry.AddItemGroup;
import com.resm.registry.ModBlocks;
import com.resm.registry.ModItemGroup;
import com.resm.registry.ModItems;
import net.fabricmc.api.ModInitializer;

import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedstoneMore implements ModInitializer {
    // This logger is used to write text to the console and the log file.
    // It is considered best practice to use your mod id as the logger's name.
    // That way, it's clear which mod wrote info, warnings, and errors.
    public static final String MOD_ID = "redstone_more";
    public static final Logger LOGGER = LoggerFactory.getLogger("redstone_more");

    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.
        AddItemGroup addItemGroup = new AddItemGroup();
        addItemGroup.add(ItemGroups.REDSTONE);
        LOGGER.info(String.valueOf(addItemGroup.getItemGroup(0)));
        LOGGER.info(String.valueOf(addItemGroup.getItem(0)));
        LOGGER.info(String.valueOf(addItemGroup.getAddMode(0)));
        addItemGroup.add(ItemGroups.TOOLS, Items.REDSTONE, AddItemGroup.targetMode.AFTER);
        LOGGER.info(String.valueOf(addItemGroup.getItemGroup(1)));
        LOGGER.info(String.valueOf(addItemGroup.getItem(1)));
        LOGGER.info(String.valueOf(addItemGroup.getAddMode(1)));

        LOGGER.info("["+RedstoneMore.MOD_ID+"]Load");
        LOGGER.debug("["+RedstoneMore.MOD_ID+"]Registering items, item groups, and blocks");
        ModItemGroup.registerModGroup();//注意要在注册物品前注册物品组
        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        LOGGER.debug("["+RedstoneMore.MOD_ID+"]Registration complete");


    }
}