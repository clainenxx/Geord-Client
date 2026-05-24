package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftClient.class)
public abstract class CrystalOptimizerMixin {
    @Shadow public net.minecraft.client.network.ClientPlayerEntity player;
    @Shadow public net.minecraft.client.network.ClientPlayerInteractionManager interactionManager;
    @Shadow public HitResult crosshairTarget;
    
    @Shadow private int itemUseCooldown; 

    @Inject(method = "tick", at = @At("HEAD"))
    private void hapusDelay(CallbackInfo ci) {
        if (!geordmod.util.GeordConfig.crystalOptimizer) return;
        if (this.player != null && this.crosshairTarget != null) {
            boolean pegangCrystal = this.player.getMainHandStack().isOf(Items.END_CRYSTAL) || this.player.getOffHandStack().isOf(Items.END_CRYSTAL);
            
            if (pegangCrystal) {
                if (this.crosshairTarget.getType() == HitResult.Type.BLOCK) {
                    BlockHitResult blockHit = (BlockHitResult) this.crosshairTarget;
                    BlockPos pos = blockHit.getBlockPos();
                    
                    net.minecraft.block.Block blokTarget = this.player.getWorld().getBlockState(pos).getBlock();
                    
                    if (blokTarget == net.minecraft.block.Blocks.OBSIDIAN || blokTarget == net.minecraft.block.Blocks.BEDROCK) {
                        this.itemUseCooldown = 0; 
                        return;
                    }
                }
            }
        }
    }

    @Inject(method = "doAttack", at = @At("HEAD"))
    private void serangDanTaruhInstan(CallbackInfoReturnable<Boolean> cir) {
        if (!geordmod.util.GeordConfig.crystalOptimizer) return;
        if (this.player == null || this.interactionManager == null || this.crosshairTarget == null) return;

        boolean pegangCrystal = this.player.getMainHandStack().isOf(Items.END_CRYSTAL) || this.player.getOffHandStack().isOf(Items.END_CRYSTAL);

        if (this.crosshairTarget.getType() == HitResult.Type.ENTITY) {
            EntityHitResult entityHit = (EntityHitResult) this.crosshairTarget;
            Entity target = entityHit.getEntity();
            
            if (target instanceof EndCrystalEntity) {
                target.setRemoved(Entity.RemovalReason.KILLED);

                if (pegangCrystal) {
                    BlockPos posisiBlok = target.getBlockPos().down();
                    
                    BlockHitResult blockTarget = new BlockHitResult(
                        new Vec3d(posisiBlok.getX() + 0.5, posisiBlok.getY() + 1.0, posisiBlok.getZ() + 0.5),
                        Direction.UP,
                        posisiBlok,
                        false
                    );
                    
                    Hand tangan = this.player.getMainHandStack().isOf(Items.END_CRYSTAL) ? Hand.MAIN_HAND : Hand.OFF_HAND;
                    
                    this.interactionManager.interactBlock(this.player, tangan, blockTarget);
                    this.player.swingHand(tangan);
                }
            }
        }
    }
}