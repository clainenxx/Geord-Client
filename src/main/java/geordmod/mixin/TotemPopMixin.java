package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.network.packet.s2c.play.EntityStatusS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.TotemTracker;

@Mixin(ClientPlayNetworkHandler.class)
public class TotemPopMixin {

    @Inject(method = "onEntityStatus", at = @At("HEAD"))
    private void onEntityStatus(EntityStatusS2CPacket packet, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null) return;

        net.minecraft.entity.Entity entity = packet.getEntity(client.world);
        if (!(entity instanceof PlayerEntity player)) return;

        if (packet.getStatus() == 35) {
            TotemTracker.POPS.merge(player.getUuid(), 1, Integer::sum);
        } else if (packet.getStatus() == 3) {
            TotemTracker.POPS.put(player.getUuid(), 0);
        }
    }
}