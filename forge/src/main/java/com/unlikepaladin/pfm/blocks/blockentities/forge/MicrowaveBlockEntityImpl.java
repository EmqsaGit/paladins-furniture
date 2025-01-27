package com.unlikepaladin.pfm.blocks.blockentities.forge;

import com.unlikepaladin.pfm.blocks.blockentities.MicrowaveBlockEntity;
import com.unlikepaladin.pfm.networking.forge.MicrowaveUpdatePacket;
import com.unlikepaladin.pfm.registry.forge.NetworkRegistryForge;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.chunk.WorldChunk;
import net.minecraftforge.fmllegacy.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class MicrowaveBlockEntityImpl  extends MicrowaveBlockEntity {
    public MicrowaveBlockEntityImpl(BlockPos pos, BlockState state) {
        super(pos, state);
    }
    public static void setActiveonClient(MicrowaveBlockEntity microwaveBlockEntity, boolean active) {
        microwaveBlockEntity.setActive(active);
        BlockPos pos = microwaveBlockEntity.getPos();
        WorldChunk chunk = Objects.requireNonNull(microwaveBlockEntity.getWorld()).getWorldChunk(pos);
        NetworkRegistryForge.PFM_CHANNEL.send(PacketDistributor.TRACKING_CHUNK.with(() -> chunk), new MicrowaveUpdatePacket(pos, active));
    }

    @Nullable
    @Override
    public BlockEntityUpdateS2CPacket toUpdatePacket() {
        return new BlockEntityUpdateS2CPacket(this.pos, BlockEntityUpdateS2CPacket.CAMPFIRE, this.toInitialChunkDataNbt());
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = super.toInitialChunkDataNbt();
        nbt.putBoolean("isActive", this.isActive);
        Inventories.writeNbt(nbt, this.inventory);
        return nbt;
    }


    @Override
    public void handleUpdateTag(NbtCompound tag) {
        this.readNbt(tag);
    }

    @Override
    public void onDataPacket(ClientConnection net, BlockEntityUpdateS2CPacket pkt) {
        super.onDataPacket(net, pkt);
        this.inventory = DefaultedList.ofSize(this.size(), ItemStack.EMPTY);
        this.isActive = pkt.getNbt().getBoolean("isActive");
        Inventories.readNbt(pkt.getNbt(), this.inventory);
    }

}
