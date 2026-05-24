package geordmod.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.model.ModelPart;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.UUID;

public class ShieldTracker {
    public static final Map<UUID, Long> DISABLED_SHIELDS = new HashMap<>();

    public static final Map<UUID, Long> RECENT_ATTACKS = new HashMap<>();

    public static PlayerEntity currentRenderedPlayer = null;
    public static final Set<ModelPart> SHIELD_PARTS = new HashSet<>();

    public static void registerShieldPart(ModelPart root) {
        SHIELD_PARTS.add(root);
        try {
            SHIELD_PARTS.add(root.getChild("plate"));
            SHIELD_PARTS.add(root.getChild("handle"));
        } catch (Exception e) {}
    }

    public static void handlePlayerAttack(PlayerEntity attacker, PlayerEntity target) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;
        if (!attacker.getUuid().equals(client.player.getUuid())) return;

        net.minecraft.item.Item weapon = attacker.getMainHandStack().getItem();
        if (!(weapon instanceof net.minecraft.item.AxeItem)) return;

        RECENT_ATTACKS.put(target.getUuid(), Util.getMeasuringTimeMs());
    }

    public static void handleSoundDisable(PlayerEntity player) {
        UUID uuid = player.getUuid();
        
        Long attackTime = RECENT_ATTACKS.get(uuid);
        if (attackTime == null) return;
        if (Util.getMeasuringTimeMs() - attackTime > 1000) return;
        
        DISABLED_SHIELDS.put(uuid, Util.getMeasuringTimeMs());
        RECENT_ATTACKS.remove(uuid);
    }

    public static void handleEntityEventDisable(PlayerEntity player) {
        DISABLED_SHIELDS.put(player.getUuid(), Util.getMeasuringTimeMs());
        RECENT_ATTACKS.remove(player.getUuid());
    }

    public static boolean isShieldDisabled(PlayerEntity player) {
        if (player == null) return false;

        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player != null && player.getUuid().equals(client.player.getUuid())) {
            return player.getItemCooldownManager().isCoolingDown(new ItemStack(Items.SHIELD));
        }

        UUID uuid = player.getUuid();
        Long breakTime = DISABLED_SHIELDS.get(uuid);
        if (breakTime == null) return false;

        if (Util.getMeasuringTimeMs() - breakTime > 5000) {
            DISABLED_SHIELDS.remove(uuid);
            return false;
        }
        return true;
    }
}