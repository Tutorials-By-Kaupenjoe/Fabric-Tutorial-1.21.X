package net.kaupenjoe.tutorialmod.networking.custom;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public record SyncPedestalBlockEntityS2CPayload(BlockPos blockPos, DefaultedList<ItemStack> inventory) implements CustomPayload {
    public static final Identifier SYNC_PEDESTAL_BLOCK_ENTITY_PAYLOAD_ID = Identifier.of(TutorialMod.MOD_ID, "sync_pedestal_block_entity");
    public static final CustomPayload.Id<SyncPedestalBlockEntityS2CPayload> ID = new CustomPayload.Id<>(SYNC_PEDESTAL_BLOCK_ENTITY_PAYLOAD_ID);

    // Define a PacketCodec for DefaultedList<ItemStack>
    public static final PacketCodec<RegistryByteBuf, DefaultedList<ItemStack>> ITEM_STACK_LIST_CODEC = new PacketCodec<>() {
        @Override
        public DefaultedList<ItemStack> decode(RegistryByteBuf buf) {
            // Read the size of the list
            int size = buf.readVarInt();
            // Create a DefaultedList with the specified size
            DefaultedList<ItemStack> list = DefaultedList.ofSize(size, ItemStack.EMPTY);
            // Read each ItemStack from the buffer
            for (int i = 0; i < size; i++) {
                // Decode the ItemStack, allowing empty stacks
                ItemStack stack = ItemStack.OPTIONAL_PACKET_CODEC.decode(buf);
                list.set(i, stack);
            }
            return list;
        }

        @Override
        public void encode(RegistryByteBuf buf, DefaultedList<ItemStack> list) {
            // Write the size of the list
            buf.writeVarInt(list.size());
            // Write each ItemStack to the buffer
            for (ItemStack stack : list) {
                // Encode the ItemStack, allowing empty stacks
                ItemStack.OPTIONAL_PACKET_CODEC.encode(buf, stack);
            }
        }
    };

    // Define the PacketCodec for SyncPedestalBlockEntityS2CPayload
    public static final PacketCodec<RegistryByteBuf, SyncPedestalBlockEntityS2CPayload> CODEC = PacketCodec.tuple(
            BlockPos.PACKET_CODEC, SyncPedestalBlockEntityS2CPayload::blockPos, // Handle BlockPos
            ITEM_STACK_LIST_CODEC, SyncPedestalBlockEntityS2CPayload::inventory, // Handle DefaultedList<ItemStack>
            SyncPedestalBlockEntityS2CPayload::new // Constructor
    );

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}
