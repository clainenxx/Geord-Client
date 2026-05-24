package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import geordmod.util.GeordConfig;
import geordmod.util.ShieldTracker;

@Mixin(ModelPart.class)
public class ModelPartColorMixin {

    @ModifyVariable(method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;III)V", at = @At("HEAD"), argsOnly = true, ordinal = 2)
    private int modifyShieldTint(int originalColor) {
        if (!GeordConfig.disabledShieldTint) return originalColor;

        ModelPart thisPart = (ModelPart) (Object) this;
        if (!ShieldTracker.SHIELD_PARTS.contains(thisPart)) return originalColor;

        PlayerEntity player = ShieldTracker.currentRenderedPlayer;

        if (player == null) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player != null && ShieldTracker.isShieldDisabled(client.player)) {
                return 0xFFFF6666;
            }
            return originalColor;
        }

        if (ShieldTracker.isShieldDisabled(player)) {
            return 0xFFFF6666;
        }

        return originalColor;
    }
}