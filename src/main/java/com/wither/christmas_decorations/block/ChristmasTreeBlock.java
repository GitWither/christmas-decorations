package com.wither.christmas_decorations.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class ChristmasTreeBlock extends Block {
    public static final BooleanProperty HAS_ORNAMENT = BooleanProperty.of("has_ornament");

    private static final VoxelShape OUTLINE = VoxelShapes.cuboid(0.5, 0.5,0.5, 1,1, 1);
    public ChristmasTreeBlock(Settings settings) {
        super(settings);
    }


    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(HAS_ORNAMENT, false);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(HAS_ORNAMENT);
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos down = pos.down();
        BlockState blockBelow = world.getBlockState(down);
        return !blockBelow.getCollisionShape(world, down).getFace(Direction.UP).isEmpty() || blockBelow.isSideSolidFullSquare(world, down, Direction.UP);
    }
}