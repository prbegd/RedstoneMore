package com.resm.registry.items.tools;

import com.resm.RedstoneMore;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class CreativeToolMaterials implements ToolMaterial {
    public static final CreativeToolMaterials CREATIVETOOLMATERIALS = new CreativeToolMaterials();
    @Override
    public int getDurability() {
        return -1;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 3.4e38f;
    }

    @Override
    public float getAttackDamage() {
        return 3.4e38f;
    }

    @Override
    public int getMiningLevel() {
        return 2147483647;
    }

    @Override
    public int getEnchantability() {
        return 2147483647;//2147483647
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
