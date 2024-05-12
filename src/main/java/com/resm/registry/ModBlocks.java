package com.resm.registry;

import com.resm.RedstoneMore;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block SILVER_ORE = registerBlock("silver_ore", new
            ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.GOLD_ORE)), new
            AddItemGroup().add(ItemGroups.NATURAL, Items.DEEPSLATE_GOLD_ORE,
            AddItemGroup.targetMode.AFTER));
    public static final Block DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", new
            ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE)), new
            AddItemGroup().add(ItemGroups.NATURAL, SILVER_ORE.asItem(),
            AddItemGroup.targetMode.AFTER));
    public static final Block SILVER_BLOCK = registerBlock("silver_block", new
            ExperienceDroppingBlock(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY)
            .requiresTool().strength(3.0f, 6.0f)
            .sounds(BlockSoundGroup.METAL)), new
            AddItemGroup().add(ItemGroups.NATURAL, Items.GOLD_BLOCK,
            AddItemGroup.targetMode.AFTER));
    public static final Block RAW_SILVER_BLOCK = registerBlock("raw_silver_block", new
            ExperienceDroppingBlock(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY)
            .instrument(Instrument.BASEDRUM).requiresTool()
            .strength(5.0f, 6.0f)), new
            AddItemGroup().add(ItemGroups.NATURAL, Items.RAW_GOLD_BLOCK,
            AddItemGroup.targetMode.AFTER));
    //    public static final Block SILVER_WIRE = registerBlock("silver_wire", new
//            SilverWireBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_WIRE)), new AddItemGroup());
    public static final Block REDSTONELAMP1 = registerBlock("redstone_lamp1", new
            RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup()
            .add(ModItemGroup.MORE_REDSTONE)
    );
//    public static final Block REDSTONELAMP2 = registerBlock("redstone_lamp2", new
//            RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup()
//            .add(ModItemGroup.MORE_REDSTONE));
//    public static final Block REPEATER_WITH_NO_DELAY = registerBlock("repeater_with_no_delay", new
//            Repeater_With_No_Delay(FabricBlockSettings.copyOf(Blocks.REPEATER)), new AddItemGroup()
//            .add(ModItemGroup.MORE_REDSTONE));
//    public static final Block LED_BLOCK = registerBlock("led_block", new
//            LEDBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup()
//            .add(ModItemGroup.MORE_REDSTONE));
//    public static final BlockEntityType<LEDBlockEntity> LED_BLOCK_ENTITY = Registry.register(
//            Registries.BLOCK_ENTITY_TYPE,
//            new Identifier(RedstoneMore.MOD_ID, "led_block_entity"),
//            FabricBlockEntityTypeBuilder.create(LEDBlockEntity::new, LED_BLOCK).build()
//    );

    //---------------------------------------------------------------------
    public static Block registerBlock(String name, Block block, AddItemGroup itemGroups) {
        ModItems.registerItem(name, new BlockItem(block, new FabricItemSettings()), itemGroups);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMore.MOD_ID, name), block);
    }

    public static void registerModBlocks() {
    }
}
