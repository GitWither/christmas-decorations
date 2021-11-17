package com.wither.christmas_decorations.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class SnowmanBlock extends Block {
    private static final DirectionProperty FACING = HorizontalFacingBlock.FACING;
    private static final BooleanProperty HAS_EARMUFFS = BooleanProperty.of("has_earmuffs");

    private static final VoxelShape SHAPE = Stream.of(
            Block.createCuboidShape(2, 0, 2, 14, 12, 14),
            Block.createCuboidShape(3, 12, 3, 13, 22, 13),
            Block.createCuboidShape(4, 22, 4, 12, 30, 12)
    ).reduce((v1, v2) -> VoxelShapes.combineAndSimplify(v1, v2, BooleanBiFunction.OR)).get();

    public SnowmanBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(HAS_EARMUFFS, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(HAS_EARMUFFS);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView world, BlockPos pos) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
}
