package geordmod.mixin;

import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import geordmod.mod.FexClientMod;

@Mixin(Camera.class)
public class CameraMixin {
    
    // Mencegat perhitungan rotasi kamera di dalam game (Aman & Anti-Crash)
    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V"))
    private void applyFreelookRotation(Args args) {
        if (FexClientMod.isFreelooking) {
            // Memaksa kamera menengok ke arah Freelook kita, bukan ke wajah player
            args.set(0, FexClientMod.freelookYaw);
            args.set(1, FexClientMod.freelookPitch);
        }
    }
}