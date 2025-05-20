package net.kaupenjoe.tutorialmod.entity.client;

import com.google.common.collect.Maps;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.MantisEntity;
import net.kaupenjoe.tutorialmod.entity.custom.MantisVariant;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class MantisRenderer extends MobEntityRenderer<MantisEntity, MantisRenderState, MantisModel> {
    private static final Map<MantisVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MantisVariant.class), map -> {
                map.put(MantisVariant.DEFAULT,
                        Identifier.of(TutorialMod.MOD_ID, "textures/entity/mantis/mantis.png"));
                map.put(MantisVariant.ORCHID,
                        Identifier.of(TutorialMod.MOD_ID, "textures/entity/mantis/mantis_orchid.png"));
            });

    public MantisRenderer(EntityRendererFactory.Context context) {
        super(context, new MantisModel(context.getPart(MantisModel.MANTIS)), 0.75f);
    }

    @Override
    public Identifier getTexture(MantisRenderState state) {
        return LOCATION_BY_VARIANT.get(state.variant);
    }

    @Override
    public void render(MantisRenderState state, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(state.baby) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(state, matrixStack, vertexConsumerProvider, i);
    }

    @Override
    public MantisRenderState createRenderState() {
        return new MantisRenderState();
    }

    @Override
    public void updateRenderState(MantisEntity livingEntity, MantisRenderState livingEntityRenderState, float f) {
        super.updateRenderState(livingEntity, livingEntityRenderState, f);
        livingEntityRenderState.idleAnimationState.copyFrom(livingEntity.idleAnimationState);
        livingEntityRenderState.variant = livingEntity.getVariant();
    }
}
