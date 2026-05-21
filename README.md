# Geordd Client Mod

A Fabric client-side mod for Minecraft 1.20.5–1.21.4 focused on QoL improvements and combat clarity.

---

## Features

### ⚔️ Combat
- **Shield Status** — Highlights a player's shield red when it's temporarily disabled by an axe hit. Works for both your own shield and enemies'.
- **Crystal Optimizer** — Improves end crystal interaction logic for faster and more consistent placement/detonation.
- **Smart Hitbox** — More accurate hitbox rendering so you can better visualize where hits register.
- **Totem Pop** — Displays a notification when a player uses a Totem of Undying.
- **Fast XP** — XP orbs are picked up instantly instead of floating toward you.

### 🎨 Visual / HUD
- **Armor HUD** — Displays your armor durability on screen so you don't need to open your inventory.
- **Dynamic Crosshair** — Crosshair changes appearance based on what you're doing (attacking, blocking, etc).
- **Nametag** — Improved nametag rendering with additional player info.
- **Totem Animation** — Custom animation when a Totem of Undying activates.
- **Smooth Hotbar** — Smooth animation when switching between hotbar slots.
- **Held Item Renderer** — Custom rendering adjustments for the item held in hand.

### 🎮 Utility
- **Zoom** — Zoom in like OptiFine, no dependency required.
- **Camera** — Adjustable camera behavior.
- **Mouse** — Mouse input adjustments for better control.
- **In-Game Overlay** — Additional overlay information rendered during gameplay.

---

## Why This Isn't a Cheat Mod

- **No automation** — Every action still requires manual input from the player. Nothing is automated or bot-assisted.
- **No X-ray or ESP** — There is no wallhack, entity tracker, or any feature that reveals information that isn't normally visible.
- **No reach or velocity modification** — Hit detection, reach distance, and knockback are completely untouched.
- **No movement hacks** — No fly, speed, bhop, or any physics manipulation.
- **Visual clarity only** — Combat features like Shield Status only show information that already exists in the game (shield cooldown state), presented more clearly. The server always determines what actually happens.
- **Client-side only** — Nothing is sent to or manipulated on the server. The mod only changes what you see, not what the game does.

---

## Installation

1. Install [Fabric Loader](https://fabricmc.net/use/installer/) for Minecraft 1.20.5–1.21.4
2. Install [Fabric API](https://modrinth.com/mod/fabric-api)
3. Drop `geordd.jar` into your `mods` folder

---

## Requirements

- Minecraft 1.20.5 – 1.21.4
- Fabric Loader 0.19+
- Fabric API
- Java 21