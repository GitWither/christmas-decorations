package com.wither.christmas_decorations.mixin;

import net.minecraft.client.particle.BlockFallingDustParticle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BlockFallingDustParticle.class)
public class TestMixin {

    @Inject(method = "tick", at = @At(value = "HEAD"))
    public void test(CallbackInfo test) {
        System.out.println();
    }
}
