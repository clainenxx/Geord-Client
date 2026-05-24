package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.state.LivingEntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.ShieldTracker;

// Pindah ke LivingEntityRenderer biar pelacakannya pas badan & item musuh digambar (bukan nametagnya doang)
@Mixin(LivingEntityRenderer.class)
public class PlayerContextMixin {

    @Inject(method = "render", at = @At("HEAD"))
    private void captureEnemyPlayer(LivingEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (state instanceof PlayerEntityRenderState playerState) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.world != null) {
                // Tangkap data musuh dari ID render-nya
                Entity entity = client.world.getEntityById(playerState.id);
                if (entity instanceof PlayerEntity player) {
                    ShieldTracker.currentRenderedPlayer = player;
                }
            }
        }
    }

    @Inject(method = "render", at = @At("RETURN"))
    private void releaseEnemyPlayer(LivingEntityRenderState state, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (state instanceof PlayerEntityRenderState) {
            // Reset setelah selesai nggambar musuh
            ShieldTracker.currentRenderedPlayer = null;
        }
    }
}