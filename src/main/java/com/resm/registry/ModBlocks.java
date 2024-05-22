package com.resm.registry;

import com.resm.RedstoneMore;
import com.resm.registry.blocks.LEDBlock;
import com.resm.registry.blocks.LEDBlockEntity;
import com.resm.registry.blocks.Repeater_With_No_Delay;
import com.resm.registry.blocks.SilverWireBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block SILVER_ORE = AddItemGroup.registerBlock("silver_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.GOLD_ORE)), new AddItemGroup(1).add(ItemGroups.NATURAL, Items.DEEPSLATE_GOLD_ORE, AddItemGroup.TargetModeEnum.AFTER));
    public static final Block DEEPSLATE_SILVER_ORE = AddItemGroup.registerBlock("deepslate_silver_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE)), new AddItemGroup(1).add(ItemGroups.NATURAL, SILVER_ORE.asItem(), AddItemGroup.TargetModeEnum.AFTER));
    public static final Block SILVER_BLOCK = AddItemGroup.registerBlock("silver_block", new ExperienceDroppingBlock(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.METAL)), new AddItemGroup(1).add(ItemGroups.NATURAL, Items.GOLD_BLOCK, AddItemGroup.TargetModeEnum.AFTER));
    public static final Block RAW_SILVER_BLOCK = AddItemGroup.registerBlock("raw_silver_block", new ExperienceDroppingBlock(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(5.0f, 6.0f)), new AddItemGroup(1).add(ItemGroups.NATURAL, Items.RAW_GOLD_BLOCK, AddItemGroup.TargetModeEnum.AFTER));
    public static final Block SILVER_WIRE = AddItemGroup.registerBlock("silver_wire", new SilverWireBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_WIRE)), new AddItemGroup());
    public static final Block REDSTONELAMP1 = AddItemGroup.registerBlock("redstone_lamp1", new RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final Block REDSTONELAMP2 = AddItemGroup.registerBlock("redstone_lamp2", new RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final Block REPEATER_WITH_NO_DELAY = AddItemGroup.registerBlock("repeater_with_no_delay", new Repeater_With_No_Delay(FabricBlockSettings.copyOf(Blocks.REPEATER)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final Block LED_BLOCK = AddItemGroup.registerBlock("led_block", new LEDBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final BlockEntityType<LEDBlockEntity> LED_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(RedstoneMore.MOD_ID, "led_block_entity"), FabricBlockEntityTypeBuilder.create(LEDBlockEntity::new, LED_BLOCK).build());

    public static void registerModBlocks() {
    }
}
