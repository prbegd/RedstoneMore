package com.resm.registry.items;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;

public class MobKillerItem extends Item {
    public MobKillerItem(Item.Settings settings) {
        super(settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        target.kill();
        return false;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (user.getWorld().isClient) {
            return ActionResult.PASS;
        }
        entity.discard();
        user.sendMessage(Text.translatable("item.redstone_more.mob_killer.msg", entity.getName()));
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {// 默认为白色文本
        tooltip.add(Text.translatable("item.redstone_more.mob_killer.tooltip1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.redstone_more.mob_killer.tooltip2").formatted(Formatting.GRAY));
    }
}
