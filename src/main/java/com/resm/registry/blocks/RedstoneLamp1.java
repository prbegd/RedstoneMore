package com.resm.registry.blocks;

import com.resm.RedstoneMore;
import com.resm.registry.ModBlocks;
import com.resm.registry.ModItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;

public class RedstoneLamp1 {
    public static final Block REDSTONELAMP1 = ModBlocks.registerBlock("redstone_lamp1", new
            RedstoneLampBlock(FabricBlockSettings.create().strength(0.3f).luminance(Blocks.
            createLightLevelFromLitBlockState(15)).sounds(BlockSoundGroup.GLASS)
            .allowsSpawning(Blocks::always)), ModItemGroup.MORE_REDSTONE);
    public static final Block REDSTONELAMP2 = ModBlocks.registerBlock("redstone_lamp2", new
            RedstoneLampBlock(FabricBlockSettings.create().strength(0.3f).luminance(Blocks.
                    createLightLevelFromLitBlockState(15)).sounds(BlockSoundGroup.GLASS)
            .allowsSpawning(Blocks::always)), ModItemGroup.MORE_REDSTONE);

    public static void registerModBlocks() {
        RedstoneMore.LOGGER.debug("Registering mod blocks for" + RedstoneMore.MOD_ID);
    }
}
