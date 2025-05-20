package net.kaupenjoe.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.custom.*;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.kaupenjoe.tutorialmod.world.tree.ModSaplingGenerators;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;

import java.util.function.Function;

public class ModBlocks {
    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            properties -> new Block(properties.strength(4f)
                    .requiresTool().sounds(BlockSoundGroup.AMETHYST_BLOCK)));
    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
            properties -> new Block(properties.strength(3f)
                    .requiresTool()));

    public static final Block PINK_GARNET_ORE = registerBlock("pink_garnet_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(2, 5),
                    properties.strength(3f).requiresTool()));
    public static final Block PINK_GARNET_DEEPSLATE_ORE = registerBlock("pink_garnet_deepslate_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(3, 6),
                    properties.strength(4f).requiresTool().sounds(BlockSoundGroup.DEEPSLATE)));

    public static final Block PINK_GARNET_END_ORE = registerBlock("pink_garnet_end_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(4, 8),
                    properties.strength(7f).requiresTool()));
    public static final Block PINK_GARNET_NETHER_ORE = registerBlock("pink_garnet_nether_ore",
            properties -> new ExperienceDroppingBlock(UniformIntProvider.create(1, 5),
                    properties.strength(3f).requiresTool()));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(1f).requiresTool().sounds(ModSounds.MAGIC_BLOCK_SOUNDS)));

    public static final Block PINK_GARNET_STAIRS = registerBlock("pink_garnet_stairs",
            properties -> new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(),
                    properties.strength(2f).requiresTool()));
    public static final Block PINK_GARNET_SLAB = registerBlock("pink_garnet_slab",
            properties -> new SlabBlock(properties.strength(2f).requiresTool()));

    public static final Block PINK_GARNET_BUTTON = registerBlock("pink_garnet_button",
            properties -> new ButtonBlock(BlockSetType.IRON, 2, properties.strength(2f).requiresTool().noCollision()));
    public static final Block PINK_GARNET_PRESSURE_PLATE = registerBlock("pink_garnet_pressure_plate",
            properties -> new PressurePlateBlock(BlockSetType.IRON, properties.strength(2f).requiresTool()));

    public static final Block PINK_GARNET_FENCE = registerBlock("pink_garnet_fence",
            properties -> new FenceBlock(properties.strength(2f).requiresTool()));
    public static final Block PINK_GARNET_FENCE_GATE = registerBlock("pink_garnet_fence_gate",
            properties -> new FenceGateBlock(WoodType.ACACIA, properties.strength(2f).requiresTool()));
    public static final Block PINK_GARNET_WALL = registerBlock("pink_garnet_wall",
            properties -> new WallBlock(properties.strength(2f).requiresTool()));

    public static final Block PINK_GARNET_DOOR = registerBlock("pink_garnet_door",
            properties -> new DoorBlock(BlockSetType.IRON, properties.strength(2f).requiresTool().nonOpaque()));
    public static final Block PINK_GARNET_TRAPDOOR = registerBlock("pink_garnet_trapdoor",
            properties -> new TrapdoorBlock(BlockSetType.IRON, properties.strength(2f).requiresTool().nonOpaque()));

    public static final Block PINK_GARNET_LAMP = registerBlock("pink_garnet_lamp",
            properties -> new PinkGarnetLampBlock(properties
                    .strength(1f).requiresTool().luminance(state -> state.get(PinkGarnetLampBlock.CLICKED) ? 15 : 0)));

    public static final Block CAULIFLOWER_CROP = registerBlockWithoutBlockItem("cauliflower_crop",
            properties -> new CauliflowerCropBlock(properties.noCollision()
                    .ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP)
                    .pistonBehavior(PistonBehavior.DESTROY)));
    public static final Block HONEY_BERRY_BUSH = registerBlockWithoutBlockItem("honey_berry_bush",
            properties -> new HoneyBerryBushBlock(properties.mapColor(MapColor.DARK_GREEN).ticksRandomly()
                    .noCollision().sounds(BlockSoundGroup.SWEET_BERRY_BUSH).pistonBehavior(PistonBehavior.DESTROY)));


    public static final Block DRIFTWOOD_LOG = registerBlock("driftwood_log",
            properties -> new PillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block DRIFTWOOD_WOOD = registerBlock("driftwood_wood",
            properties -> new PillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block STRIPPED_DRIFTWOOD_LOG = registerBlock("stripped_driftwood_log",
            properties -> new PillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));
    public static final Block STRIPPED_DRIFTWOOD_WOOD = registerBlock("stripped_driftwood_wood",
            properties -> new PillarBlock(properties
                    .instrument(NoteBlockInstrument.BASS)
                    .strength(2.0F).sounds(BlockSoundGroup.WOOD).burnable()));

    public static final Block DRIFTWOOD_PLANKS = registerBlock("driftwood_planks",
            properties -> new Block(properties.strength(3f)));
    public static final Block DRIFTWOOD_LEAVES = registerBlock("driftwood_leaves",
            properties -> new LeavesBlock(properties
                    .mapColor(MapColor.DARK_GREEN).strength(0.2F).ticksRandomly()
                    .sounds(BlockSoundGroup.AZALEA_LEAVES).nonOpaque()
                    .allowsSpawning(Blocks::canSpawnOnLeaves).suffocates(Blocks::never)
                    .blockVision(Blocks::never).burnable().pistonBehavior(PistonBehavior.DESTROY)
                    .solidBlock(Blocks::never)));

    public static final Block DRIFTWOOD_SAPLING = registerBlock("driftwood_sapling",
            properties -> new ModSaplingBlock(ModSaplingGenerators.DRIFTWOOD, properties.mapColor(MapColor.DARK_GREEN)
                    .noCollision().ticksRandomly().breakInstantly()
                    .sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY), Blocks.STONE));

    public static final Block CHAIR = registerBlock("chair",
            properties -> new ChairBlock(properties.nonOpaque()));

    public static final Block PEDESTAL = registerBlock("pedestal",
            properties -> new PedestalBlock(properties.nonOpaque()));

    public static final Block GROWTH_CHAMBER = registerBlock("growth_chamber", GrowthChamberBlock::new);



    private static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), toRegister);
    }

    private static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(TutorialMod.MOD_ID, name)))));
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(ModBlocks.PINK_GARNET_BLOCK);
            entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
        });
    }
}
