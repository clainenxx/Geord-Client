# Geordd

A lightweight Fabric client-side mod for Minecraft 1.20.5–1.21.4 focused on combat clarity and quality of life improvements.

---

## Features

### ⚔️ Combat
- **Shield Status** — Highlights a player's shield red when it's temporarily disabled by an axe hit. Detects both your own shield and other players' shields using sound packet interception combined with attack tracking for accurate results.
- **Crystal Optimizer** — Improves end crystal interaction logic for faster and more consistent placement and detonation.
- **Smart Hitbox** — More accurate hitbox rendering so you can better visualize where hits register.
- **Totem Pop** — Displays a notification when a player uses a Totem of Undying.
- **Fast XP** — XP orbs are picked up instantly instead of slowly floating toward you.

### 🎨 Visual / HUD
- **Armor HUD** — Displays your armor durability directly on screen so you don't need to open your inventory to check it.
- **Dynamic Crosshair** — Crosshair changes appearance contextually based on your current action such as attacking or blocking.
- **Nametag** — Improved nametag rendering with cleaner presentation.
- **Totem Animation** — Custom animation played when a Totem of Undying activates.
- **Smooth Hotbar** — Smooth transition animation when switching between hotbar slots.
- **Held Item Renderer** — Custom rendering adjustments for the item currently held in hand.

### 🎮 Utility
- **Zoom** — Zoom in without needing OptiFine or any external dependency.
- **Camera** — Adjustable camera behavior for a more comfortable experience.
- **Mouse** — Mouse input fine-tuning for better control and responsiveness.
- **In-Game Overlay** — Additional information rendered as an overlay during gameplay.

---

## Transparency

- All features are **visual or informational only** — they display game data more clearly without changing how the game actually works.
- **No actions are automated** — every input still comes from the player.
- **Nothing server-side is modified** — the mod only affects what is rendered on your screen.
- **No reach, velocity, or movement modification** of any kind.
- The server always remains in full control of what actually happens in the game.

---

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 1.20.5–1.21.4
2. Install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Drop `geordd.jar` into your `.minecraft/mods` folder

---

## Requirements

| Component | Version |
|---|---|
| Minecraft | 1.20.5 – 1.21.4 |
| Fabric Loader | 0.19+ |
| Fabric API | Latest for your MC version |
| Java | 21 |

---

## Building from Source

```bash
./gradlew build
```

Output jar will be in `build/libs/`.
