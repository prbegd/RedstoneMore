package com.resm.registry.items.tools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Property;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;

public class DebugToolItem extends Item {
    public DebugToolItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (context.getWorld().isClient) {
            return ActionResult.CONSUME;
        }
        World world = context.getWorld();
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        Collection<Property<?>> collection = state.getProperties();
        PlayerEntity player = context.getPlayer();
        if (player == null) {
            return ActionResult.PASS;
        }
        String blockId = Registries.BLOCK.getId(block).toString();
        String blockName = block.getName().getString();
        player.sendMessage(Text.literal(blockName + "(" + blockId + ")").append(Text.literal("'s data:")));
        if (!collection.isEmpty()) {
            player.sendMessage(Text.literal("Block states:"));
            for (Property<?> property : collection) {
                player.sendMessage(Text.literal(property.getName()).formatted(Formatting.LIGHT_PURPLE).append(":")
                        .append(Text.literal(state.get(property).toString()).formatted(Formatting.BLUE)));
            }
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity != null) {
            NbtCompound nbt = blockEntity.createNbt();
            if (!nbt.isEmpty()) {
                player.sendMessage(Text.literal("Block NBTs:"));
                for (String key : nbt.getKeys()) {
                    player.sendMessage(Text.literal(key).formatted(Formatting.GREEN).append(":")
                            .append(Text.literal(nbt.get(key).asString()).formatted(Formatting.GOLD)));
                }
            }
            String blockEntityName = Registries.BLOCK_ENTITY_TYPE.getId(blockEntity.getType()).toString();
            player.sendMessage(Text.literal("Block entity id:").formatted(Formatting.RESET).append(Text.literal(blockEntityName).formatted(Formatting.DARK_GREEN)));
        }
        player.sendMessage(Text.literal("Block's position:")
                .append(Text.literal("X:").formatted(Formatting.AQUA))
                .append(Text.literal(String.valueOf(pos.getX())).formatted(Formatting.YELLOW))
                .append(Text.literal(" Y:").formatted(Formatting.AQUA))
                .append(Text.literal(String.valueOf(pos.getY())).formatted(Formatting.YELLOW))
                .append(Text.literal(" Z:").formatted(Formatting.AQUA))
                .append(Text.literal(String.valueOf(pos.getZ())).formatted(Formatting.YELLOW)));
        player.sendMessage(Text.literal("--------------------"));
        return ActionResult.SUCCESS;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        World world = user.getWorld();
        if (world.isClient) {
            return ActionResult.CONSUME;
        }

        String entityId = Registries.ENTITY_TYPE.getId(entity.getType()).toString();
        String entityName = entity.getName().getString();
        String entityType = entity.getType().getName().getString();
        user.sendMessage(Text.literal(entityName).append("'s data:"));
        user.sendMessage(Text.literal("Entity type:").formatted(Formatting.LIGHT_PURPLE).append(Text.literal(entityType).formatted(Formatting.BLUE).append("(").append(entityId).append(")")));
        user.sendMessage(Text.literal("UUID:").formatted(Formatting.LIGHT_PURPLE).append(Text.literal(entity.getUuidAsString()).formatted(Formatting.BLUE)));
        //user.sendMessage(Text.literal(entity.getDisplayName());
        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("A convenient tool to call /data"));
    }
}
