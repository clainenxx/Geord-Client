package geordmod.mixin;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.ShieldEntityModel;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.ShieldTracker;

@Mixin(ShieldEntityModel.class)
public class ShieldEntityModelMixin {

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(ModelPart root, CallbackInfo ci) {
        ShieldTracker.registerShieldPart(root);
    }
}