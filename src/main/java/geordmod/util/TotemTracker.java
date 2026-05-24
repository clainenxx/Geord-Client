package geordmod.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.item.ItemStack; // IMPORT BARU

public class TotemTracker {
    // Map untuk menyimpan jumlah totem pop per UUID pemain
    public static final Map<UUID, Integer> POPS = new HashMap<>();

    // --- STATE UNTUK ANIMASI TOTEM KECIL ---
    public static ItemStack floatingItem = null;
    public static long floatingItemTime = 0;
}