package geordmod.util;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.item.ItemStack;

public class TotemTracker {
    public static final Map<UUID, Integer> POPS = new HashMap<>();
    public static ItemStack floatingItem = null;
    public static long floatingItemTime = 0;
}