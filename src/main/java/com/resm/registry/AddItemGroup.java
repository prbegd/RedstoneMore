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
        if (targetMode == AddItemGroup.targetMode.END) {
            this.itemGroups[addCount] = itemGroup;
            this.addMode[addCount] = AddItemGroup.targetMode.END;
            this.addCount++;
            return this;
        }
        this.itemGroups[addCount] = itemGroup;
        this.items[addCount] = item;
        this.addMode[addCount] = targetMode;
        this.addCount++;
        return this;
    }

    public AddItemGroup add(RegistryKey<ItemGroup> itemGroup) {
        return this.add(itemGroup, null, targetMode.END);
    }

    public int getItemGroupsCount() {
        return this.addCount;
    }

    public targetMode getAddMode(int addCount) {
        return this.addMode[addCount];
    }

    public RegistryKey<ItemGroup> getItemGroup(int addCount) {
        return this.itemGroups[addCount];
    }

    public Item getItem(int addCount) {
        if (this.items[addCount] == null) {
            RedstoneMore.LOGGER.warn("The corresponding item is null",
                    new NullPointerException("The corresponding array item is null"));
        }
        return this.items[addCount];
    }
}
