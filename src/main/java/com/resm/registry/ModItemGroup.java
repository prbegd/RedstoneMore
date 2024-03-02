package com.resm.registry;


import com.resm.RedstoneMore;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroup {
    public static final RegistryKey<ItemGroup> MORE_REDSTONE = RegistryKey.of(RegistryKeys.ITEM_GROUP,
            new Identifier(RedstoneMore.MOD_ID,"example"));
    public static void registerModGroup() {
        Registry.register(Registries.ITEM_GROUP, MORE_REDSTONE, FabricItemGroup.builder().displayName(
                Text.translatable("itemgroup.redstone_more.moreRedstone")).icon(()->new
                ItemStack(ModItems.EXAMPLE_ITEM))//设置组图标
                .build());
    }
}
