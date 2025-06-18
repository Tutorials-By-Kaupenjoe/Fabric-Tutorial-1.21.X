package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.util.ModTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        valueLookupBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.PINK_GARNET)
                .add(ModItems.RAW_PINK_GARNET)
                .add(Items.COAL)
                .add(Items.STICK)
                .add(Items.APPLE);

        valueLookupBuilder(ModTags.Items.PINK_GARNET_REPAIR)
                .add(ModItems.PINK_GARNET);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.PINK_GARNET_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.PINK_GARNET_PICKAXE);
        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.PINK_GARNET_SHOVEL);
        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.PINK_GARNET_AXE);
        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.PINK_GARNET_HOE);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .add(ModItems.PINK_GARNET_HELMET)
                .add(ModItems.PINK_GARNET_CHESTPLATE)
                .add(ModItems.PINK_GARNET_LEGGINGS)
                .add(ModItems.PINK_GARNET_BOOTS);

        valueLookupBuilder(ItemTags.TRIM_MATERIALS)
                .add(ModItems.PINK_GARNET);

        valueLookupBuilder(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.DRIFTWOOD_LOG.asItem())
                .add(ModBlocks.DRIFTWOOD_WOOD.asItem())
                .add(ModBlocks.STRIPPED_DRIFTWOOD_LOG.asItem())
                .add(ModBlocks.STRIPPED_DRIFTWOOD_WOOD.asItem());

        valueLookupBuilder(ItemTags.PLANKS)
                .add(ModBlocks.DRIFTWOOD_PLANKS.asItem());
    }
}
