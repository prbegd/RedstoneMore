package com.resm.registry.blocks;

import com.resm.RedstoneMore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.RedstoneTorchBlock;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LEDBlock extends Block {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final EnumProperty UNLIT_COLOR = EnumProperty.of("unlit_color", BlockColors.class);
    public static final EnumProperty LIT_COLOR = EnumProperty.of("lit_color", BlockColors.class);

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UNLIT_COLOR, LIT_COLOR, LIT);//注册属性
    }
    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(Text.translatable("block.led_block.when_unlit"));
        tooltip.add(Text.translatable("block.led_block."+ UNLIT_COLOR.));
        tooltip.add(Text.translatable("block.led_block.when_lit"));
        tooltip.add(Text.translatable("block.led_block."+ LIT_COLOR));
    }//物品提示
    public LEDBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(UNLIT_COLOR, BlockColors.UNLIT)
                .with(LIT_COLOR, BlockColors.LIT).with(LIT, false));//设置默认属性
    }
    //以下为红石灯搬过来的代码
    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()));
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (world.isClient) {
            return;
        }
        boolean bl = state.get(LIT);
        if (bl != world.isReceivingRedstonePower(pos)) {
            if (bl) {
                world.scheduleBlockTick(pos, this, 4);
            } else {
                world.setBlockState(pos, (BlockState)state.cycle(LIT), Block.NOTIFY_LISTENERS);
            }
        }
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT).booleanValue() && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, (BlockState)state.cycle(LIT), Block.NOTIFY_LISTENERS);
        }
    }
}
