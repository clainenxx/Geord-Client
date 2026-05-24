package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class FastXpMixin {

    @Shadow private int itemUseCooldown;
    @Shadow public net.minecraft.client.network.ClientPlayerEntity player;

    @Inject(method = "tick", at = @At("HEAD"))
    private void spamXpFast(CallbackInfo ci) {
        if (this.player != null) {

            if (this.player.isSneaking()) {

                boolean pegangXp = this.player.getMainHandStack().isOf(Items.EXPERIENCE_BOTTLE) || 
                                   this.player.getOffHandStack().isOf(Items.EXPERIENCE_BOTTLE);
                
                if (pegangXp) {
                    this.itemUseCooldown = 0; 
                }
            }
        }
    }
}