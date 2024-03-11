package com.resm.registry;

import com.resm.RedstoneMore;
import com.resm.registry.items.LEDItems;
import com.resm.registry.items.tools.CreativeToolMaterials;
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

    public static final Item MOB_KILLER = registerItem("mob_killer", new SwordItem(CreativeToolMaterials.CREATIVETOOLMATERIALS,
            2147483647, 3.4e38f, new Item.Settings()), ModItemGroup.MORE_REDSTONE, ItemGroups.OPERATOR);
    public static final Item SILVER_DUST = registerItemAfter("silver_dust", new
            AliasedBlockItem(ModBlocks.SILVER_WIRE, new FabricItemSettings()),
            Items.REDSTONE, ItemGroups.REDSTONE, ModItemGroup.MORE_REDSTONE);
    public static final Item RAW_SILVER = registerItemAfter("raw_silver", new
            Item(new FabricItemSettings()), Items.RAW_GOLD, ItemGroups.INGREDIENTS);
    public static final Item SILVER_INGOT = registerItemAfter("silver_ingot", new
            Item(new FabricItemSettings()), Items.GOLD_INGOT, ItemGroups.INGREDIENTS);
    public static final Item SILVER_NUGGET = registerItemAfter("silver_nugget", new
            Item(new FabricItemSettings()), Items.GOLD_NUGGET, ItemGroups.INGREDIENTS);
    //-----------------------------------------------------------------------------
    public static Item registerItemAfter(String name, Item item, Item afterItem, RegistryKey<ItemGroup>... itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        for (RegistryKey<ItemGroup> itemGroup: itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.addAfter(afterItem, registeredItem));
        }
        return registeredItem;
    }
    public static Item registerItemBefore(String name, Item item, Item beforeItem, RegistryKey<ItemGroup>... itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        for (RegistryKey<ItemGroup> itemGroup: itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.addBefore(beforeItem, registeredItem));
        }
        return registeredItem;
    }
    public static Item registerItem(String name, Item item, RegistryKey<ItemGroup>... itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        for (RegistryKey<ItemGroup> itemGroup: itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(registeredItem));
        }
        return registeredItem;
    }
    /**
     * mode 1 :把物品加在指定的物品后
     * mode 2 :把物品加在指定的物品前
     * */
    public static Item registerItem(String name, Item item, short mode, Item indItem,RegistryKey<ItemGroup>... itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        if (mode >= 1 && mode <= 2) {
            if (mode == 1) {
                for (RegistryKey<ItemGroup> itemGroup : itemGroups) {
                    ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.addAfter(indItem, registeredItem));
                }
            } else {
                for (RegistryKey<ItemGroup> itemGroup : itemGroups) {
                    ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.addBefore(indItem, registeredItem));
                }
            }
        }else {
            RedstoneMore.LOGGER.warn("Items cannot be added to item groups because the mode parameter is incorrect.");
        }
        return registeredItem;
    }

    public static void registerModItems() {
        LEDItems.registerLEDItems();
    }

}
