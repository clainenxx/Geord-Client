package geordmod.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.state.EntityRenderState;
import net.minecraft.client.render.entity.state.PlayerEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import geordmod.util.TotemTracker;

import java.util.UUID;

@Mixin(EntityRenderer.class)
public abstract class NametagMixin<S extends EntityRenderState> {
    @Inject(
        method = "renderLabelIfPresent",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/font/TextRenderer;draw(Lnet/minecraft/text/Text;FFIZLorg/joml/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;Lnet/minecraft/client/font/TextRenderer$TextLayerType;II)I",
            ordinal = 0
        )
    )
    private void renderStatsLabel(
            S state,
            Text text,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            CallbackInfo ci
    ) {
        if (!(state instanceof PlayerEntityRenderState playerState)) return;

        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.player == null || client.world == null) return;

        PlayerEntity targetPlayer = null;
        for (PlayerEntity p : client.world.getPlayers()) {
            if (p.getId() == playerState.id) { targetPlayer = p; break; }
        }
        if (targetPlayer == null) return;

        UUID playerUuid = targetPlayer.getUuid();
        if (playerUuid.equals(client.player.getUuid()) && client.options.getPerspective().isFirstPerson()) return;
        if (targetPlayer.isInvisibleTo(client.player)) return;

        int health = (int) Math.ceil(targetPlayer.getHealth() + targetPlayer.getAbsorptionAmount());
        int ping = 0;
        if (client.getNetworkHandler() != null) {
            PlayerListEntry entry = client.getNetworkHandler().getPlayerListEntry(playerUuid);
            if (entry != null) ping = entry.getLatency();
        }
        int pops = TotemTracker.POPS.getOrDefault(playerUuid, 0);

        String statsStr = "§f" + health + " §c❤ §7| §f" + pops + " §eŦ §7| §f" + ping + " §ams";
        Text statsText = Text.literal(statsStr);
        TextRenderer textRenderer = client.textRenderer;
        float x = (float) (-textRenderer.getWidth(statsText) / 2);
        float backgroundOpacity = client.options.getTextBackgroundOpacity(0.25f);
        int bgColor = (int) (backgroundOpacity * 255.0f) << 24;

        textRenderer.draw(
                statsText,
                x,
                10f,
                0xFFFFFFFF,
                false,
                matrices.peek().getPositionMatrix(),
                vertexConsumers,
                TextRenderer.TextLayerType.SEE_THROUGH,
                bgColor,
                light
        );
    }
}