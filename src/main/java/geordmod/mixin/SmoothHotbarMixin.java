package geordmod.mixin;

// IMPORT INI YANG BENAR DI 1.21.6 (Bukan net.minecraft...)
import com.mojang.blaze3d.pipeline.RenderPipeline; 
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(InGameHud.class)
public class SmoothHotbarMixin {
    
    private float smoothSlot = -1;
    private long lastTime = 0;

    // TARGET REDIRECT MENGGUNAKAN com.mojang...
    @Redirect(
        method = "renderHotbar", 
        at = @At(
            value = "INVOKE", 
            target = "Lnet/minecraft/client/gui/DrawContext;drawGuiTexture(Lcom/mojang/blaze3d/pipeline/RenderPipeline;Lnet/minecraft/util/Identifier;IIII)V"
        )
    )
    private void redirectDrawHotbarSelection(DrawContext context, RenderPipeline renderPipeline, Identifier sprite, int x, int y, int width, int height) {
        
        if (sprite.getPath().equals("hud/hotbar_selection")) {
            MinecraftClient client = MinecraftClient.getInstance();
            
            if (client.player != null) {
                // FIX PRIVATE SLOT: Kembali menggunakan getSelectedSlot()
                int targetSlot = client.player.getInventory().getSelectedSlot(); 
                
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

                context.drawGuiTexture(renderPipeline, sprite, smoothX, y, width, height);
                return;
            }
        }
        
        context.drawGuiTexture(renderPipeline, sprite, x, y, width, height);
    }
}