package com.wither.christmas_decorations.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;

public class SnowmanBlock extends Block {
    public SnowmanBlock(Settings settings) {
        super(settings);
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }
}
