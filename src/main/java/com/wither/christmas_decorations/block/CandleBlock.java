package com.wither.christmas_decorations.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CandleBlock extends Block {
    public static final BooleanProperty lit;
    public CandleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateFactory.getDefaultState().with(lit, false));
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory) {
        stateFactory.with(new Property[]{lit});
    }

    static {
        lit = Properties.LIT;
    }
}
