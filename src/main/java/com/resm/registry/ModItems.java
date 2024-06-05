package com.resm.registry;

import com.resm.registry.items.LEDItems;
import com.resm.registry.items.tools.DebugToolItem;
import com.resm.registry.items.tools.MobKillerItem;
import com.resm.registry.items.tools.SpannerItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;

import static com.resm.registry.AddItemGroup.*;

public class ModItems {
    public static final MobKillerItem MOB_KILLER = registerItem("mob_killer", new MobKillerItem(new FabricItemSettings().maxCount(1)), new AddItemGroup(2).add(ModItemGroups.MORE_REDSTONE).add(ItemGroups.OPERATOR));
    public static final Item SILVER_DUST = registerItem("silver_dust", new AliasedBlockItem(ModBlocks.SILVER_WIRE, new FabricItemSettings()), new AddItemGroup(2).addAfter(ItemGroups.REDSTONE, Items.REDSTONE).add(ModItemGroups.MORE_REDSTONE));
    public static final Item RAW_SILVER = registerItem("raw_silver", new Item(new FabricItemSettings()), new AddItemGroup(1).addAfter(ItemGroups.INGREDIENTS, Items.RAW_GOLD));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new FabricItemSettings()), new AddItemGroup(1).addAfter(ItemGroups.INGREDIENTS, Items.GOLD_INGOT));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new FabricItemSettings()), new AddItemGroup(1).addAfter(ItemGroups.INGREDIENTS, Items.GOLD_NUGGET));
    public static final SpannerItem SPANNER = registerItem("spanner", new SpannerItem(new FabricItemSettings().maxCount(1)), new AddItemGroup(2).add(ModItemGroups.MORE_REDSTONE).add(ItemGroups.TOOLS));
    public static final DebugToolItem DEBUG_TOOL = registerItem("debug_tool", new DebugToolItem(new FabricItemSettings().maxCount(1)), new AddItemGroup(2).add(ModItemGroups.MORE_REDSTONE).add(ItemGroups.OPERATOR));

    public static void registerModItems() {
        LEDItems.registerLEDItems();
    }
}
