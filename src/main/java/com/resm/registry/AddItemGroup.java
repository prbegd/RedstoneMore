package com.resm.registry;

import com.resm.RedstoneMore;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class AddItemGroup {
    public static Item registerItem(String name, Item item, AddItemGroup itemGroups) {
        Item registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        for (int i = 0; i < itemGroups.getAddCount(); i++) {
            int I = i;
            switch (itemGroups.getAddMode(i)) {
                case END -> ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i))
                        .register(entries -> entries.add(registeredItem));
                case AFTER -> ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i))
                        .register(entries -> entries.addAfter(itemGroups.getItem(I), registeredItem));
                case BEFORE -> ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i))
                        .register(entries -> entries.addBefore(itemGroups.getItem(I), registeredItem));
            }

        }
        return registeredItem;
    }

    public static Block registerBlock(String name, Block block, AddItemGroup itemGroups) {
        registerItem(name, new BlockItem(block, new FabricItemSettings()), itemGroups);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMore.MOD_ID, name), block);
    }

    public enum targetMode {
        END,
        AFTER,
        BEFORE
    }

    private final RegistryKey<ItemGroup>[] itemGroups;
    private final Item[] items;
    private final targetMode[] addMode;
    private int addIndex;
    private int addCount;


    public AddItemGroup add(RegistryKey<ItemGroup> itemGroup, Item item, targetMode targetMode) {
        if (addIndex >= addCount) {
            //RedstoneMore.LOGGER.error("The length of the added item group exceeds the specified length of the item group to be added.", new IndexOutOfBoundsException("The length of the added item group exceeds the specified length of the item group to be added."));
            throw new IndexOutOfBoundsException("The length of the added item group exceeds the specified length of the item group to be added.");
        }
        itemGroups[addIndex] = itemGroup;
        items[addIndex] = targetMode == AddItemGroup.targetMode.END ? null : item;
        addMode[addIndex] = targetMode;
        addIndex++;
        return this;
    }

    public AddItemGroup add(RegistryKey<ItemGroup> itemGroup) {
        return add(itemGroup, null, targetMode.END);
    }

    public int getAddCount() {
        return addCount;
    }

    public targetMode getAddMode(int addCount) {
        return addMode[addCount];
    }

    public RegistryKey<ItemGroup> getItemGroup(int addCount) {
        return itemGroups[addCount];
    }

    public Item getItem(int addCount) {
        if (items[addCount] == null) {
            RedstoneMore.LOGGER.warn("The corresponding item is null");
        }
        return items[addCount];
    }

    public AddItemGroup() {
        itemGroups = new RegistryKey[0];
        items = new Item[0];
        addMode = new targetMode[0];
    }

    public AddItemGroup(int addCount) {
        itemGroups = new RegistryKey[addCount];
        items = new Item[addCount];
        addMode = new targetMode[addCount];
        this.addCount = addCount;
    }
}
