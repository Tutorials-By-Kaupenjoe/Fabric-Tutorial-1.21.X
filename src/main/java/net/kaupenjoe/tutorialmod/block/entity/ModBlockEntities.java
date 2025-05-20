package net.kaupenjoe.tutorialmod.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.entity.custom.GrowthChamberBlockEntity;
import net.kaupenjoe.tutorialmod.block.entity.custom.PedestalBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<PedestalBlockEntity> PEDESTAL_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "pedestal_be"),
                    FabricBlockEntityTypeBuilder.create(PedestalBlockEntity::new, ModBlocks.PEDESTAL).build(null));

    public static final BlockEntityType<GrowthChamberBlockEntity> GROWTH_CHAMBER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "growth_chamber_be"),
                    FabricBlockEntityTypeBuilder.create(GrowthChamberBlockEntity::new, ModBlocks.GROWTH_CHAMBER).build(null));


    public static void registerBlockEntities() {
        TutorialMod.LOGGER.info("Registering Block Entities for " + TutorialMod.MOD_ID);
    }
}
