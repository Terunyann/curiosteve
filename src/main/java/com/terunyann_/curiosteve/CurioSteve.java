package com.terunyann_.curiosteve;

import com.terunyann_.curiosteve.item.ModCreativeModeTab;
import com.terunyann_.curiosteve.item.ModItems;
import com.terunyann_.curiosteve.tags.SteveSlotTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(CurioSteve.MODID)
public class CurioSteve {
    public static final String MODID = "curiosteve";
    public static final Logger LOGGER = LogUtils.getLogger();

    public CurioSteve(IEventBus modEventBus, ModContainer modContainer) {
        modEventBus.addListener(this::commonSetup);

        NeoForge.EVENT_BUS.register(this);

        ModCreativeModeTab.register(modEventBus);
        ModItems.register(modEventBus);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        NeoForge.EVENT_BUS.addListener(this::addReloadListener);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
    }

    private void addReloadListener(AddReloadListenerEvent event) {
        event.addListener(new SteveSlotTags());
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
