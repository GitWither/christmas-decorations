package com.wither.christmas_decorations.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class SnowglobeBlock extends Block {

    public SnowglobeBlock(Settings settings) {
        super(settings);
    }


    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.25, pos.getY() + 0.5, pos.getZ() + 0.625, 0, 0, 0);
    }
}
