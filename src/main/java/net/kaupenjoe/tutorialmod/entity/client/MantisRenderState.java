package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.entity.custom.MantisVariant;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.entity.AnimationState;

public class MantisRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public MantisVariant variant;
}
