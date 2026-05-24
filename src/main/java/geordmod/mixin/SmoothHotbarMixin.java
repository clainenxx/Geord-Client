package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import java.util.function.Function;

@Mixin(InGameHud.class)
public class SmoothHotbarMixin {
    
    private float smoothSlot = -1;
    private long lastTime = 0;

    @Redirect(
        method = "renderHotbar", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Ljava/util/function/Function;Lnet/minecraft/util/Identifier;IIII)V"
        )
    )
    private void redirectDrawHotbarSelection(DrawContext context, Function<Identifier, RenderLayer> renderLayers, Identifier sprite, int x, int y, int width, int height) {
        
        if (sprite.getPath().equals("hud/hotbar_selection")) {
            MinecraftClient client = MinecraftClient.getInstance();
            
            if (client.player != null) {
                int targetSlot = client.player.getInventory().selectedSlot;
                
                if (this.smoothSlot == -1) {
                    this.smoothSlot = targetSlot;
                }

                long currentTime = Util.getMeasuringTimeMs();
                if (this.lastTime == 0) this.lastTime = currentTime;
                long dt = currentTime - this.lastTime;
                this.lastTime = currentTime;

                float slideSpeed = geordmod.util.GeordConfig.smoothHotbarSpeed;
                float lerpFactor = Math.min(1.0f, dt * slideSpeed);
                
                this.smoothSlot += (targetSlot - this.smoothSlot) * lerpFactor;

                if (Math.abs(this.smoothSlot - targetSlot) < 0.001f) {
                    this.smoothSlot = targetSlot;
                }

                int screenWidth = context.getScaledWindowWidth();
                int smoothX = (int) (screenWidth / 2 - 91 - 1 + (this.smoothSlot * 20));

                context.drawGuiTexture(renderLayers, sprite, smoothX, y, width, height);
                return;
            }
        }
        
        context.drawGuiTexture(renderLayers, sprite, x, y, width, height);
    }
}