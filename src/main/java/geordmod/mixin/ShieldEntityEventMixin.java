package geordmod.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.ShieldTracker;

@Mixin(LivingEntity.class)
public class ShieldEntityEventMixin {

    @Inject(method = "handleStatus", at = @At("HEAD"))
    private void onEntityStatus(byte status, CallbackInfo ci) {
        if (status != 30) return;
        LivingEntity self = (LivingEntity) (Object) this;
        if (!(self instanceof PlayerEntity player)) return;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player != null && player.getUuid().equals(client.player.getUuid())) return;
        ShieldTracker.handleEntityEventDisable(player);
    }
}