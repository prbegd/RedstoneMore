package com.resm.registry;

import com.resm.RedstoneMore;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;

public class AddItemGroup {
    public enum targetMode {
        END,
        AFTER,
        BEFORE
    }

    private RegistryKey<ItemGroup>[] itemGroups = new RegistryKey[16];
    private Item[] items = new Item[16];
    private targetMode[] addMode = new targetMode[16];
    private int addCount = 0;


    public AddItemGroup add(RegistryKey<ItemGroup> itemGroup, Item item, targetMode targetMode) {
        itemGroups[addCount] = itemGroup;
        items[addCount] = targetMode == AddItemGroup.targetMode.END ? null : item;
        addMode[addCount] = targetMode;
        addCount++;
        return this;
    }

    public AddItemGroup add(RegistryKey<ItemGroup> itemGroup) {
        return add(itemGroup, null, targetMode.END);
    }

    public int getItemGroupsCount() {
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
            RedstoneMore.LOGGER.warn("The corresponding item is null",
                    new NullPointerException("The corresponding array item is null"));
        }
        return items[addCount];
    }
}
