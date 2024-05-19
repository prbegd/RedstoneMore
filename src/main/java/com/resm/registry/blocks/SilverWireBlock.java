package com.resm.registry.blocks;

import com.resm.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;

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
            text.putString("billboard", "center");
            text.putString("alignment", "center");
            DisplayEntity.TextDisplayEntity textEntity = EntityType.TEXT_DISPLAY.spawn(world.getServer().getWorld(world.getRegistryKey()), pos.up(1), SpawnReason.COMMAND);
            textEntity.readNbt(text);
            new clearText(textEntity).start();
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }

    class clearText extends Thread {
        private final DisplayEntity.TextDisplayEntity entity;

        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            entity.discard();
        }

        clearText(DisplayEntity.TextDisplayEntity entity) {
            this.entity = entity;
        }
    }
}