package com.wither.christmas_decorations.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.util.hit.BlockHitResult;

import java.util.Random;

public class CandleBlock extends Block implements Waterloggable {
    private static final BooleanProperty lit;

    public CandleBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateFactory.getDefaultState().with(lit, false));
    }

    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockHitResult direction) {
        ItemStack item = playerEntity.getStackInHand(hand);
        if (item.getItem() == Items.FLINT_AND_STEEL && !blockState.get(lit)) {
            if (!world.isClient) {
                world.playSound(null, blockPos, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCK, 1.0F, 1.0F);
                world.setBlockState(blockPos, this.getDefaultState().with(lit, true));
                item.applyDamage(1, playerEntity);
            }
            return true;
        } else {
            return super.activate(blockState, world, blockPos, playerEntity, hand, direction);
        }
    }

    public int getLuminance(BlockState blockState) {
        return blockState.get(lit) ? super.getLuminance(blockState) : 0;
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState blockState, World world, BlockPos blockPos, Random random) {
        if (blockState.get(lit)) {
            world.addParticle(ParticleTypes.FLAME, blockPos.getX() + 0.5, blockPos.getY(), blockPos.getZ() + 0.5, 0.0, 0.1, 0.0);
        }
    }

    protected void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory) {
        stateFactory.with(new Property[]{lit});
    }

    static {
        lit = Properties.LIT;
    }
}
