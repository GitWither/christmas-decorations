package com.wither.christmas_decorations;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;

public class MainClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(Main.CHRISTMAS_TREE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Main.SNOWMAN, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(Main.SNOWGLBOE, RenderLayer.getCutout());
    }
}
