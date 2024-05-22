package com.resm.registry;

import com.resm.registry.items.LEDItems;
import com.resm.registry.items.tools.MobKillerItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

public class ModItems {
    public static final Item MOB_KILLER = AddItemGroup.registerItem("mob_killer", new MobKillerItem(new FabricItemSettings()), new AddItemGroup(2).add(ModItemGroups.MORE_REDSTONE).add(ItemGroups.OPERATOR));
    public static final Item SILVER_DUST = AddItemGroup.registerItem("silver_dust", new AliasedBlockItem(ModBlocks.SILVER_WIRE, new FabricItemSettings()), new AddItemGroup(2).add(ItemGroups.REDSTONE, Items.REDSTONE, AddItemGroup.TargetModeEnum.AFTER).add(ModItemGroups.MORE_REDSTONE));
    public static final Item RAW_SILVER = AddItemGroup.registerItem("raw_silver", new Item(new FabricItemSettings()), new AddItemGroup(1).add(ItemGroups.INGREDIENTS, Items.RAW_GOLD, AddItemGroup.TargetModeEnum.AFTER));
    public static final Item SILVER_INGOT = AddItemGroup.registerItem("silver_ingot", new Item(new FabricItemSettings()), new AddItemGroup(1).add(ItemGroups.INGREDIENTS, Items.GOLD_INGOT, AddItemGroup.TargetModeEnum.AFTER));
    public static final Item SILVER_NUGGET = AddItemGroup.registerItem("silver_nugget", new Item(new FabricItemSettings()), new AddItemGroup(1).add(ItemGroups.INGREDIENTS, Items.GOLD_NUGGET, AddItemGroup.TargetModeEnum.AFTER));
    public static final Item SPANNER = AddItemGroup.registerItem("spanner", new Item(new FabricItemSettings().maxCount(1)), new AddItemGroup(2).add(ModItemGroups.MORE_REDSTONE).add(ItemGroups.TOOLS));

    public static void registerModItems() {
        LEDItems.registerLEDItems();
    }
}
