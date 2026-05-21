# Geordd

A lightweight Fabric client-side mod for Minecraft **1.20.5 → 1.21.4** focused on combat clarity, cleaner visuals, and quality-of-life improvements for PvP gameplay.

Built to make information easier to read during fights without changing vanilla mechanics. Because apparently humans decided staring at tiny pixels during crystal PvP needed even more sensory overload.

---

# Features

# ⚔️ Combat

## Shield Status
Highlights a player's shield in **red** when it becomes temporarily disabled after being hit by an axe.

The mod detects:
- Your own shield disable state
- Other players' shield disable state

Detection is handled using:
- Sound packet interception
- Attack tracking logic

This makes the indicator far more accurate during fast-paced combat situations.

---

## Smart Hitbox
When you hit another player, their hitbox can temporarily turn **red**.

A red hitbox means:
- The player is currently immune to damage
- Your hits will not register yet

This commonly happens after:
- Using an Ender Pearl
- Teleporting
- Certain temporary invulnerability states

Instead of wasting hits while the target is still invulnerable, the red hitbox gives you a clear visual indicator for when you should wait before attacking again.

That's why it's called **Smart Hitbox**. Tiny glowing rectangle of tactical disappointment.

---

## Crystal Optimizer
Improves End Crystal interaction handling for:
- Faster crystal placement
- More responsive detonation
- Smoother crystal combat consistency

Designed to reduce interaction delay feeling during Crystal PvP.

---

## Totem Pop
Displays a notification when a player uses a **Totem of Undying**.

Features:
- Adjustable notification size
- Can be made subtle or highly visible depending on your preference

Useful during intense fights where tracking pops quickly matters.

---

## Fast XP
Quickly pulls nearby XP orbs directly to the player.

Activation:
- Hold **Shift**
- Then **Right Click**

This activates instant XP orb pickup instead of waiting for the orbs to slowly float toward you like they're emotionally processing the situation first.

Useful for:
- Fast armor repairing
- Crystal PvP
- Quick mending recovery

---

# 🎨 Visual / HUD

## Header Stats
Displays useful real-time combat information above your screen.

Includes:
- ❤️ **Hit / Reach Counter**
  - Shows hit-related combat information and reach tracking

- 🪬 **Totem Pop Counter**
  - Tracks how many totems a player has used during combat

- 📶 **Ping Counter**
  - Displays your current server latency in real time

Designed to keep important combat information visible without opening menus or chat spam.

---

## Dynamic Crosshair
The crosshair only appears when another player is within melee attack range.

Meaning:
- If the target is too far away → crosshair stays hidden
- If the target is close enough to hit → crosshair appears

This provides:
- A cleaner screen during gameplay
- A visual indicator for attack range timing

Basically your crosshair stops existing until violence becomes legally available.

---

## Armor HUD
Displays armor durability directly on your screen.

Allows you to:
- Monitor armor condition in real time
- Avoid opening inventory repeatedly during combat

---

## Nametag
Improved nametag rendering with a cleaner and more readable appearance.

---

## Totem Animation
Custom animation played when a Totem of Undying activates.

Features:
- Adjustable animation size
- Can be scaled larger or smaller to match personal preference

---

## Smooth Hotbar
Adds smooth transition animations when switching hotbar slots.

Makes item switching feel cleaner and less abrupt.

---

## Held Item Renderer
Allows independent adjustment of:
- Right hand position
- Left hand position

In first-person view.

You can fine-tune:
- Hand height
- Held item placement
- Screen positioning

Useful for:
- Cleaner visuals
- PvP preference adjustments
- Better visibility while fighting

---

## In-Game Overlay
Additional gameplay information rendered directly as an on-screen overlay.

---

# 🎮 Utility

## Zoom
A built-in zoom feature without requiring OptiFine or external dependencies.

Fully customizable:
- Zoom-in animation speed
- Zoom-out animation speed
- Zoom smoothness
- Animation feel

Allows the zoom to feel:
- Instant
- Smooth
- Cinematic
- Or aggressively snappy depending on your preference

Because apparently people now need configurable binocular physics inside block game PvP.

---

## Camera
Adjustable camera behavior for a more comfortable gameplay experience.

---

## Mouse
Mouse input fine-tuning for:
- Better responsiveness
- More consistent control
- Smoother aiming feel

---

# Transparency

- All features are **visual or informational only**
- No gameplay automation exists
- Every action still requires player input
- Nothing server-side is modified
- No reach modification
- No velocity modification
- No movement hacks
- No combat automation

The server always remains fully authoritative over gameplay.

This mod improves readability and feedback only. Your bad aim is still your responsibility.

---

# Installation

1. Install :contentReference[oaicite:0]{index=0} for Minecraft 1.20.5–1.21.4
2. Install :contentReference[oaicite:1]{index=1}
3. Put `geordd.jar` inside your `.minecraft/mods` folder

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
