package com.wither.christmas_decorations.block;

import net.minecraft.SharedConstants;
import net.minecraft.block.*;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

import java.util.Random;

public class SnowglobeBlock extends Block {

    private static final VoxelShape GENERAL_SHAPE = VoxelShapes.combine(
            Block.createCuboidShape(2, 0, 2, 14, 2, 14),
            Block.createCuboidShape(3, 2, 3, 13, 12, 13),
            BooleanBiFunction.OR
    );

    public SnowglobeBlock(Settings settings) {
        super(settings);
    }


    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return GENERAL_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        //Here I'm assuming that any other collisions aren't from entities. I don't know whether there are other collision types.
        if (context instanceof EntityShapeContext && ((EntityShapeContext)context).getEntity() == null) {
            return VoxelShapes.empty();
        }
        else return this.getOutlineShape(state, world, pos, context);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos down = pos.down();
        BlockState blockBelow = world.getBlockState(down);
        return !blockBelow.getCollisionShape(world, down).getFace(Direction.UP).isEmpty() || blockBelow.isSideSolidFullSquare(world, down, Direction.UP);
    }



    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        double snowX = pos.getX() + random.nextDouble() * 0.5 + 0.25;
        double snowY = pos.getY() + 0.7;
        double snowZ = pos.getZ() + random.nextDouble() * 0.5 + 0.25;


        world.addParticle(ParticleTypes.SMOKE, pos.getX() + 0.25, pos.getY() + 0.5, pos.getZ() + 0.625, 0, 0, 0);

        world.addParticle(new BlockStateParticleEffect(ParticleTypes.FALLING_DUST, Blocks.SNOW_BLOCK.getDefaultState()), snowX, snowY, snowZ, 0.0, 0, 0.0);
    }
}
