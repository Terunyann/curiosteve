package com.terunyann_.curiosteve.item;

import com.terunyann_.curiosteve.CurioSteve;
import com.terunyann_.curiosteve.item.custom.Steve;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CurioSteve.MODID);

    public static final DeferredItem<Item> STEVE = ITEMS.register("steve",
            () -> new Steve(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<Item> ALEX = ITEMS.register("alex",
            () -> new Steve(new Item.Properties().stacksTo(1)));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
