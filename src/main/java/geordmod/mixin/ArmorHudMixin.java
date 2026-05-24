package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderTickCounter;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class ArmorHudMixin {

    private static final Identifier VANILLA_HOTBAR = Identifier.ofVanilla("hud/hotbar");

    @Inject(method = "renderHotbar", at = @At("TAIL"))
    private void renderArmorHud(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        PlayerEntity player = client.player;
        
        if (player == null || client.options.hudHidden) return;

        int width = context.getScaledWindowWidth();
        int height = context.getScaledWindowHeight();

        // Titik awal menggambar (Sejajar di sebelah kanan hotbar asli)
        int startX = width / 2 + 91 + 10; 
        int startY = height - 22; 

        // --- TEKNIK SCISSOR (GUNTING) ---
        // 1. Kunci area layar yang boleh digambar hanya seluas 82 piksel (4 Slot)
        context.enableScissor(startX, startY, startX + 82, startY + 22);
        
        // 2. Gambar hotbar 1.21.4 dengan format RenderLayer yang benar
        // Karena area dikunci, sisa slot yang panjang akan otomatis terpotong rapi!
        context.drawGuiTexture(RenderLayer::getGuiTextured, VANILLA_HOTBAR, startX, startY, 182, 22);
        
        // 3. Matikan gunting agar sisa game (item, teks, dll) bisa dirender normal
        context.disableScissor();

        // --- RENDER ITEM ARMOR ---
        ItemStack[] armorSlots = new ItemStack[]{
            player.getEquippedStack(net.minecraft.entity.EquipmentSlot.FEET),
            player.getEquippedStack(net.minecraft.entity.EquipmentSlot.LEGS),
            player.getEquippedStack(net.minecraft.entity.EquipmentSlot.CHEST),
            player.getEquippedStack(net.minecraft.entity.EquipmentSlot.HEAD)
        };

        for (int i = 0; i < 4; i++) {
            ItemStack stack = armorSlots[3 - i];
            
            // Posisi item disejajarkan dengan kotak slot yang sudah digambar
            int itemX = startX + 3 + (i * 20);
            int itemY = startY + 3; 

            if (stack != null && !stack.isEmpty()) {
                context.drawItem(stack, itemX, itemY);
                context.drawStackOverlay(client.textRenderer, stack, itemX, itemY);
            }
        }
    }
}