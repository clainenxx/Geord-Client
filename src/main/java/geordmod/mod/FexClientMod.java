package geordmod.mod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.Perspective;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.lwjgl.glfw.GLFW;

public class FexClientMod implements ClientModInitializer {
    public static boolean isNightVisionActive = false;
    private static KeyBinding nvKeyBinding;
    private static KeyBinding zoomKeyBinding;
    private static KeyBinding elytraKeyBinding;
    private static KeyBinding freelookKeyBinding;

    public static float currentTargetZoom = 0.25f; 
    public static boolean isFreelooking = false;
    public static float freelookYaw = 0f;
    public static float freelookPitch = 0f;
    private static Perspective originalPerspective = Perspective.FIRST_PERSON;

    public static boolean isZooming() {
        return zoomKeyBinding != null && zoomKeyBinding.isPressed();
    }

    public static void onScroll(double amount) {
        if (amount > 0) currentTargetZoom -= 0.05f; 
        else if (amount < 0) currentTargetZoom += 0.05f; 
        currentTargetZoom = Math.max(0.02f, Math.min(0.5f, currentTargetZoom));
    }

    private static void doElytraSwap(MinecraftClient client) {
        if (client.player == null || client.interactionManager == null) return;
        
        net.minecraft.entity.player.PlayerInventory inv = client.player.getInventory();
        net.minecraft.item.ItemStack chestStack = client.player.getEquippedStack(net.minecraft.entity.EquipmentSlot.CHEST);
        boolean hasElytra = chestStack.isOf(net.minecraft.item.Items.ELYTRA);
        
        int targetSlot = -1;
        
        for (int i = 0; i < 36; i++) {
            net.minecraft.item.ItemStack stack = inv.getStack(i);
            if (stack.isEmpty()) continue;
            
            if (hasElytra) {
                if (stack.isOf(net.minecraft.item.Items.NETHERITE_CHESTPLATE) ||
                    stack.isOf(net.minecraft.item.Items.DIAMOND_CHESTPLATE) ||
                    stack.isOf(net.minecraft.item.Items.IRON_CHESTPLATE) ||
                    stack.isOf(net.minecraft.item.Items.GOLDEN_CHESTPLATE) ||
                    stack.isOf(net.minecraft.item.Items.CHAINMAIL_CHESTPLATE) ||
                    stack.isOf(net.minecraft.item.Items.LEATHER_CHESTPLATE)) {
                    targetSlot = i; break;
                }
            } else {
                if (stack.isOf(net.minecraft.item.Items.ELYTRA)) {
                    targetSlot = i; break;
                }
            }
        }
        
        if (targetSlot != -1) {
            int screenSlot = targetSlot < 9 ? targetSlot + 36 : targetSlot;
            
            client.interactionManager.clickSlot(0, screenSlot, 0, net.minecraft.screen.slot.SlotActionType.PICKUP, client.player);
            client.interactionManager.clickSlot(0, 6, 0, net.minecraft.screen.slot.SlotActionType.PICKUP, client.player); // 6 = Slot Baju
            client.interactionManager.clickSlot(0, screenSlot, 0, net.minecraft.screen.slot.SlotActionType.PICKUP, client.player);
        }
    }

    @Override
    public void onInitializeClient() {
        geordmod.util.GeordConfig.load();
        nvKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Night Vision", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, "Geordd Area"));
        zoomKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Zoom", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_C, "Geordd Area"));
        
        elytraKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Elytra Swapper", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Geordd Area"));
        freelookKeyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding("Freelook", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, "Geordd Area"));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            while (nvKeyBinding.wasPressed()) {
                isNightVisionActive = !isNightVisionActive;
                if (!isNightVisionActive) client.player.removeStatusEffect(StatusEffects.NIGHT_VISION);
            }
            if (isNightVisionActive) client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, -1, 0, false, false, false));

            while (elytraKeyBinding.wasPressed()) {
                doElytraSwap(client);
            }

            if (!isZooming()) currentTargetZoom = 0.25f;

            boolean freelookingNow = freelookKeyBinding.isPressed();
            if (freelookingNow && !isFreelooking) {
                isFreelooking = true;
                freelookYaw = client.player.getYaw();
                freelookPitch = client.player.getPitch();
                originalPerspective = client.options.getPerspective();
                client.options.setPerspective(Perspective.THIRD_PERSON_BACK);
            } else if (!freelookingNow && isFreelooking) {
                isFreelooking = false;
                client.options.setPerspective(originalPerspective);
            }
        });
    }
}