package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Mouse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.mod.FexClientMod;

@Mixin(Mouse.class)
public class MouseMixin {

    @Shadow private double cursorDeltaX;
    @Shadow private double cursorDeltaY;

    private double smoothedX = 0;
    private double smoothedY = 0;

    @Inject(method = "onMouseScroll", at = @At("HEAD"), cancellable = true)
    private void onScroll(long window, double horizontal, double vertical, CallbackInfo ci) {
        if (FexClientMod.isZooming() && vertical != 0) {
            FexClientMod.onScroll(vertical);
            ci.cancel(); 
        }
    }

    @Inject(method = "updateMouse", at = @At("HEAD"))
    private void onUpdateMouse(CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (FexClientMod.isFreelooking) {
            double sensitivity = client.options.getMouseSensitivity().getValue() * 0.6 + 0.2;
            double mult = sensitivity * sensitivity * sensitivity * 8.0;
            
            FexClientMod.freelookYaw += (float) (this.cursorDeltaX * mult * 0.15);
            FexClientMod.freelookPitch += (float) (this.cursorDeltaY * mult * 0.15);
            FexClientMod.freelookPitch = Math.max(-90.0f, Math.min(90.0f, FexClientMod.freelookPitch));
            
            this.cursorDeltaX = 0;
            this.cursorDeltaY = 0;
            
            this.smoothedX = 0;
            this.smoothedY = 0;

        } else if (FexClientMod.isZooming()) {
            double sensitivityScale = FexClientMod.currentTargetZoom; 
            this.cursorDeltaX *= sensitivityScale;
            this.cursorDeltaY *= sensitivityScale;

            double cinematicWeight = geordmod.util.GeordConfig.zoomSmoothness;
            this.smoothedX = this.smoothedX * cinematicWeight + this.cursorDeltaX * (1.0 - cinematicWeight);
            this.smoothedY = this.smoothedY * cinematicWeight + this.cursorDeltaY * (1.0 - cinematicWeight);

            this.cursorDeltaX = this.smoothedX;
            this.cursorDeltaY = this.smoothedY;
            
        } else {
            this.smoothedX = 0;
            this.smoothedY = 0;
        }
    }
}