package com.wither.christmas_decorations.mixin;

import com.wither.christmas_decorations.Main;
import net.minecraft.block.BlockState;
import net.minecraft.client.particle.BlockFallingDustParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(BlockFallingDustParticle.Factory.class)
public class BlockFallingDustParticleMixin {

    @Redirect(at = @At(value = "INVOKE", target = "net/minecraft/block/BlockState.isAir()Z"), method = "createParticle(Lnet/minecraft/particle/BlockStateParticleEffect;Lnet/minecraft/client/world/ClientWorld;DDDDDD)Lnet/minecraft/client/particle/Particle;")
    public boolean checkForSnowglobe(BlockState block) {
        return !block.isAir() || !block.isOf(Main.SNOWGLBOE);
    }
}
