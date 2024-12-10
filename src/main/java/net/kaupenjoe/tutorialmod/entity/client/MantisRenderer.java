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

public class MantisRenderer extends MobEntityRenderer<MantisEntity, MantisModel<MantisEntity>> {
    private static final Map<MantisVariant, Identifier> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(MantisVariant.class), map -> {
                map.put(MantisVariant.DEFAULT,
                        Identifier.of(TutorialMod.MOD_ID, "textures/entity/mantis/mantis.png"));
                map.put(MantisVariant.ORCHID,
                        Identifier.of(TutorialMod.MOD_ID, "textures/entity/mantis/mantis_orchid.png"));
            });

    public MantisRenderer(EntityRendererFactory.Context context) {
        super(context, new MantisModel<>(context.getPart(MantisModel.MANTIS)), 0.75f);
    }

    @Override
    public Identifier getTexture(MantisEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(MantisEntity livingEntity, float f, float g, MatrixStack matrixStack,
                       VertexConsumerProvider vertexConsumerProvider, int i) {
        if(livingEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(livingEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}
