package geordmod.mixin;

import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.GeordConfig;
import geordmod.util.TotemTracker;

@Mixin(GameRenderer.class)
public class TotemAnimationMixin {

    @Inject(method = "showFloatingItem", at = @At("HEAD"), cancellable = true)
    private void onShowFloatingItem(ItemStack floatingItem, CallbackInfo ci) {
        if (floatingItem.isOf(Items.TOTEM_OF_UNDYING) && GeordConfig.smallTotem) {
            TotemTracker.floatingItem = floatingItem;
            TotemTracker.floatingItemTime = System.currentTimeMillis();
            ci.cancel(); 
        }
    }
}