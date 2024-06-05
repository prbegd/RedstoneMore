package com.resm.registry.items.tools;

import com.resm.library.ClearText;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneWireBlock;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpannerItem extends Item {
    public SpannerItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World world = context.getWorld();
        if (world.isClient) return ActionResult.CONSUME;
        BlockPos pos = context.getBlockPos();
        BlockState state = world.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof RedstoneWireBlock) {
            NbtCompound nbt = new NbtCompound();
            nbt.putString("text", String.valueOf(state.get(RedstoneWireBlock.POWER)));
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
        return ActionResult.PASS;
    }
}
