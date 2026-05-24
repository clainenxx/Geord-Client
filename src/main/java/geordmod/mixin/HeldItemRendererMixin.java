package geordmod.mixin;

import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.GeordConfig;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Inject(method = "renderFirstPersonItem", at = @At("HEAD"))
    private void applyHandHeight(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        boolean isMainHand = (hand == Hand.MAIN_HAND);
        boolean isRightArm = isMainHand ? (player.getMainArm() == net.minecraft.util.Arm.RIGHT) : (player.getMainArm() == net.minecraft.util.Arm.LEFT);
        
        if (isRightArm) {
            matrices.translate(0.0F, GeordConfig.rightHandHeight, 0.0F);
        } else {
            matrices.translate(0.0F, GeordConfig.leftHandHeight, 0.0F);
        }
    }

    @Inject(method = "renderFirstPersonItem", at = @At("RETURN"))
    private void revertHandHeight(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand, float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        boolean isMainHand = (hand == Hand.MAIN_HAND);
        boolean isRightArm = isMainHand ? (player.getMainArm() == net.minecraft.util.Arm.RIGHT) : (player.getMainArm() == net.minecraft.util.Arm.LEFT);
        
        if (isRightArm) {
            matrices.translate(0.0F, -GeordConfig.rightHandHeight, 0.0F);
        } else {
            matrices.translate(0.0F, -GeordConfig.leftHandHeight, 0.0F);
        }
    }
}