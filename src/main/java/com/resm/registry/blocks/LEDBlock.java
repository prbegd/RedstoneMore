package com.resm.registry.blocks;

import com.resm.registry.ModBlocks;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class LEDBlock extends Block implements BlockEntityProvider {
    public static final BooleanProperty LIT = RedstoneTorchBlock.LIT;
    public static final EnumProperty<BlockColorsEnum> UNLIT_COLOR = EnumProperty.of("unlit_color", BlockColorsEnum.class);
    public static final EnumProperty<BlockColorsEnum> LIT_COLOR = EnumProperty.of("lit_color", BlockColorsEnum.class);

    public LEDBlock(FabricBlockSettings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(UNLIT_COLOR, BlockColorsEnum.UNLIT)
                .with(LIT_COLOR, BlockColorsEnum.LIT).with(LIT, false));//设置默认属性
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UNLIT_COLOR, LIT_COLOR, LIT);//注册属性
    }

    @Override
    public void appendTooltip(ItemStack itemStack, BlockView world, List<Text> tooltip, TooltipContext tooltipContext) {
        NbtCompound nbt = BlockItem.getBlockEntityNbt(itemStack);
        if (nbt == null) {
            nbt = new NbtCompound();
            nbt.putString("lit_color", BlockColorsEnum.LIT.name());
            nbt.putString("unlit_color", BlockColorsEnum.UNLIT.name());
            BlockItem.setBlockEntityNbt(itemStack, ModBlocks.LED_BLOCK_ENTITY, nbt);
        }
        tooltip.add(Text.translatable("item.led_block.led_block.tooltip.when_unlit").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.led_block.led_block.tooltip." + nbt.getString("unlit_color")).formatted(Formatting.BLUE, Formatting.ITALIC));
        tooltip.add(Text.translatable("item.led_block.led_block.tooltip.when_lit").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("item.led_block.led_block.tooltip." + nbt.getString("lit_color")).formatted(Formatting.BLUE, Formatting.ITALIC));
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LEDBlockEntity(pos, state);
    }

    @Override
    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        NbtCompound nbt = BlockItem.getBlockEntityNbt(ctx.getStack());
        if (nbt == null) {
            return this.getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()))
                    .with(UNLIT_COLOR, BlockColorsEnum.UNLIT)
                    .with(LIT_COLOR, BlockColorsEnum.LIT);
        }
        return this.getDefaultState().with(LIT, ctx.getWorld().isReceivingRedstonePower(ctx.getBlockPos()))
                .with(UNLIT_COLOR, BlockColorsEnum.valueOf(nbt.getString("unlit_color")))
                .with(LIT_COLOR, BlockColorsEnum.valueOf(nbt.getString("lit_color")));
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
                world.setBlockState(pos, state.cycle(LIT));
            }
        }
    }

    @Override
    public void scheduledTick(@NotNull BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(LIT) && !world.isReceivingRedstonePower(pos)) {
            world.setBlockState(pos, state.cycle(LIT), Block.NOTIFY_LISTENERS);
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof LEDBlockEntity ledBlockEntity) {
            if (ledBlockEntity.getUNLIT_COLOR() != state.get(UNLIT_COLOR)) {
                ledBlockEntity.setUNLIT_COLOR(state.get(UNLIT_COLOR));
            }
            if (ledBlockEntity.getLIT_COLOR() != state.get(LIT_COLOR)) {
                ledBlockEntity.setLIT_COLOR(state.get(LIT_COLOR));
            }
        }
    }
}
