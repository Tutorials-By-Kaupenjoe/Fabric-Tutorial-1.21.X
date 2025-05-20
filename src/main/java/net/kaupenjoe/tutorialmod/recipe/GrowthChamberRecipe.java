package net.kaupenjoe.tutorialmod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeBookCategories;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.world.World;

public record GrowthChamberRecipe(Ingredient inputItem, ItemStack output) implements Recipe<GrowthChamberRecipeInput> {
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.of();
        list.add(this.inputItem);
        return list;
    }

    // read Recipe JSON files --> new GrowthChamberRecipe

    @Override
    public boolean matches(GrowthChamberRecipeInput input, World world) {
        if(world.isClient()) {
            return false;
        }

        return inputItem.test(input.getStackInSlot(0));
    }

    @Override
    public ItemStack craft(GrowthChamberRecipeInput input, RegistryWrapper.WrapperLookup lookup) {
        return output.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<GrowthChamberRecipeInput>> getSerializer() {
        return ModRecipes.GROWTH_CHAMBER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<GrowthChamberRecipeInput>> getType() {
        return ModRecipes.GROWTH_CHAMBER_TYPE;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forSingleSlot(inputItem);
    }

    @Override
    public RecipeBookCategory getRecipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }

    public static class Serializer implements RecipeSerializer<GrowthChamberRecipe> {
        public static final MapCodec<GrowthChamberRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC.fieldOf("ingredient").forGetter(GrowthChamberRecipe::inputItem),
                ItemStack.CODEC.fieldOf("result").forGetter(GrowthChamberRecipe::output)
        ).apply(inst, GrowthChamberRecipe::new));

        public static final PacketCodec<RegistryByteBuf, GrowthChamberRecipe> STREAM_CODEC =
                PacketCodec.tuple(
                        Ingredient.PACKET_CODEC, GrowthChamberRecipe::inputItem,
                        ItemStack.PACKET_CODEC, GrowthChamberRecipe::output,
                        GrowthChamberRecipe::new);

        @Override
        public MapCodec<GrowthChamberRecipe> codec() {
            return CODEC;
        }

        @Override
        public PacketCodec<RegistryByteBuf, GrowthChamberRecipe> packetCodec() {
            return STREAM_CODEC;
        }
    }
}
