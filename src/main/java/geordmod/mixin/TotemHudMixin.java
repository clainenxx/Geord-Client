package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.GeordConfig;
import geordmod.util.TotemTracker;

@Mixin(InGameHud.class)
public class TotemHudMixin {

    @Inject(method = "renderCrosshair", at = @At("TAIL"))
    private void onRenderSmallTotem(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        if (!GeordConfig.smallTotem) return;
        if (TotemTracker.floatingItem == null) return;

        long elapsed = System.currentTimeMillis() - TotemTracker.floatingItemTime;
        
        if (elapsed > 2000) {
            TotemTracker.floatingItem = null;
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        float progress = elapsed / 2000.0f;
        float sinCurve = (float) Math.pow(Math.sin(progress * Math.PI), 0.5);
        float scale = sinCurve * 3.0f;
        float yOffset = 20.0f - (progress * 60.0f);

        if (scale <= 0.0f) return;

        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        float centerX = screenWidth / 2.0f;
        float centerY = (screenHeight / 2.0f) + yOffset; 

        context.getMatrices().pushMatrix();
        
        context.getMatrices().translate(centerX, centerY);
        context.getMatrices().scale(scale, scale);
        context.getMatrices().translate(-centerX, -centerY);

        context.drawItem(TotemTracker.floatingItem, (int)centerX - 8, (int)centerY - 8);

        context.getMatrices().popMatrix();
    }
}