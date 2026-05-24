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

    // Menyadap variabel delay/cooldown internal dari mouse klik kanan
    @Shadow private int itemUseCooldown;
    @Shadow public net.minecraft.client.network.ClientPlayerEntity player;

    @Inject(method = "tick", at = @At("HEAD"))
    private void spamXpFast(CallbackInfo ci) {
        if (this.player != null) {
            
            // Cek apakah pemain sedang menahan tombol Shift (Jongkok/Sneaking)
            if (this.player.isSneaking()) {
                
                // Cek apakah pemain memegang XP Bottle di tangan kanan atau tangan kiri (Offhand)
                boolean pegangXp = this.player.getMainHandStack().isOf(Items.EXPERIENCE_BOTTLE) || 
                                   this.player.getOffHandStack().isOf(Items.EXPERIENCE_BOTTLE);
                
                if (pegangXp) {
                    // Paksa delay lemparan menjadi 0ms (Sangat Cepat!)
                    this.itemUseCooldown = 0; 
                }
            }
        }
    }
}