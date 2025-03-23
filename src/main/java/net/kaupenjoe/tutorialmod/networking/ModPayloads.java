package net.kaupenjoe.tutorialmod.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.networking.custom.SyncPedestalBlockEntityS2CPayload;

public class ModPayloads {
    public static void registerModPayloads() {
        PayloadTypeRegistry.playS2C().register(SyncPedestalBlockEntityS2CPayload.ID, SyncPedestalBlockEntityS2CPayload.CODEC);
        TutorialMod.LOGGER.info("Registering Mod Payloads for " + TutorialMod.MOD_ID);
    }
}
