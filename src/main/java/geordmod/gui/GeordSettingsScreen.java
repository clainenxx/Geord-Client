package geordmod.gui;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.SliderWidget;
import net.minecraft.text.Text;
import geordmod.util.GeordConfig;

public class GeordSettingsScreen extends Screen {
    private final Screen parent;

    public GeordSettingsScreen(Screen parent) {
        super(Text.literal("§fGeord Client Settings"));
        this.parent = parent;
    }

    @Override
    protected void init() {
        int startY = 45;
        int centerX = this.width / 2;

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Crystal Optimizer: " + (GeordConfig.crystalOptimizer ? "§aON" : "§cOFF")), 
            button -> {
                GeordConfig.crystalOptimizer = !GeordConfig.crystalOptimizer;
                button.setMessage(Text.literal("Crystal Optimizer: " + (GeordConfig.crystalOptimizer ? "§aON" : "§cOFF")));
            }
        ).dimensions(centerX - 155, startY, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("No Camera Fire: " + (GeordConfig.noFire ? "§aON" : "§cOFF")), 
            button -> {
                GeordConfig.noFire = !GeordConfig.noFire;
                button.setMessage(Text.literal("No Camera Fire: " + (GeordConfig.noFire ? "§aON" : "§cOFF")));
            }
        ).dimensions(centerX - 155, startY + 25, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Small Totem Pop: " + (GeordConfig.smallTotem ? "§aON" : "§cOFF")), 
            button -> {
                GeordConfig.smallTotem = !GeordConfig.smallTotem;
                button.setMessage(Text.literal("Small Totem Pop: " + (GeordConfig.smallTotem ? "§aON" : "§cOFF")));
            }
        ).dimensions(centerX - 155, startY + 50, 150, 20).build());

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Smart Hitbox: " + (GeordConfig.smartHitbox ? "§aON" : "§cOFF")), 
            button -> {
                GeordConfig.smartHitbox = !GeordConfig.smartHitbox;
                button.setMessage(Text.literal("Smart Hitbox: " + (GeordConfig.smartHitbox ? "§aON" : "§cOFF")));
            }
        ).dimensions(centerX - 155, startY + 75, 150, 20).build());


        this.addDrawableChild(new SliderWidget(centerX + 5, startY, 150, 20, Text.literal("Hotbar Speed"), GeordConfig.getHotbarPercent()) {
            @Override protected void updateMessage() { this.setMessage(Text.literal("Hotbar Speed: " + String.format("%.3f", GeordConfig.smoothHotbarSpeed))); }
            @Override protected void applyValue() { GeordConfig.setHotbarPercent(this.value); }
        });
        
        this.addDrawableChild(new SliderWidget(centerX + 5, startY + 25, 150, 20, Text.literal("Zoom Speed"), GeordConfig.getZoomSpeedPercent()) {
            @Override protected void updateMessage() { this.setMessage(Text.literal("Zoom Anim Speed: " + String.format("%.3f", GeordConfig.zoomSpeed))); }
            @Override protected void applyValue() { GeordConfig.setZoomSpeedPercent(this.value); }
        });

        this.addDrawableChild(new SliderWidget(centerX + 5, startY + 50, 150, 20, Text.literal("Zoom Slipperiness"), GeordConfig.getZoomSmoothnessPercent()) {
            @Override protected void updateMessage() { this.setMessage(Text.literal("Zoom Slipperiness: " + String.format("%.2f", GeordConfig.zoomSmoothness))); }
            @Override protected void applyValue() { GeordConfig.setZoomSmoothnessPercent(this.value); }
        });

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Shield Status: " + (GeordConfig.disabledShieldTint ? "§aON" : "§cOFF")), 
            button -> {
                GeordConfig.disabledShieldTint = !GeordConfig.disabledShieldTint;
                button.setMessage(Text.literal("Shield Status: " + (GeordConfig.disabledShieldTint ? "§aON" : "§cOFF")));
            }
        ).dimensions(centerX + 5, startY + 75, 150, 20).build());


        this.addDrawableChild(new SliderWidget(centerX - 155, startY + 105, 150, 20, Text.literal("Right Hand Height"), GeordConfig.getRightHandPercent()) {
            @Override protected void updateMessage() { this.setMessage(Text.literal("Right Hand Y: " + String.format("%.2f", GeordConfig.rightHandHeight))); }
            @Override protected void applyValue() { GeordConfig.setRightHandPercent(this.value); }
        });

        this.addDrawableChild(new SliderWidget(centerX + 5, startY + 105, 150, 20, Text.literal("Left Hand Height"), GeordConfig.getLeftHandPercent()) {
            @Override protected void updateMessage() { this.setMessage(Text.literal("Left Hand Y: " + String.format("%.2f", GeordConfig.leftHandHeight))); }
            @Override protected void applyValue() { GeordConfig.setLeftHandPercent(this.value); }
        });


        this.addDrawableChild(ButtonWidget.builder(Text.literal("Done"), button -> {
            geordmod.util.GeordConfig.save();
            this.client.setScreen(this.parent);
        }).dimensions(centerX - 100, this.height - 35, 200, 20).build());
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 18, 0xFFFFFF);
    }
}