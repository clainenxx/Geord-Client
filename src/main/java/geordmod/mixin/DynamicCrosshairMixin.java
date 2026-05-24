package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class DynamicCrosshairMixin {

    @Inject(method = "renderCrosshair", at = @At("TAIL"))
    private void drawDynamicIndicator(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        
        if (client.player == null || !client.options.getPerspective().isFirstPerson()) return;

        HitResult hit = client.crosshairTarget;
        
        if (hit != null && hit.getType() == HitResult.Type.ENTITY) {
            Entity target = ((EntityHitResult) hit).getEntity();
            
            if (target instanceof LivingEntity livingTarget) {
                boolean musuhBisaDihit = livingTarget.hurtTime <= 0;
                boolean senjataSiap = client.player.getAttackCooldownProgress(0.0f) >= 1.0f;

                if (musuhBisaDihit && senjataSiap) {
                    int width = context.getScaledWindowWidth();
                    int height = context.getScaledWindowHeight();
                    
                    // --- PERBAIKAN 1: TITIK TENGAH AKURAT ---
                    // Mengikuti rumus Mojang: (Lebar Layar - Lebar Tekstur 15) / 2 + 7 (Titik Pusat Tekstur)
                    // Ini menjamin posisi Dynamic Crosshair akan selalu akurat dengan crosshair asli
                    int cx = (width - 15) / 2 + 7;
                    int cy = (height - 15) / 2 + 7;

                    // --- PERBAIKAN 2: WARNA TRANSPARAN ---
                    // Menggunakan Alpha E0 (Sekitar 12-15% Transparan)
                    int color = 0xE0FFFFFF; 

                    // --- PERBAIKAN 3: KORDINAT SIMETRIS SEMPURNA ---
                    
                    // 1. SUDUT ATAS-KIRI
                    context.fill(cx - 5, cy - 5, cx - 2, cy - 4, color); // Horizontal
                    context.fill(cx - 5, cy - 4, cx - 4, cy - 2, color); // Vertikal

                    // 2. SUDUT ATAS-KANAN
                    context.fill(cx + 3, cy - 5, cx + 6, cy - 4, color); // Horizontal
                    context.fill(cx + 5, cy - 4, cx + 6, cy - 2, color); // Vertikal

                    // 3. SUDUT BAWAH-KIRI
                    context.fill(cx - 5, cy + 5, cx - 2, cy + 6, color); // Horizontal
                    context.fill(cx - 5, cy + 3, cx - 4, cy + 5, color); // Vertikal

                    // 4. SUDUT BAWAH-KANAN
                    context.fill(cx + 3, cy + 5, cx + 6, cy + 6, color); // Horizontal
                    context.fill(cx + 5, cy + 3, cx + 6, cy + 5, color); // Vertikal
                }
            }
        }
    }
}