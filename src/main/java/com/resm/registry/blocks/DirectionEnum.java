package com.resm.registry.blocks;

import com.resm.RedstoneMore;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.Direction;

public enum DirectionEnum implements StringIdentifiable {
    NULL("null"),
    FRONT("front"),
    BACK("back"),
    UP("up"),
    DOWN("down"),
    LEFT("left"),
    RIGHT("right");
    private final String name;
    DirectionEnum(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }

    @Override
    public String asString() {
        return name;
    }

    public static DirectionEnum getRelativeDirection(Direction thisDirection, Direction targetDirection) {
        if (thisDirection.equals(targetDirection)) return FRONT;
        switch (thisDirection) {
            case UP :
                switch (targetDirection) {
                    case DOWN -> {
                        return BACK;
                    }
                    case NORTH -> {
                        return UP;
                    }
                    case SOUTH -> {
                        return DOWN;
                    }
                    case WEST -> {
                        return LEFT;
                    }
                    case EAST -> {
                        return RIGHT;
                    }
                }
                break;
            case DOWN:
                switch (targetDirection) {
                    case UP -> {
                        return BACK;
                    }
                    case NORTH -> {
                        return UP;
                    }
                    case SOUTH -> {
                        return DOWN;
                    }
                    case WEST -> {
                        return LEFT;
                    }
                    case EAST -> {
                        return RIGHT;
                    }
                }
                break;
            case NORTH:
                switch (targetDirection) {
                    case UP -> {
                        return UP;
                    }
                    case DOWN -> {
                        return DOWN;
                    }
                    case SOUTH -> {
                        return BACK;
                    }
                    case WEST -> {
                        return RIGHT;
                    }
                    case EAST -> {
                        return LEFT;
                    }
                }
                break;
            case SOUTH:
                switch (targetDirection) {
                    case UP -> {
                        return UP;
                    }
                    case DOWN -> {
                        return DOWN;
                    }
                    case NORTH -> {
                        return BACK;
                    }
                    case WEST -> {
                        return LEFT;
                    }
                    case EAST -> {
                        return RIGHT;
                    }
                }
                break;
            case WEST:
                switch (targetDirection) {
                    case UP -> {
                        return UP;
                    }
                    case DOWN -> {
                        return DOWN;
                    }
                    case SOUTH -> {
                        return RIGHT;
                    }
                    case EAST -> {
                        return BACK;
                    }
                    case NORTH -> {
                        return LEFT;
                    }
                }
                break;
            case EAST:
                switch (targetDirection) {
                    case UP -> {
                        return UP;
                    }
                    case DOWN -> {
                        return DOWN;
                    }
                    case SOUTH -> {
                        return LEFT;
                    }
                    case WEST -> {
                        return BACK;
                    }
                    case NORTH -> {
                        return RIGHT;
                    }
                }
                break;
        }
        RedstoneMore.LOGGER.error("Unable to find the corresponding direction[{},{}]", thisDirection, targetDirection);
        return null;
    }
}
