package com.resm.registry.blocks;

import com.resm.RedstoneMore;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class IndicatorBlock extends Block {
    public static final EnumProperty<DirectionEnum> UP = EnumProperty.of("up", DirectionEnum.class);
    public static final EnumProperty<DirectionEnum> DOWN = EnumProperty.of("down", DirectionEnum.class);
    public static final EnumProperty<DirectionEnum> NORTH = EnumProperty.of("north", DirectionEnum.class);
    public static final EnumProperty<DirectionEnum> SOUTH = EnumProperty.of("south", DirectionEnum.class);
    public static final EnumProperty<DirectionEnum> WEST = EnumProperty.of("west", DirectionEnum.class);
    public static final EnumProperty<DirectionEnum> EAST = EnumProperty.of("east", DirectionEnum.class);
    public static final IntProperty POWER = Properties.POWER;

    public IndicatorBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState()
                .with(UP, DirectionEnum.NULL)
                .with(DOWN, DirectionEnum.NULL)
                .with(NORTH, DirectionEnum.NULL)
                .with(SOUTH, DirectionEnum.NULL)
                .with(WEST, DirectionEnum.NULL)
                .with(EAST, DirectionEnum.NULL)
                .with(POWER, 0));
    }

    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(UP, DOWN, NORTH, SOUTH, WEST, EAST, POWER);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World world = ctx.getWorld();
        BlockPos pos = ctx.getBlockPos();
        int maxPower = 0;
        Direction maxPowerDirection = null;
        if (world.getEmittedRedstonePower(pos.down(), Direction.DOWN) > maxPower) {
            RedstoneMore.LOGGER.info("getPlacementState1");
            maxPowerDirection = Direction.DOWN;
            RedstoneMore.LOGGER.info("getPlacementState2");
            maxPower = world.getEmittedRedstonePower(pos.down(), Direction.DOWN);
            RedstoneMore.LOGGER.info("getPlacementState3");
        }
        if (world.getEmittedRedstonePower(pos.up(), Direction.UP) > maxPower) {
            RedstoneMore.LOGGER.info("getPlacementState4");
            maxPowerDirection = Direction.UP;
            RedstoneMore.LOGGER.info("getPlacementState5");
            maxPower = world.getEmittedRedstonePower(pos.up(), Direction.UP);
        }
        if (world.getEmittedRedstonePower(pos.north(), Direction.NORTH) > maxPower) {
            maxPowerDirection = Direction.NORTH;
            maxPower = world.getEmittedRedstonePower(pos.north(), Direction.NORTH);
        }
        if (world.getEmittedRedstonePower(pos.south(), Direction.SOUTH) > maxPower) {
            maxPowerDirection = Direction.SOUTH;
            maxPower = world.getEmittedRedstonePower(pos.south(), Direction.SOUTH);
        }
        if (world.getEmittedRedstonePower(pos.west(), Direction.WEST) > maxPower) {
            maxPowerDirection = Direction.WEST;
            maxPower = world.getEmittedRedstonePower(pos.west(), Direction.WEST);
        }
        if (world.getEmittedRedstonePower(pos.east(), Direction.EAST) > maxPower) {
            maxPowerDirection = Direction.EAST;
            maxPower = world.getEmittedRedstonePower(pos.east(), Direction.EAST);
        }
        if (maxPowerDirection == null) return getDefaultState();
        RedstoneMore.LOGGER.info("getPlacementState1 done");
        return getDefaultState()
                .with(UP, DirectionEnum.getRelativeDirection(Direction.UP, maxPowerDirection))
                .with(DOWN, DirectionEnum.getRelativeDirection(Direction.DOWN, maxPowerDirection))
                .with(NORTH, DirectionEnum.getRelativeDirection(Direction.NORTH, maxPowerDirection))
                .with(SOUTH, DirectionEnum.getRelativeDirection(Direction.SOUTH, maxPowerDirection))
                .with(WEST, DirectionEnum.getRelativeDirection(Direction.WEST, maxPowerDirection))
                .with(EAST, DirectionEnum.getRelativeDirection(Direction.EAST, maxPowerDirection))
                .with(POWER, maxPower);
    }
}