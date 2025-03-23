package net.kaupenjoe.tutorialmod.block.entity.custom;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PlayerLookup;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.kaupenjoe.tutorialmod.block.entity.ImplementedInventory;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.networking.custom.SyncPedestalBlockEntityS2CPayload;
import net.kaupenjoe.tutorialmod.screen.custom.PedestalScreenHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class PedestalBlockEntity extends BlockEntity implements ImplementedInventory, ExtendedScreenHandlerFactory<BlockPos> {
    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);
    private float rotation = 0;

    public PedestalBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.PEDESTAL_BE, pos, state);
    }

    public static void sendSyncPacket(World world, BlockPos blockpos, DefaultedList<ItemStack> inventory) {
        if (world.isClient()) return;
        SyncPedestalBlockEntityS2CPayload payload = new SyncPedestalBlockEntityS2CPayload(blockpos, inventory);

        for (ServerPlayerEntity serverPlayerEntity : PlayerLookup.world((ServerWorld) world)) {
            ServerPlayNetworking.send(serverPlayerEntity, payload);
        }
    }

    public static void onSyncPacket(SyncPedestalBlockEntityS2CPayload payload, ClientPlayNetworking.Context context) {
        ClientWorld world = context.client().world;

        if (world == null) return; // Ensure the world is not null

        // Retrieve the BlockEntity at the specified BlockPos
        BlockEntity blockEntity = world.getBlockEntity(payload.blockPos());
        if (blockEntity instanceof PedestalBlockEntity pedestalBlockEntity) {
            // Update the BlockEntity's inventory with the payload data
            pedestalBlockEntity.setStack(0, payload.inventory().getFirst());

            // Mark the BlockEntity for re-rendering (optional, if needed)
            world.updateListeners(payload.blockPos(), blockEntity.getCachedState(), blockEntity.getCachedState(), Block.NOTIFY_ALL);
        }
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    public float getRenderingRotation() {
        rotation += 0.5f;
        if(rotation >= 360) {
            rotation = 0;
        }
        return rotation;
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        Inventories.writeNbt(nbt, inventory, registryLookup);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        Inventories.readNbt(nbt, inventory, registryLookup);
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return this.pos;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Pedestal");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new PedestalScreenHandler(syncId, playerInventory, this.pos);
    }


    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(RegistryWrapper.WrapperLookup registryLookup) {
        return createNbt(registryLookup);
    }

    @Override
    public void markDirty() {
        super.markDirty();
        syncInventory();
    }

    public void syncInventory() {
        if (this.world != null && !this.world.isClient) {
            sendSyncPacket(this.world, this.pos, this.inventory);
        }
    }
}
