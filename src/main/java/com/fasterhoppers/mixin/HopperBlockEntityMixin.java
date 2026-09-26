package com.fasterhoppers.mixin;

import com.fasterhoppers.FasterHoppers;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.HopperBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.BooleanSupplier;

@Mixin(HopperBlockEntity.class)
abstract class HopperBlockEntityMixin {
    @Shadow
    private void setCooldown(int cooldown) {
    }

    @Inject(
            method = "tryMoveItems",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/entity/HopperBlockEntity;setCooldown(I)V",
                    shift = At.Shift.AFTER
            )
    )
    private static void fasterhoppers$shortenSuperHopperCooldown(
            Level level,
            BlockPos pos,
            BlockState state,
            HopperBlockEntity hopper,
            BooleanSupplier shouldContinue,
            CallbackInfoReturnable<Boolean> cir
    ) {
        if (FasterHoppers.SUPER_HOPPER_NAME.equals(hopper.getCustomName())) {
            ((HopperBlockEntityMixin) (Object) hopper).setCooldown(1);
        }
    }
}