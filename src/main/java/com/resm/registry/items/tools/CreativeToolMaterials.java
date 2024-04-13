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
        return Float.MAX_VALUE;
    }

    @Override
    public float getAttackDamage() {
        return Float.MAX_VALUE;
    }

    @Override
    public int getMiningLevel() {
        return Integer.MAX_VALUE;
    }

    @Override
    public int getEnchantability() {
        return Integer.MAX_VALUE;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }
}
