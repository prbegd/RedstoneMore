package com.resm.registry;

import com.resm.RedstoneMore;
import com.resm.registry.blocks.*;
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

import static com.resm.registry.AddItemGroup.*;

public class ModBlocks {
    public static final ExperienceDroppingBlock SILVER_ORE = registerBlock("silver_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.GOLD_ORE)), new AddItemGroup(1).addAfter(ItemGroups.NATURAL, Items.DEEPSLATE_GOLD_ORE));
    public static final ExperienceDroppingBlock DEEPSLATE_SILVER_ORE = registerBlock("deepslate_silver_ore", new ExperienceDroppingBlock(FabricBlockSettings.copyOf(Blocks.DEEPSLATE_GOLD_ORE)), new AddItemGroup(1).addAfter(ItemGroups.NATURAL, SILVER_ORE.asItem()));
    public static final Block SILVER_BLOCK = registerBlock("silver_block", new Block(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY).requiresTool().strength(3.0f, 6.0f).sounds(BlockSoundGroup.METAL)), new AddItemGroup(1).addAfter(ItemGroups.NATURAL, Items.GOLD_BLOCK));
    public static final Block RAW_SILVER_BLOCK = registerBlock("raw_silver_block", new Block(FabricBlockSettings.create().mapColor(MapColor.IRON_GRAY).instrument(Instrument.BASEDRUM).requiresTool().strength(5.0f, 6.0f)), new AddItemGroup(1).addAfter(ItemGroups.NATURAL, Items.RAW_GOLD_BLOCK));
    public static final SilverWireBlock SILVER_WIRE = registerBlock("silver_wire", new SilverWireBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_WIRE)), new AddItemGroup());
    public static final RedstoneLampBlock REDSTONE_LAMP1 = registerBlock("redstone_lamp1", new RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final RedstoneLampBlock REDSTONE_LAMP2 = registerBlock("redstone_lamp2", new RedstoneLampBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final RepeaterWithNoDelay REPEATER_WITH_NO_DELAY = registerBlock("repeater_with_no_delay", new RepeaterWithNoDelay(FabricBlockSettings.copyOf(Blocks.REPEATER)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final LEDBlock LED_BLOCK = registerBlock("led_block", new LEDBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_LAMP)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final BlockEntityType<LEDBlockEntity> LED_BLOCK_ENTITY = Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(RedstoneMore.MOD_ID, "led_block_entity"), FabricBlockEntityTypeBuilder.create(LEDBlockEntity::new, LED_BLOCK).build());
    public static final CopperBulbBlock COPPER_BULB = registerBlock("copper_bulb", new CopperBulbBlock(FabricBlockSettings.copyOf(Blocks.COPPER_BLOCK).solidBlock(Blocks::never)), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));
    public static final IndicatorBlock INDICATOR = registerBlock("indicator", new IndicatorBlock(FabricBlockSettings.copyOf(Blocks.STONE)/*.solidBlock(Blocks::never)*/), new AddItemGroup(1).add(ModItemGroups.MORE_REDSTONE));

    public static void registerModBlocks() {
    }
}
