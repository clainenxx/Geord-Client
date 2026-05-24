package geordmod.mixin;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.gui.GeordSettingsScreen;

@Mixin(OptionsScreen.class)
public class OptionsScreenMixin extends Screen {

    protected OptionsScreenMixin(Text title) { super(title); }

    @Inject(method = "init", at = @At("TAIL"))
    private void addGeordSettingButton(CallbackInfo ci) {
        // FIX WARNA: Diubah menjadi §f (Putih Biasa) sesuai permintaanmu
        this.addDrawableChild(ButtonWidget.builder(Text.literal("§fGeord Settings"), button -> {
            this.client.setScreen(new GeordSettingsScreen(this));
        }).dimensions(10, 10, 120, 20).build());
    }
}