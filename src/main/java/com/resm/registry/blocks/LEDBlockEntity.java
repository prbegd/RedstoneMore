package com.resm.registry.blocks;

import com.resm.RedstoneMore;
import com.resm.registry.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class LEDBlockEntity extends BlockEntity {
    private BlockColorsEnum UNLIT_COLOR;
    private BlockColorsEnum LIT_COLOR;

    public LEDBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.LED_BLOCK_ENTITY, pos, state);
        if (world != null) {
            world.updateListeners(pos, state, state, Block.NOTIFY_LISTENERS);
        }
        UNLIT_COLOR = BlockColorsEnum.BLUE;
        LIT_COLOR = BlockColorsEnum.RED;
        RedstoneMore.LOGGER.info("------------LEDBlockEntity");
    }

    @Override
    public void writeNbt(NbtCompound nbt) {
        RedstoneMore.LOGGER.info("{}{}{}", nbt, LIT_COLOR.name(), UNLIT_COLOR.name());
        nbt.putString("lit_color", LIT_COLOR.name());
        nbt.putString("unlit_color", UNLIT_COLOR.name());
        super.writeNbt(nbt);
        RedstoneMore.LOGGER.info("------------writeNBT");
        RedstoneMore.LOGGER.info("{}{}{}", nbt, LIT_COLOR.name(), UNLIT_COLOR.name());
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        RedstoneMore.LOGGER.info("------------readNBT");
        RedstoneMore.LOGGER.info("readNBT" + nbt.getString("lit_color") + "|and|" + nbt.getString("unlit_color"));
        UNLIT_COLOR = BlockColorsEnum.valueOf(nbt.getString("unlit_color"));
        LIT_COLOR = BlockColorsEnum.valueOf(nbt.getString("lit_color"));
    }

    public BlockColorsEnum getUNLIT_COLOR() {
        return this.UNLIT_COLOR;
    }

    public void setUNLIT_COLOR(BlockColorsEnum blockColors) {
        this.UNLIT_COLOR = blockColors;
    }

    public BlockColorsEnum getLIT_COLOR() {
        return this.LIT_COLOR;
    }

    public void setLIT_COLOR(BlockColorsEnum blockColors) {
        this.LIT_COLOR = blockColors;
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
