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
import org.jetbrains.annotations.Nullable;


/**
 * 用于添加物品或方块到物品组。
 */
public class AddItemGroup {
    private final RegistryKey<ItemGroup>[] itemGroups;
    private final Item[] items;
    private final TargetModeEnum[] addMode;
    private int addIndex;
    private int addCount;

    /**
     * 用于添加物品到游戏中(注册物品并添加物品到物品组中)
     *
     * @param name       物品的命名空间ID,必须为小写字母、下划线和数字的组合,且开头不得是数字
     * @param item       要添加物品的实例。
     * @param itemGroups AddItemGroup的实例,用于添加物品组
     * @return 要添加物品的实例
     * @see #registerBlock(String, Block, AddItemGroup)
     */
    public static <T extends Item> T registerItem(String name, T item, AddItemGroup itemGroups) {
        T registeredItem = Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, name), item);
        for (int i = 0; i < itemGroups.getAddCount(); i++) {
            int I = i;
            switch (itemGroups.getAddMode(i)) {
                case END ->
                        ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i)).register(entries -> entries.add(registeredItem));
                case AFTER ->
                        ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i)).register(entries -> entries.addAfter(itemGroups.getItem(I), registeredItem));
                case BEFORE ->
                        ItemGroupEvents.modifyEntriesEvent(itemGroups.getItemGroup(i)).register(entries -> entries.addBefore(itemGroups.getItem(I), registeredItem));
            }

        }
        return registeredItem;
    }

    /**
     * 用于添加方块到游戏中(注册方块并添加方块到物品组中)
     *
     * @param name       方块的命名空间ID,必须为小写字母、下划线和数字的组合,且开头不得是数字
     * @param block      要添加方块的实例。
     * @param itemGroups AddItemGroup的实例,用于添加物品组
     * @return 要添加方块的实例
     * @see #registerItem(String, Item, AddItemGroup)
     */
    public static <T extends Block> T registerBlock(String name, T block, AddItemGroup itemGroups) {
        registerItem(name, new BlockItem(block, new FabricItemSettings()), itemGroups);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMore.MOD_ID, name), block);
    }

    private enum TargetModeEnum {
        AFTER, BEFORE, END

    }

    private AddItemGroup add(RegistryKey<ItemGroup> itemGroup, Item item, TargetModeEnum targetMode) {
        if (addIndex >= addCount) {
            RedstoneMore.LOGGER.error("The length of the added item group exceeds the specified length of the item group to be added.");
            throw new IndexOutOfBoundsException("The length of the added item group exceeds the specified length of the item group to be added.");
        }
        itemGroups[addIndex] = itemGroup;
        items[addIndex] = targetMode == TargetModeEnum.END ? null : item;
        addMode[addIndex] = targetMode;
        addIndex++;
        return this;
    }

    /**
     * 用于添加物品到物品组的末尾。需要一个AddItemGroup的实例以修改其中的值。
     *
     * @param itemGroup 决定添加到哪一个物品组
     * @return AddItemGroup实例
     * @see #addAfter(RegistryKey, Item)
     * @see #addBefore(RegistryKey, Item)
     */
    public AddItemGroup add(RegistryKey<ItemGroup> itemGroup) {
        return add(itemGroup, null, TargetModeEnum.END);
    }

    public <T extends Item> AddItemGroup addAfter(RegistryKey<ItemGroup> itemGroup, T item) {
        return add(itemGroup, item, TargetModeEnum.AFTER);
    }

    public <T extends Item> AddItemGroup addBefore(RegistryKey<ItemGroup> itemGroup, T item) {
        return add(itemGroup, item, TargetModeEnum.BEFORE);
    }

    public int getAddCount() {
        return addCount;
    }

    public TargetModeEnum getAddMode(int addCount) {
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
        addMode = new TargetModeEnum[0];
    }

    public AddItemGroup(int addCount) {
        itemGroups = new RegistryKey[addCount];
        items = new Item[addCount];
        addMode = new TargetModeEnum[addCount];
        this.addCount = addCount;
    }
}
