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
        
        // Durasi diubah ke 2 detik (2000ms) agar pas dengan waktu "ngambang" ala vanilla
        if (elapsed > 2000) {
            TotemTracker.floatingItem = null;
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        float progress = elapsed / 2000.0f; // Berjalan dari 0.0F ke 1.0F
        
        // --- UPGRADE ANIMASI ALA VANILLA ---
        // 1. Skala "Pop" (Kurva Sinusoidal)
        // Menggunakan Math.sin agar nilainya naik tajam ke 1.0 lalu turun lagi ke 0.0
        // Math.pow(..., 0.5) memberikan efek ledakan (muncul lebih cepat, mengecil perlahan)
        float sinCurve = (float) Math.pow(Math.sin(progress * Math.PI), 0.5);
        float scale = sinCurve * 3.0f; // Ukuran maksimal 3x lipat
        
        // 2. Gerakan Mengambang (Y-Axis)
        // Mulai dari 20 piksel di bawah tengah layar, perlahan naik sejauh 60 piksel ke atas
        float yOffset = 20.0f - (progress * 60.0f);

        // Jangan render jika skalanya sudah mengecil ke 0 agar tidak terjadi glitch visual
        if (scale <= 0.0f) return;

        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        float centerX = screenWidth / 2.0f;
        // Terapkan offset Y agar mengambang
        float centerY = (screenHeight / 2.0f) + yOffset; 

        context.getMatrices().pushMatrix();
        
        // Terapkan translasi dan skala baru
        context.getMatrices().translate(centerX, centerY);
        context.getMatrices().scale(scale, scale);
        context.getMatrices().translate(-centerX, -centerY);

        context.drawItem(TotemTracker.floatingItem, (int)centerX - 8, (int)centerY - 8);

        context.getMatrices().popMatrix();
    }
}