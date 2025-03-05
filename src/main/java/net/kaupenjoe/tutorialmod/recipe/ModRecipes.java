package net.kaupenjoe.tutorialmod.recipe;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static final RecipeSerializer<GrowthChamberRecipe> GROWTH_CHAMBER_SERIALIZER = Registry.register(
            Registries.RECIPE_SERIALIZER, Identifier.of(TutorialMod.MOD_ID, "growth_chamber"),
                    new GrowthChamberRecipe.Serializer());
    public static final RecipeType<GrowthChamberRecipe> GROWTH_CHAMBER_TYPE = Registry.register(
            Registries.RECIPE_TYPE, Identifier.of(TutorialMod.MOD_ID, "growth_chamber"), new RecipeType<GrowthChamberRecipe>() {
                @Override
                public String toString() {
                    return "growth_chamber";
                }
            });

    public static void registerRecipes() {
        TutorialMod.LOGGER.info("Registering Custom Recipes for " + TutorialMod.MOD_ID);
    }
}
