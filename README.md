# Geordd

A lightweight Fabric client-side mod for Minecraft 1.20.5–1.21.4 focused on combat clarity and quality of life improvements.

---

## Features

### ⚔️ Combat

- **Shield Status** — Highlights a player's shield red when it's temporarily disabled by an axe hit. Detects both your own shield and other players' shields using sound packet interception combined with attack tracking for accurate results.

- **Smart Hitbox** — When you hit another player, their hitbox turns red to indicate they are temporarily immune to damage — for example, after teleporting via Ender Pearl or similar. This gives you a clear visual cue to time your attacks better instead of wasting hits.

- **Crystal Optimizer** — Improves end crystal interaction logic for faster and more consistent placement and detonation.

- **Totem Pop** — Displays a notification when a player uses a Totem of Undying, with an adjustable size so you can make it as subtle or prominent as you prefer.

- **Fast XP** — Hold **Shift + Right Click** to activate instant XP orb pickup, pulling nearby orbs to you immediately instead of waiting for them to float over.

### 🎨 Visual / HUD

- **Header Stats** — Displays useful real-time information above your screen including:
  - ❤️ Hit/reach counter
  - 🪬 Totem pop counter — tracks how many totems a player has used
  - 📶 Ping counter — shows your current latency to the server

- **Dynamic Crosshair** — The crosshair only appears when you are within melee attack range of another player, giving you a clean, clutter-free view and a precise visual indicator of when you can land a hit.

- **Armor HUD** — Displays your armor durability directly on screen so you don't need to open your inventory to check it.

- **Nametag** — Improved nametag rendering with cleaner presentation.

- **Totem Animation** — Custom animation played when a Totem of Undying activates, with adjustable size to match your preference.

- **Smooth Hotbar** — Smooth transition animation when switching between hotbar slots.

- **Held Item Renderer** — Adjust the height position of both your right and left hand independently in first-person view, letting you fine-tune exactly where your held items appear on screen.

- **In-Game Overlay** — Additional information rendered as an overlay during gameplay.

### 🎮 Utility

- **Zoom** — Zoom in without needing OptiFine or any external dependency. Fully adjustable zoom-in/out animation speed and smoothness so it feels exactly how you want it.

- **Camera** — Adjustable camera behavior for a more comfortable experience.

- **Mouse** — Mouse input fine-tuning for better control and responsiveness.

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
