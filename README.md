# Raid Spell Checker

**Developed by MrIronTy**

> **Plugin must be configured before use!**

---

## Overview

Raid Spell Checker helps ensure you're prepared for raids by verifying your **spellbook** and **rune requirements** before you enter **Chambers of Xeric (COX)**, **Theatre of Blood (TOB)**, or **Tombs of Amascut (TOA)**.

---

## Features

- Configure required **spellbook** and **specific spells** per raid.
- Alerts if you're on the **wrong spellbook**.
- Alerts if you're **missing runes** to cast selected spells.
- **Sound notifications** (selectable sound and volume).
- **Custom sound support** using `.wav` files.

---

## Custom Sound Setup

1. Place your custom `.wav` file into:
    raid-spell-checker/src/main/resources/sounds/
2. In the plugin config, set the sound name:
    MyCustomSound.wav

---

## How to Test

To test the plugin:
- Be on the wrong spellbook **or**
- Be missing runes for required spells.
- Walk near the **starting door** of one of the supported raids (COX/TOB/TOA).
- You should see a popup and hear the configured sound effect.

---

## Configuration Required

Before using the plugin, open the plugin panel and:
- Select the raid(s) you'd like to configure.
- Choose the correct spellbook and required spells.
- Optionally configure your sound effect and popup visuals.
