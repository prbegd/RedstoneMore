package com.resm.registry.items.tools;

import com.resm.RedstoneMore;
import com.resm.registry.ModItemGroup;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class MobKiller extends SwordItem {
    public static ToolItem MOB_KILLER = new SwordItem(CreativeToolMaterials.CREATIVETOOLMATERIALS,
            0, 3.4e38f, new Item.Settings());

    public MobKiller(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public static void registerModItems() {
        RedstoneMore.LOGGER.debug("Registering mod items for" + RedstoneMore.MOD_ID);
        Registry.register(Registries.ITEM, new Identifier(RedstoneMore.MOD_ID, "mob_killer"), MOB_KILLER);
        ItemGroupEvents.modifyEntriesEvent(ModItemGroup.MORE_REDSTONE).register(entries -> {
            entries.add(MOB_KILLER);
        });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.OPERATOR).register(entries -> {
            entries.add(MOB_KILLER);
        });
    }
}
