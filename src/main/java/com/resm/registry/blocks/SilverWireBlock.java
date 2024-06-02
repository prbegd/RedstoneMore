package com.resm.registry.blocks;

import com.resm.registry.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
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
            NbtCompound nbt = new NbtCompound();
            nbt.putString("text", String.valueOf(state.get(POWER)));
            nbt.putString("billboard", "center");
            nbt.putString("alignment", "center");
            DisplayEntity.TextDisplayEntity entity = EntityType.TEXT_DISPLAY.spawn(world.getServer().getWorld(world.getRegistryKey()), null, null, pos.up(1), null, true, false);
            NbtList nbtList = new NbtList();
            nbtList.add(NbtDouble.of(entity.getX()));
            nbtList.add(NbtDouble.of(entity.getY()));
            nbtList.add(NbtDouble.of(entity.getZ()));
            nbt.put("Pos", nbtList);
            entity.readNbt(nbt);
            new ClearText(entity).start();
            return ActionResult.SUCCESS;
        }
        return super.onUse(state, world, pos, player, hand, hit);
    }
}