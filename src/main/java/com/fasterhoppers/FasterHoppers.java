package com.fasterhoppers;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Blocks;

public class FasterHoppers implements ModInitializer {
    public static final String MOD_ID = "fasterhoppers";
    public static final Component SUPER_HOPPER_NAME = Component.translatable("container.fasterhoppers.super_hopper");
    private static final Identifier SUPER_HOPPER_ID = id("super_hopper");
    public static final Item SUPER_HOPPER = Registry.register(
            BuiltInRegistries.ITEM,
            SUPER_HOPPER_ID,
            new BlockItem(Blocks.HOPPER, new Item.Properties()
                    .setId(ResourceKey.create(Registries.ITEM, SUPER_HOPPER_ID))
                    .component(DataComponents.CUSTOM_NAME, SUPER_HOPPER_NAME))
    );

    @Override
    public void onInitialize() {
        ResourceKey<CreativeModeTab> redstoneTab = ResourceKey.create(
                Registries.CREATIVE_MODE_TAB,
                Identifier.fromNamespaceAndPath("minecraft", "redstone_blocks")
        );
        CreativeModeTabEvents.modifyOutputEvent(redstoneTab)
            .register(output -> output.accept(SUPER_HOPPER));
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}