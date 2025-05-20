package net.kaupenjoe.tutorialmod.item;

import net.minecraft.component.type.ConsumableComponent;
import net.minecraft.component.type.ConsumableComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.consume.ApplyEffectsConsumeEffect;

public class ModFoodComponents {
    public static final FoodComponent CAULIFLOWER = new FoodComponent.Builder().nutrition(3).saturationModifier(0.25f).build();

    public static final ConsumableComponent CAULIFLOWER_EFFECT = ConsumableComponents.food()
            .consumeEffect(new ApplyEffectsConsumeEffect(new StatusEffectInstance(StatusEffects.HEALTH_BOOST, 200), 0.15f)).build();


    public static final FoodComponent HONEY_BERRIES = new FoodComponent.Builder().nutrition(2).saturationModifier(0.15f).build();

}
