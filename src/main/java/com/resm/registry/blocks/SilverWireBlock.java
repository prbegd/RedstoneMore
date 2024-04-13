package com.resm.registry.blocks;

import com.resm.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SilverWireBlock extends RedstoneWireBlock {
    public SilverWireBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.CONSUME;
        }
        if (player.getMainHandStack().getItem() == ModItems.SPANNER) {
            NbtCompound text = new NbtCompound();
            text.putString("text", String.valueOf(state.get(POWER)));
            text.putInt("background", 0);
            ServerWorld serverWorld = world.getServer().getWorld(world.getRegistryKey());
            world.spawnEntity(EntityType.TEXT_DISPLAY.spawn(serverWorld, text, null, pos.up(1), SpawnReason.EVENT, false, false));
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}
