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

1. Locate your `.runelite` folder

On windows this is likely to be here: `C:\Users\<your username>\.runelite`

If you aren't sure, it's the same place that stores your `settings.properties`

Within this `.runelite` folder, there should be a `raid-spell-checker-sounds` folder, which is where you will put your custom sound files

2. Prepare your sound files

Make sure your custom file is in `.wav` format (just changing the extension won't work, actually convert them)

Under the Notification section, add your file name to the `Custom Sound` field and ensure the Sound Effect is set to `Custom` - The file name must __exactly__ match


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