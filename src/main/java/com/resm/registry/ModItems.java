package com.resm.registry;

import com.resm.RedstoneMore;
import com.resm.registry.items.LEDItems;
import com.resm.registry.items.MobKillerItem;
import com.resm.registry.items.SpannerItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModItems {
    //public static final Item EXAMPLE_ITEM = registerItem("example_item", new
    // Item(new FabricItemSettings()), ModItemGroup.MORE_REDSTONE);

    public static final Item MOB_KILLER = registerItem("mob_killer", new MobKillerItem(
            new Item.Settings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE).add(ItemGroups.OPERATOR));
    public static final Item SILVER_DUST = registerItem("silver_dust", new
            AliasedBlockItem(ModBlocks.SILVER_WIRE, new FabricItemSettings()), new
            AddItemGroup().add(ItemGroups.REDSTONE, Items.REDSTONE,
                    AddItemGroup.targetMode.AFTER).add(ModItemGroup.MORE_REDSTONE));
    public static final Item RAW_SILVER = registerItem("raw_silver", new
            Item(new FabricItemSettings()), new AddItemGroup()
            .add(ItemGroups.INGREDIENTS, Items.RAW_GOLD, AddItemGroup.targetMode.AFTER));
    public static final Item SILVER_INGOT = registerItem("silver_ingot", new
            Item(new FabricItemSettings()), new AddItemGroup()
            .add(ItemGroups.INGREDIENTS, Items.GOLD_INGOT, AddItemGroup.targetMode.AFTER));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new
            Item(new FabricItemSettings()), new AddItemGroup()
            .add(ItemGroups.INGREDIENTS, Items.GOLD_NUGGET, AddItemGroup.targetMode.AFTER));
    public static final Item SPANNER = registerItem("spanner", new
            SpannerItem(new FabricItemSettings()),  new AddItemGroup()
            .add(ModItemGroup.MORE_REDSTONE).add(ItemGroups.TOOLS));
    //-----------------------------------------------------------------------------

    public static Item registerItem(String name, Item item, AddItemGroup itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        for (int i = 0; i < itemGroups.getItemGroupsCount(); i++) {
            int I = i;
            if (itemGroups.getAddMode(i) == AddItemGroup.targetMode.END) {
                ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i)).register(entries -> entries.add(registeredItem));
            }else if (itemGroups.getAddMode(i) == AddItemGroup.targetMode.AFTER) {
                ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i)).register(entries -> entries.addAfter(itemGroups.getItem(I), registeredItem));
            }else {
                ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i)).register(entries -> entries.addBefore(itemGroups.getItem(I), registeredItem));
            }

        }
        return registeredItem;
    }
    public static void registerModItems() {
        LEDItems.registerLEDItems();
    }
}
