package net.kaupenjoe.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.entity.ModEntities;
import net.kaupenjoe.tutorialmod.item.custom.ChiselItem;
import net.kaupenjoe.tutorialmod.item.custom.HammerItem;
import net.kaupenjoe.tutorialmod.item.custom.ModArmorItem;
import net.kaupenjoe.tutorialmod.item.custom.TomahawkItem;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.item.*;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ModItems {
    public static final Item PINK_GARNET = registerItem("pink_garnet", Item::new);
    public static final Item RAW_PINK_GARNET = registerItem("raw_pink_garnet", Item::new);

    public static final Item CHISEL = registerItem("chisel", setting -> new ChiselItem(setting.maxDamage(32)));
    public static final Item CAULIFLOWER = registerItem("cauliflower", setting -> new Item(setting
            .food(ModFoodComponents.CAULIFLOWER, ModFoodComponents.CAULIFLOWER_EFFECT)) {
        @Override
        public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
            textConsumer.accept(Text.translatable("tooltip.tutorialmod.cauliflower.tooltip"));
            super.appendTooltip(stack, context, displayComponent, textConsumer, type);
        }
    });

    public static final Item STARLIGHT_ASHES = registerItem("starlight_ashes", Item::new);

    public static final Item PINK_GARNET_SWORD = registerItem("pink_garnet_sword",
            setting -> new Item(setting.sword(ModToolMaterials.PINK_GARNET, 3, -2.4f)));
    public static final Item PINK_GARNET_PICKAXE = registerItem("pink_garnet_pickaxe",
            setting -> new Item(setting.pickaxe(ModToolMaterials.PINK_GARNET, 1, -2.8f)));
    public static final Item PINK_GARNET_SHOVEL = registerItem("pink_garnet_shovel",
            setting -> new ShovelItem(ModToolMaterials.PINK_GARNET, 1.5f, -3.0f, setting));
    public static final Item PINK_GARNET_AXE = registerItem("pink_garnet_axe",
            setting -> new AxeItem(ModToolMaterials.PINK_GARNET, 6, -3.2f, setting));
    public static final Item PINK_GARNET_HOE = registerItem("pink_garnet_hoe",
            setting -> new HoeItem(ModToolMaterials.PINK_GARNET, 0, -3f, setting));

    public static final Item PINK_GARNET_HAMMER = registerItem("pink_garnet_hammer",
            setting -> new HammerItem(ModToolMaterials.PINK_GARNET, 7, -3.4f, setting));

    public static final Item PINK_GARNET_HELMET = registerItem("pink_garnet_helmet",
            setting -> new ModArmorItem(setting.armor(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.HELMET)));
    public static final Item PINK_GARNET_CHESTPLATE = registerItem("pink_garnet_chestplate",
            setting -> new Item(setting.armor(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.CHESTPLATE)));
    public static final Item PINK_GARNET_LEGGINGS = registerItem("pink_garnet_leggings",
            setting -> new Item(setting.armor(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.LEGGINGS)));
    public static final Item PINK_GARNET_BOOTS = registerItem("pink_garnet_boots",
            setting -> new Item(setting.armor(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL, EquipmentType.BOOTS)));

    public static final Item PINK_GARNET_HORSE_ARMOR = registerItem("pink_garnet_horse_armor",
            setting -> new Item(setting.horseArmor(ModArmorMaterials.PINK_GARNET_ARMOR_MATERIAL).maxCount(1)));
    public static final Item KAUPEN_SMITHING_TEMPLATE = registerItem("kaupen_armor_trim_smithing_template",
            SmithingTemplateItem::of);

    public static final Item KAUPEN_BOW = registerItem("kaupen_bow",
            setting -> new BowItem(setting.maxDamage(500)));

    public static final Item BAR_BRAWL_MUSIC_DISC = registerItem("bar_brawl_music_disc",
            setting -> new Item(setting.jukeboxPlayable(ModSounds.BAR_BRAWL_KEY).maxCount(1)));

    public static final Item CAULIFLOWER_SEEDS = registerItem("cauliflower_seeds",
            setting -> new BlockItem(ModBlocks.CAULIFLOWER_CROP, setting));

    public static final Item HONEY_BERRIES = registerItem("honey_berries",
            setting -> new BlockItem(ModBlocks.HONEY_BERRY_BUSH, setting.food(ModFoodComponents.HONEY_BERRIES)));

    public static final Item MANTIS_SPAWN_EGG = registerItem("mantis_spawn_egg",
            setting -> new SpawnEggItem(ModEntities.MANTIS, setting));

    public static final Item TOMAHAWK = registerItem("tomahawk",
            setting -> new TomahawkItem(setting.maxCount(16)));

    public static final Item SPECTRE_STAFF = registerItem("spectre_staff",
            setting -> new Item(setting.maxCount(1)));


    private static Item registerItem(String name, Function<Item.Settings, Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
                function.apply(new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(TutorialMod.MOD_ID, name)))));
    }
    public static void registerModItems() {
        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
            entries.add(PINK_GARNET);
            entries.add(RAW_PINK_GARNET);
        });
    }
}