package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.PlaySoundS2CPacket;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.ShieldTracker;

@Mixin(ClientPlayNetworkHandler.class)
public class ShieldStatusMixin {

    @Inject(method = "onPlaySound", at = @At("HEAD"))
    private void onSound(PlaySoundS2CPacket packet, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        net.minecraft.util.Identifier soundId = packet.getSound().value().id();
        if (!soundId.getPath().equals("item.shield.break")) return;

        // Kalau shield kita sendiri lagi cooldown = suara ini untuk shield kita, skip
        if (client.player.getItemCooldownManager().isCoolingDown(
                new net.minecraft.item.ItemStack(net.minecraft.item.Items.SHIELD))) return;

        double sx = packet.getX(), sy = packet.getY(), sz = packet.getZ();

        PlayerEntity nearest = null;
        double nearestDist = Double.MAX_VALUE;

        for (PlayerEntity player : client.world.getPlayers()) {
            if (player == client.player) continue;
            double dx = player.getX() - sx;
            double dy = player.getY() - sy;
            double dz = player.getZ() - sz;
            double dist = dx*dx + dy*dy + dz*dz;
            if (dist < nearestDist) {
                nearestDist = dist;
                nearest = player;
            }
        }

        if (nearest != null && nearestDist < 36.0) {
            ShieldTracker.handleSoundDisable(nearest);
        }
    }
}