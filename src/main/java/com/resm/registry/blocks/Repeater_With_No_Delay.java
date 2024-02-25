package com.resm.registry.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneLampBlock;
import net.minecraft.block.RepeaterBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Repeater_With_No_Delay extends Block{
    //创建状态 自创状态把后面改成BooleanProperty.of("[状态名]")
    public static final BooleanProperty POWERED = Properties.POWERED;//用的是原版已有的状态

    public Repeater_With_No_Delay(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(POWERED, false));
        //.with(FACING, Direction.NORTH))
    }//设置默认状态

    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        world.setBlockState(pos, state.with(POWERED, true));
        return ActionResult.SUCCESS;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED);
    }//注册属性
}
