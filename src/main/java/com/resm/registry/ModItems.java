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

    public static final Item EXAMPLE_ITEM = registerItem("example_item", new Item(new FabricItemSettings()), ModItemGroup.MORE_REDSTONE);

    public static final Item MOB_KILLER = registerItem("mob_killer", new SwordItem(CreativeToolMaterials.CREATIVETOOLMATERIALS,
            2147483647, 3.4e38f, new Item.Settings()), ModItemGroup.MORE_REDSTONE, ItemGroups.OPERATOR);
    //-----------------------------------------------------------------------------
    public static Item registerItem(String name, Item item, RegistryKey<ItemGroup>... itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        for (RegistryKey<ItemGroup> itemGroup: itemGroups) {
            ItemGroupEvents.modifyEntriesEvent(itemGroup).register(entries -> entries.add(registeredItem));
        }
        return registeredItem;
    }

    public static void registerModItems() {
        LEDItems.registerLEDItems();
    }

}
