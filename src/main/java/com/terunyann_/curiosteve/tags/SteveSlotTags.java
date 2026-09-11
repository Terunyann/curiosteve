package com.terunyann_.curiosteve.tags;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SteveSlotTags extends SimpleJsonResourceReloadListener {

    private static final Gson GSON = new Gson();

    private static final Set<String> BLACKLIST = new HashSet<>();

    public SteveSlotTags() {
        super(GSON, "tags/curios_slots");
    }

    @Override
    protected void apply(
            Map<ResourceLocation, JsonElement> files,
            ResourceManager resourceManager,
            ProfilerFiller profiler
    ) {
        BLACKLIST.clear();

        for (JsonElement element : files.values()) {
            if (!element.isJsonObject()) {
                continue;
            }

            JsonObject json = element.getAsJsonObject();

            if (!json.has("values")) {
                continue;
            }

            JsonArray values = json.getAsJsonArray("values");

            for (JsonElement value : values) {
                if (value.isJsonPrimitive()) {
                    BLACKLIST.add(value.getAsString());
                }
            }
        }
    }

    public static boolean isBlacklisted(String slotId) {
        return BLACKLIST.contains(slotId);
    }
}