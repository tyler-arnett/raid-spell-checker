# Raid Spell Checker

**Developed by MrIronTy**

>  **This plugin must be configured before use!**

---

## Overview

**Raid Spell Checker** ensures you're prepared for raids by verifying your **spellbook** and **rune requirements** before entering:

- **Chambers of Xeric (COX)**
- **Theatre of Blood (TOB)**
- **Tombs of Amascut (TOA)**

---

## Features

- Select required **spellbook** and **specific spells** per raid.
- Alerts if you're on the **wrong spellbook**.
- Alerts if you're **missing runes** to cast required spells.
- **Sound notifications** (choose sound + volume).
- **Custom sound support** using `.wav` files.

---

## Custom Sound Setup

To use a custom sound:

1. Place your `.wav` file in the folder:  
   `src/main/resources/sounds/`
2. In the plugin configuration, enter the exact filename:  
   e.g., `MyCustomSound.wav`

---

## How to Test

To test the plugin:

1. Be on the **wrong spellbook**, **or**
2. Be **missing runes** for the selected spell(s)
3. Approach the **entry area** of:
    - COX (e.g. raid door)
    - TOB (e.g. theatre entrance)
    - TOA (e.g. invocation mirror)
4. You should see a **popup** and hear the **configured sound**.

---

## Configuration

To get started:

- Open the **plugin configuration** panel
- Enable the raids you'd like to monitor
- Select the appropriate **spellbook** and required **spells** for each raid
- Customize:
    - **Notification sound**
    - **Sound volume**
    - **Popup message appearance**

---