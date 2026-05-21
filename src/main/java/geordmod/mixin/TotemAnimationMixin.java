package geordmod.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.GeordConfig;

@Mixin(GameRenderer.class)
public class TotemAnimationMixin {

    @Shadow private ItemStack floatingItem;

    @Inject(method = "renderFloatingItem", at = @At("HEAD"))
    private void makeTotemSmall(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (GeordConfig.smallTotem && this.floatingItem != null && this.floatingItem.isOf(Items.TOTEM_OF_UNDYING)) {
            context.getMatrices().push();
            
            float centerX = context.getScaledWindowWidth() / 2.0f;
            float centerY = context.getScaledWindowHeight() / 2.0f;
            
            context.getMatrices().translate(centerX, centerY, 0.0f);
            
            // [PERUBAHAN]: Angka diturunkan menjadi 0.25F agar totemnya jadi sangat kecil (mini)!
            context.getMatrices().scale(0.25F, 0.25F, 0.25F); 
            
            context.getMatrices().translate(-centerX, -centerY, 0.0f);
        }
    }

    @Inject(method = "renderFloatingItem", at = @At("RETURN"))
    private void restoreTotemSize(DrawContext context, float tickDelta, CallbackInfo ci) {
        if (GeordConfig.smallTotem && this.floatingItem != null && this.floatingItem.isOf(Items.TOTEM_OF_UNDYING)) {
            context.getMatrices().pop();
        }
    }
}