package geordmod.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.Camera;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import geordmod.mod.FexClientMod;

@Mixin(GameRenderer.class)
public class ZoomMixin {
    private float currentFovMultiplier = 1.0f;
    private long lastTime = 0;

    @Inject(method = "getFov", at = @At("RETURN"), cancellable = true)
    private void applySmoothZoom(Camera camera, float tickDelta, boolean changingFov, CallbackInfoReturnable<Float> cir) {
        
        boolean isZooming = FexClientMod.isZooming();
        float targetFovMultiplier = isZooming ? FexClientMod.currentTargetZoom : 1.0f;

        long currentTime = Util.getMeasuringTimeMs();
        if (this.lastTime == 0) this.lastTime = currentTime;
        long dt = currentTime - this.lastTime;
        this.lastTime = currentTime;

        float smoothingSpeed = geordmod.util.GeordConfig.zoomSpeed;
        float lerpFactor = Math.min(1.0f, dt * smoothingSpeed);
        
        this.currentFovMultiplier += (targetFovMultiplier - this.currentFovMultiplier) * lerpFactor;

        if (Math.abs(this.currentFovMultiplier - targetFovMultiplier) < 0.001f) {
            this.currentFovMultiplier = targetFovMultiplier;
        }

        if (this.currentFovMultiplier != 1.0f) {
            cir.setReturnValue(cir.getReturnValue() * this.currentFovMultiplier);
        }
    }
}