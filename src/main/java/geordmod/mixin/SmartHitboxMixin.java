package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexRendering;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.GeordConfig;

@Mixin(WorldRenderer.class)
public class SmartHitboxMixin {

    @Inject(method = "renderEntity", at = @At("TAIL"))
    private void drawSmartHitbox(Entity entity, double cameraX, double cameraY, double cameraZ,
                                 float tickDelta, MatrixStack matrices,
                                 VertexConsumerProvider vertexConsumers, CallbackInfo ci) {

        if (!GeordConfig.smartHitbox) return;
        if (!(entity instanceof PlayerEntity player)) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null) return;

        // Skip diri sendiri saat first person
        if (player == client.player && client.options.getPerspective().isFirstPerson()) return;

        // Warna: merah = kebal, putih = bisa di-hit
        float r, g, b;
        boolean isImmune = player.isCreative()
                        || player.isInvulnerable()
                        || player.hurtTime > 0
                        || player.isSpectator();
        if (isImmune) {
            r = 1.0f; g = 0.0f; b = 0.0f;
        } else {
            r = 1.0f; g = 1.0f; b = 1.0f;
        }

        // FIX POSISI:
        // cameraX/Y/Z = posisi kamera di world
        // entity.getX/Y/Z = posisi entity di world
        // Selisihnya = posisi entity relatif terhadap kamera (yang dipakai matrix)
        double dx = player.getX() - cameraX;
        double dy = player.getY() - cameraY;
        double dz = player.getZ() - cameraZ;

        // Bounding box entity di world space, lalu geser ke camera-relative space
        Box box = player.getBoundingBox().offset(-player.getX() + dx, -player.getY() + dy, -player.getZ() + dz);

        VertexConsumer buffer = vertexConsumers.getBuffer(RenderLayer.getLines());
        VertexRendering.drawBox(matrices, buffer, box, r, g, b, 1.0f);
    }
}