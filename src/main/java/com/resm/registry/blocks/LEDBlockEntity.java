package com.resm.registry.blocks;

import com.resm.RedstoneMore;
import com.resm.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class LEDBlockEntity extends BlockEntity {
    public static BlockColors UNLIT_COLOR = BlockColors.BLUE;
    public static BlockColors LIT_COLOR = BlockColors.RED;
    public LEDBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.LED_BLOCK_ENTITY, pos, state);
        if (world != null) {
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        }
        RedstoneMore.LOGGER.info("------------LEDBlockEntity");
    }
    @Override
    public void writeNbt(NbtCompound nbt) {
        RedstoneMore.LOGGER.info(nbt +LIT_COLOR.name()+UNLIT_COLOR.name());
        nbt.putString("lit_color", LIT_COLOR.name());
        nbt.putString("unlit_color", UNLIT_COLOR.name());
        super.writeNbt(nbt);
        RedstoneMore.LOGGER.info("------------writeNBT");
        RedstoneMore.LOGGER.info(nbt +LIT_COLOR.name()+UNLIT_COLOR.name());
    }
    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        RedstoneMore.LOGGER.info("------------readNBT");
        RedstoneMore.LOGGER.info("readNBT"+nbt.getString("lit_color")+"|and|"+nbt.getString("unlit_color"));
        UNLIT_COLOR = BlockColors.valueOf(nbt.getString("unlit_color"));
        LIT_COLOR = BlockColors.valueOf(nbt.getString("lit_color"));
    }
    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}
