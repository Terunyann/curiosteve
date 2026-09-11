package com.terunyann_.curiosteve.item.custom;

import com.terunyann_.curiosteve.tags.SteveSlotTags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;
import top.theillusivec4.curios.api.type.inventory.ICurioStacksHandler;

import java.util.Map;

public class Steve extends Item implements ICurioItem {

    public Steve(Properties properties) {
        super(properties);
    }

    private static ResourceLocation modifierId(SlotContext context) {
        return ResourceLocation.fromNamespaceAndPath(
                "curiosteve",
                "steve_"
                        + context.identifier()
                        + "_"
                        + context.index()
        );
    }

    @Override
    public void onEquip(
            SlotContext slotContext,
            ItemStack newStack,
            ItemStack stack
    ) {
        LivingEntity entity = slotContext.entity();

        CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {

            ResourceLocation id = modifierId(slotContext);

            for (String slotId : handler.getCurios().keySet()) {

                // ブラックリスト
                if (SteveSlotTags.isBlacklisted(slotId)) {
                    continue;
                }

                // Curiosに登録されているスロットタイプを取得
                var slotType = CuriosApi.getSlots(false).get(slotId);

                if (slotType == null) {
                    continue;
                }

                // 基本スロット数
                int baseSlots = slotType.getSize();

                if (baseSlots <= 0) {
                    continue;
                }

                // 基本スロット数だけ加算
                handler.addTransientSlotModifier(
                        slotId,
                        id,
                        baseSlots,
                        AttributeModifier.Operation.ADD_VALUE
                );
            }
        });
    }

    @Override
    public void onUnequip(
            SlotContext slotContext,
            ItemStack newStack,
            ItemStack stack
    ) {
        LivingEntity entity = slotContext.entity();

        CuriosApi.getCuriosInventory(entity).ifPresent(handler -> {

            ResourceLocation id = modifierId(slotContext);

            for (String slotId : handler.getCurios().keySet()) {
                handler.removeSlotModifier(slotId, id);
            }
        });
    }
}