package geordmod.mixin;

import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.GeordConfig;

@Mixin(InGameOverlayRenderer.class)
public class InGameOverlayRendererMixin {

    // [PERBAIKAN]: Menyesuaikan parameter dengan format Minecraft 1.21.4 yang baru
    // (Menghapus MinecraftClient, dan menambahkan VertexConsumerProvider)
    @Inject(method = "renderFireOverlay", at = @At("HEAD"), cancellable = true)
    private static void onRenderFire(MatrixStack matrices, VertexConsumerProvider vertexConsumers, CallbackInfo ci) {
        
        // Jika status No Camera Fire diaktifkan di Geord Settings, batalkan efek api
        if (GeordConfig.noFire) {
            ci.cancel();
        }
    }
}