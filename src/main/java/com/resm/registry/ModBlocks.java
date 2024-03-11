package com.resm.registry;

import com.resm.RedstoneMore;
import com.resm.registry.blocks.LEDBlock;
import com.resm.registry.blocks.LEDBlockEntity;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
public class ModBlocks {
    //public static final Block EXAMPLE_BLOCK = registerBlock("example_block", new
    // Block(FabricBlockSettings.create().strength(114.5f)), ModItemGroup.MORE_REDSTONE);
    public static final Block SILVER_ORE = registerBlockAfter("silver_ore", new
            ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.GOLD_ORE)),
            Item.fromBlock(Blocks.DEEPSLATE_GOLD_ORE), ItemGroups.NATURAL);
    public static final Block DEEPSLATE_SILVER_ORE = registerBlockAfter("deepslate_silver_ore", new
            ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE)),
            Item.fromBlock(SILVER_ORE), ItemGroups.NATURAL);
    public static final Block SILVER_BLOCK = registerBlockAfter("silver_block", new
            ExperienceDroppingBlock(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY)
            .requiresTool().strength(3.0f, 6.0f)
            .sounds(BlockSoundGroup.METAL)), Item.fromBlock(Blocks.GOLD_BLOCK),
            ItemGroups.BUILDING_BLOCKS);
    public static final Block RAW_SILVER_BLOCK = registerBlockAfter("raw_silver_block", new
            ExperienceDroppingBlock(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY)
            .instrument(Instrument.BASEDRUM).requiresTool()
            .strength(5.0f, 6.0f)), Item.fromBlock(Blocks.RAW_GOLD_BLOCK),
            ItemGroups.NATURAL);
    public static final Block SILVER_WIRE = registerBlock("silver_wire", new
            RedstoneWireBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_WIRE)));
    public static final Block REDSTONELAMP1 = registerBlock("redstone_lamp1", new
            RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)),
            ModItemGroup.MORE_REDSTONE);
    public static final Block REDSTONELAMP2 = registerBlock("redstone_lamp2", new
            RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)),
            ModItemGroup.MORE_REDSTONE);
    //public static final Block REPEATER_WITH_NO_DELAY = registerBlock("repeater_with_no_delay", new
            //Repeater_With_No_Delay(FabricBlockSettings.copyOf(Blocks.REPEATER)), ModItemGroup.MORE_REDSTONE);
    public static final Block LED_BLOCK = registerBlock("led_block", new
            LEDBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)),
            ModItemGroup.MORE_REDSTONE);
    public static final BlockEntityType<LEDBlockEntity> LED_BLOCK_ENTITY = Registry.register(
            Registries.BLOCK_ENTITY_TYPE,
            new Identifier(RedstoneMore.MOD_ID, "led_block_entity"),
            FabricBlockEntityTypeBuilder.create(LEDBlockEntity::new, LED_BLOCK).build()
    );

    //---------------------------------------------------------------------
    public static Block registerBlock(String name, Block block, RegistryKey<ItemGroup>... itemgroups) {
        ModItems.registerItem(name, new BlockItem(block, new FabricItemSettings()), itemgroups);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMore.MOD_ID, name), block);
    }
    public static Block registerBlockAfter(String name, Block block, Item afterItem, RegistryKey<ItemGroup>... itemgroups) {
        ModItems.registerItemAfter(name, new BlockItem(block, new FabricItemSettings()), afterItem, itemgroups);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMore.MOD_ID, name), block);
    }
    public static Block registerBlockBefore(String name, Block block, Item beforeItem, RegistryKey<ItemGroup>... itemgroups) {
        ModItems.registerItemBefore(name, new BlockItem(block, new FabricItemSettings()), beforeItem, itemgroups);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMore.MOD_ID, name), block);
    }
    public static void registerModBlocks() {}
}
