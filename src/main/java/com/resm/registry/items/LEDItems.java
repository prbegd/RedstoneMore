package com.resm.registry.items;

import com.resm.registry.AddItemGroup;
import com.resm.registry.ModItemGroup;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;

import static com.resm.registry.ModItems.registerItem;

public class LEDItems {
    public static final Item LED = registerItem("led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item BLACK_LED = registerItem("black_led", new Item(new FabricItemSettings()),new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item BLUE_LED = registerItem("blue_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item BROWN_LED = registerItem("brown_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item CYAN_LED = registerItem("cyan_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item GRAY_LED = registerItem("gray_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item GREEN_LED = registerItem("green_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item LIGHT_BLUE_LED = registerItem("light_blue_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item LIGHT_GRAY_LED = registerItem("light_gray_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item LIME_LED = registerItem("lime_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item MAGENTA_LED = registerItem("magenta_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item ORANGE_LED = registerItem("orange_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item PINK_LED = registerItem("pink_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item PURPLE_LED = registerItem("purple_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item RED_LED = registerItem("red_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item WHITE_LED = registerItem("white_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static final Item YELLOW_LED = registerItem("yellow_led", new Item(new FabricItemSettings()), new AddItemGroup().add(ModItemGroup.MORE_REDSTONE));
    public static void registerLEDItems() {}
}
