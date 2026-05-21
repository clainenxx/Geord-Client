# Geordd

A lightweight Fabric client-side mod for Minecraft **1.20.5 → 1.21.4** focused on combat clarity, PvP visibility, and quality-of-life improvements.

Geordd is designed to improve how information is displayed during gameplay without modifying vanilla combat mechanics or automating actions. Basically: cleaner PvP, better feedback, less guessing, fewer moments of "why did that hit not register bro".

---

# Preview

## Main Options Menu

![Options Menu](2026-05-21_17.35.23.png)

---

## Geord Client GUI

![Geord Client GUI](2026-05-21_17.35.27.png)

All visual settings, combat modules, animation tuning, and sliders can be adjusted directly inside the **Geord Client GUI**.

You can customize:
- Crystal Optimizer
- Smart Hitbox
- Shield Status
- Totem Pop Size
- Zoom Speed
- Zoom Smoothness
- Hotbar Animation Speed
- Right Hand Height
- Left Hand Height
- And more

---

## Keybind Settings

![Keybind Settings](2026-05-21_17.35.38.png)

Utility features can also be configured through Minecraft keybind settings.

Includes configurable binds for:
- Zoom
- Freelook
- Night Vision
- Elytra Swapper
- Other Geordd utilities

Because reaching for menus mid-fight is how people accidentally throw pearls into walls and spiritually leave their bodies.

---

# Features

# ⚔️ Combat

## Crystal Optimizer
Improves End Crystal interaction handling for:
- Faster crystal placement
- More responsive crystal breaking
- Smoother and more consistent crystal combat

Designed to reduce interaction delay feeling during Crystal PvP while remaining fully client-side.

---

## Shield Status
Highlights a player's shield in **red** when it becomes temporarily disabled after an axe hit.

The system detects:
- Your own shield disable state
- Other players' shield disable state

Detection uses:
- Sound packet interception
- Attack tracking logic

This allows accurate shield-disable feedback even during fast-paced PvP.

---

## Smart Hitbox
When you hit another player, their hitbox can temporarily turn **red**.

A red hitbox means:
- The target is currently damage immune
- Your hits will not register yet

Common situations include:
- After teleporting
- After using an Ender Pearl
- Temporary invulnerability frames

Instead of wasting hits while the player is immune, Smart Hitbox visually warns you by changing the hitbox color to red.

This gives clearer combat timing and better attack decision-making during PvP.

---

## Totem Pop
Displays a notification whenever a player uses a **Totem of Undying**.

Features:
- Adjustable notification size
- Can be scaled larger or smaller in the GUI

Useful for:
- Crystal PvP
- Tracking enemy pops
- Cleaner combat awareness

---

## Fast XP
Allows instant XP orb pickup for faster repairing and mending.

Activation:
1. Hold **Shift**
2. Then **Right Click**

Nearby XP orbs will immediately move toward the player instead of slowly floating over normally.

Useful during:
- Crystal PvP
- Fast armor repair
- High-pressure fights

---

# 🎨 Visual / HUD

## Header Stats
Displays real-time combat information above every player's head on the server.

The stats appear:
- Above the player's character
- Directly below their nametag

Includes:
- ❤️ **Hit / Reach Counter**
  - Displays combat hit-related information and reach tracking

- 🪬 **Totem Pop Counter**
  - Tracks how many totems a player has used during combat

- 📶 **Ping Counter**
  - Displays the player's current server latency in real time

This allows faster target reading during fights without opening menus or relying on chat messages.

---

## Dynamic Crosshair
The crosshair only appears when another player is within melee attack range.

Behavior:
- Target too far away → crosshair stays hidden
- Target close enough to hit → crosshair appears

This provides:
- Cleaner visuals
- Less screen clutter
- A visual indicator for valid hit range timing

So the crosshair only shows up when violence becomes mathematically possible. Minecraft philosophy evolving in real time.

---

## Armor HUD
Displays armor durability directly on screen.

Allows quick monitoring without opening the inventory during combat.

---

## Nametag
Improved nametag rendering with cleaner and easier-to-read visuals.

---

## Totem Animation
Custom animation played when a Totem of Undying activates.

Features:
- Adjustable animation size
- Fully customizable inside the Geord Client GUI

You can make the animation:
- Smaller and subtle
- Large and highly visible

---

## Smooth Hotbar
Adds smooth transition animations when switching hotbar slots.

Animation speed can be adjusted inside the Geord Client GUI.

---

## Held Item Renderer
Allows independent first-person hand position adjustment.

You can separately customize:
- Right hand height
- Left hand height

Useful for:
- Better screen visibility
- PvP preference adjustments
- Cleaner first-person visuals

---

## In-Game Overlay
Additional gameplay information rendered directly as an on-screen overlay.

---

# 🎮 Utility

## Zoom
Built-in zoom feature without requiring OptiFine.

Fully customizable:
- Zoom-in animation speed
- Zoom-out animation speed
- Zoom smoothness / slipperiness

You can configure the zoom to feel:
- Instant
- Smooth
- Cinematic
- Fast and responsive

All settings are adjustable directly in the Geord Client GUI.

---

## Camera
Adjustable camera behavior for a more comfortable gameplay experience.

---

## Mouse
Mouse input fine-tuning for:
- Better responsiveness
- More consistent movement
- Smoother aiming feel

---

# Customization

Most Geordd features can be configured directly inside:
- **Geord Client GUI**
- **Minecraft Keybind Settings**

This includes:
- Visual modules
- Combat feedback
- Animation tuning
- Utility toggles
- Keybind controls

Designed to stay lightweight while still giving full control over how the mod feels and behaves.

---

# Transparency

- All features are visual or informational only
- No gameplay automation exists
- Every action still requires player input
- Nothing server-side is modified
- No reach modification
- No velocity modification
- No movement hacks
- No combat automation

The server always remains fully authoritative over gameplay.

Geordd improves clarity and feedback only. Your aim, panic, and questionable pearl decisions are still completely manual.

---

# Installation

1. Install :contentReference[oaicite:0]{index=0} for Minecraft 1.20.5 → 1.21.4
2. Install :contentReference[oaicite:1]{index=1}
3. Put `geordd.jar` into your `.minecraft/mods` folder

---

# Requirements

| Component | Version |
|---|---|
| Minecraft | 1.20.5 → 1.21.4 |
| Fabric Loader | 0.19+ |
| Fabric API | Latest |
| Java | 21 |

---

# Building from Source

```bash
./gradlew build
