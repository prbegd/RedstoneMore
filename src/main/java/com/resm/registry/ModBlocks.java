package com.resm.registry;

import com.resm.RedstoneMore;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block EXAMPLE_BLOCK = registerBlock("example_block", new
            Block(FabricBlockSettings.create().strength(1200.0f)), ModItemGroup.MORE_REDSTONE);
    public static Block registerBlock(String name, Block block, RegistryKey<ItemGroup>... itemgroups) {
        ModItems.registerItem(name, new BlockItem(block, new FabricItemSettings()), itemgroups);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMore.MOD_ID, name), block);
    }
    public static void registerModBlocks() {
        RedstoneMore.LOGGER.debug("Registering mod blocks for" + RedstoneMore.MOD_ID);
    }
}
