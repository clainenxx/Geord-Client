package geordmod.mixin;

import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.ShieldTracker;

@Mixin(ClientPlayerInteractionManager.class)
public class ShieldAttackMixin {

    @Inject(method = "attackEntity", at = @At("HEAD"))
    private void onAttack(PlayerEntity player, Entity target, CallbackInfo ci) {
        if (!(target instanceof PlayerEntity targetPlayer)) return;
        ShieldTracker.handlePlayerAttack(player, targetPlayer);
    }
}