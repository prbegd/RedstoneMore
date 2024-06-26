package com.resm.registry.blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;

public class RepeaterWithNoDelay extends AbstractRedstoneGateBlock {
    public RepeaterWithNoDelay(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(POWERED, false));
        //.with(FACING, Direction.NORTH))
    }//设置默认状态

    @Override
    protected int getUpdateDelayInternal(BlockState state) {
        return 0;
    }
    @Override
    protected boolean getSideInputFromGatesOnly() {
        return true;
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWERED) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(POWERED), Block.NOTIFY_LISTENERS);
        }
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }//注册属性
}
