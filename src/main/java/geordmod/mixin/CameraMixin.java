package geordmod.mixin;

import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;
import geordmod.mod.FexClientMod;

@Mixin(Camera.class)
public class CameraMixin {
    
    @ModifyArgs(method = "update", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/render/Camera;setRotation(FF)V"))
    private void applyFreelookRotation(Args args) {
        if (FexClientMod.isFreelooking) {
            args.set(0, FexClientMod.freelookYaw);
            args.set(1, FexClientMod.freelookPitch);
        }
    }
}