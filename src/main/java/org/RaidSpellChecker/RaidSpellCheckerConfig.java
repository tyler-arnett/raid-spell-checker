package org.RaidSpellChecker;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;
import net.runelite.client.config.Units;

@ConfigGroup("raidspellchecker")
public interface RaidSpellCheckerConfig extends Config
{
	enum Spellbook
	{
		STANDARD,
		ANCIENT,
		LUNAR,
		ARCEUUS
	}

	enum Sound
	{
		RUBY_BOLTS,
		FAIRY_RING,
		ICE_BARRAGE,
		EEEEEEEE,
		CUSTOM
	}

//region COX
	@ConfigSection(
			name = "COX",
			description = "Spells to use in COX",
			position  = 1,
			closedByDefault = true
	)
	String coxSpellbookSection = "COX Spellbook";

	// === COX Spellbook Selector === //
	@ConfigItem(
			position = 1,
			keyName = "enableCOX",
			name = "Enable",
			description = "Enabled for COX",
			section = coxSpellbookSection
	)
	default boolean enableCox() {return true;}
	@ConfigItem(
			position = 2,
			keyName = "spellSelectionCOX",
			name = "Spellbook selection",
			description = "Spellbook to use for COX",
			section = coxSpellbookSection
	)
	default Spellbook spellSelectionCOX() {return Spellbook.STANDARD;}

	// === COX Standard Spells === //
	@ConfigSection(
			name = "COX Standard Spells",
			description = "Standard spells to use in COX",
			position  = 2,
			closedByDefault = true
	)
	String coxStandardSpells = "COX Standard Spells";
	@ConfigItem(position = 1,
			keyName = "labelCOXStandard",
			name = "", description = "",
			section = coxStandardSpells)
	default String labelCOXStandard() { return "Standard spell requirements for COX."; }
	@ConfigItem(position = 2,
			keyName = "useFireCOX",
			name = "Fire Spell",
			description = "Use a fire spell (Standard)",
			section = coxStandardSpells
			)
	default boolean useFireCOX() {return false;}
	@ConfigItem(position = 3,
			keyName = "useWaterCOX",
			name = "Water Spell",
			description = "Use a water spell (Standard)",
			section = coxStandardSpells
	)
	default boolean useWaterCOX() {return false;}
	@ConfigItem(position = 4,
			keyName = "useEarthCOX",
			name = "Earth Spell",
			description = "Use an earth spell (Standard)",
			section = coxStandardSpells
	)
	default boolean useEarthCOX() {return false;}
	@ConfigItem(position = 5,
			keyName = "useAirCOX",
			name = "Air Spell",
			description = "Use an air spell (Standard)",
			section = coxStandardSpells
	)
	default boolean useAirCOX() {return false;}

	// === COX Arceuus Spells === //
	@ConfigSection(
			name = "COX Arceuus Spells",
			description = "Arceuus spells to use in COX",
			position  = 3,
			closedByDefault = true
	)
	String coxArceuusSpells = "COX Arceuus Spells";
	@ConfigItem(position = 1,
			keyName = "labelCOXArceuus",
			name = "", description = "",
			section = coxArceuusSpells)
	default String labelCOXArceuus() { return "Arceuus spell requirements for COX."; }
	@ConfigItem(position = 2,
			keyName = "useGraspCOX",
			name = "Grasp Spell",
			description = "Use a grasp spell (Arceuus)",
			section = coxArceuusSpells
	)
	default boolean useGraspCOX() {return false;}
	@ConfigItem(position = 3,
			keyName = "useDemonbaneCOX",
			name = "Demonbane Spell",
			description = "Use a demonbane spell (Arceuus)",
			section = coxArceuusSpells
	)
	default boolean useDemonbaneCOX() {return false;}
	@ConfigItem(position = 4,
			keyName = "useThrallsCOX",
			name = "Thralls",
			description = "Use a thrall spell (Arceuus)",
			section = coxArceuusSpells
	)
	default boolean useThrallsCOX() {return false;}
	@ConfigItem(position = 5,
			keyName = "useDeathChargeCOX",
			name = "Death Charge",
			description = "Use the death charge spell (Arceuus)",
			section = coxArceuusSpells
	)
	default boolean useDeathChargeCOX() {return false;}
	@ConfigItem(position = 6,
			keyName = "useMarkOfDarknessCOX",
			name = "Mark of Darkness",
			description = "Use the mark of darkness spell (Arceuus)",
			section = coxArceuusSpells
	)
	default boolean useMarkOfDarknessCOX() {return false;}

	// === COX Ancient Spells === //
	@ConfigSection(
			name = "COX Ancient Spells",
			description = "Ancient spells to use in COX",
			position  = 4,
			closedByDefault = true
	)
	String coxAncientSpells = "COX Ancient Spells";
	@ConfigItem(position = 1,
			keyName = "labelCOXAncient",
			name = "", description = "",
			section = coxAncientSpells)
	default String labelCOXAncient() { return "Ancient spell requirements for COX."; }
	@ConfigItem(position = 2,
			keyName = "useSmokeCOX",
			name = "Smoke Spell",
			description = "Use a smoke spell (Ancient)",
			section = coxAncientSpells
	)
	default boolean useSmokeCOX() {return false;}
	@ConfigItem(position = 3,
			keyName = "useShadowCOX",
			name = "Shadow Spell",
			description = "Use a shadow spell (Ancient)",
			section = coxAncientSpells
	)
	default boolean useShadowCOX() {return false;}
	@ConfigItem(position = 4,
			keyName = "useBloodCOX",
			name = "Blood Spell",
			description = "Use a blood spell (Ancient)",
			section = coxAncientSpells
	)
	default boolean useBloodCOX() {return false;}
	@ConfigItem(position = 5,
			keyName = "useIceCOX",
			name = "Ice Spell",
			description = "Use an ice spell (Ancient)",
			section = coxAncientSpells
	)
	default boolean useIceCOX() {return false;}

	// === COX Lunar Spells === //
	@ConfigSection(
			name = "COX Lunar Spells",
			description = "Lunar spells to use in COX",
			position  = 5,
			closedByDefault = true
	)
	String coxLunarSpells = "COX Lunar Spells";
	@ConfigItem(position = 1,
			keyName = "labelCOXLunar",
			name = "", description = "",
			section = coxLunarSpells)
	default String labelCOXLunar() { return "Lunar spell requirements for COX."; }
	@ConfigItem(position = 2,
			keyName = "useCureCOX",
			name = "Cure Spells",
			description = "Use a cure spell (Lunar)",
			section = coxLunarSpells
	)
	default boolean useCureCOX() {return false;}
	@ConfigItem(position = 3,
			keyName = "usePotShareCOX",
			name = "Pot Share",
			description = "Use a potshare spell (Lunar)",
			section = coxLunarSpells
	)
	default boolean usePotShareCOX() {return false;}
	@ConfigItem(position = 4,
			keyName = "useVengeCOX",
			name = "Venge",
			description = "Use the venge spell (Lunar)",
			section = coxLunarSpells
	)
	default boolean useVengeCOX() {return false;}
	@ConfigItem(position = 5,
			keyName = "useHumidifyCOX",
			name = "Humidify",
			description = "Use the humidify spell (Lunar)",
			section = coxLunarSpells
	)
	default boolean useHumidifyCOX() {return false;}

//endregion
//---------------------------------------------------------------------------------------------------------------------------------
//region TOB
	@ConfigSection(
			name = "TOB",
			description = "Spells to use in TOB",
			position  = 6,
			closedByDefault = true
	)
	String tobSpellbookSection = "TOB";
	@ConfigItem(
			position = 1,
			keyName = "enableTOB",
			name = "Enable",
			description = "Enabled for TOB",
			section = tobSpellbookSection
	)
	default boolean enableTOB() {return true;}
	@ConfigItem(
			position = 2,
			keyName = "spellSelectionTOB",
			name = "Spellbook selection",
			description = "Spellbook to use for TOB",
			section = tobSpellbookSection
	)
	default Spellbook spellSelectionTOB() {return Spellbook.STANDARD;}

	// === TOB Standard Spells === //
	@ConfigSection(
			name = "TOB Standard Spells",
			description = "Standard spells to use in TOB",
			position  = 7,
			closedByDefault = true
	)
	String tobStandardSpells = "TOB Standard Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOBStandard",
			name = "", description = "",
			section = tobStandardSpells)
	default String labelTOBStandard() { return "Standard spell requirements for TOB."; }
	@ConfigItem(position = 2,
			keyName = "useFireTOB",
			name = "Fire Spell",
			description = "Use a fire spell (Standard)",
			section = tobStandardSpells
	)
	default boolean useFireTOB() {return false;}
	@ConfigItem(position = 3,
			keyName = "useWaterTOB",
			name = "Water Spell",
			description = "Use a water spell (Standard)",
			section = tobStandardSpells
	)
	default boolean useWaterTOB() {return false;}
	@ConfigItem(position = 4,
			keyName = "useEarthTOB",
			name = "Earth Spell",
			description = "Use an earth spell (Standard)",
			section = tobStandardSpells
	)
	default boolean useEarthTOB() {return false;}
	@ConfigItem(position = 5,
			keyName = "useAirTOB",
			name = "Air Spell",
			description = "Use an air spell (Standard)",
			section = tobStandardSpells
	)
	default boolean useAirTOB() {return false;}

	// === TOB Arceuus Spells === //
	@ConfigSection(
			name = "TOB Arceuus Spells",
			description = "Arceuus spells to use in TOB",
			position  = 8,
			closedByDefault = true
	)
	String tobArceuusSpells = "TOB Arceuus Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOBArceuus",
			name = "", description = "",
			section = tobArceuusSpells)
	default String labelTOBArceuus() { return "Arceuus spell requirements for TOB."; }
	@ConfigItem(position = 2,
			keyName = "useGraspTOB",
			name = "Grasp Spell",
			description = "Use a grasp spell (Arceuus)",
			section = tobArceuusSpells
	)
	default boolean useGraspTOB() {return false;}
	@ConfigItem(position = 3,
			keyName = "useDemonbaneTOB",
			name = "Demonbane Spell",
			description = "Use a demonbane spell (Arceuus)",
			section = tobArceuusSpells
	)
	default boolean useDemonbaneTOB() {return false;}
	@ConfigItem(position = 4,
			keyName = "useThrallsTOB",
			name = "Thralls",
			description = "Use a thrall spell (Arceuus)",
			section = tobArceuusSpells
	)
	default boolean useThrallsTOB() {return false;}
	@ConfigItem(position = 5,
			keyName = "useDeathChargeTOB",
			name = "Death Charge",
			description = "Use the death charge spell (Arceuus)",
			section = tobArceuusSpells
	)
	default boolean useDeathChargeTOB() {return false;}
	@ConfigItem(position = 6,
			keyName = "useMarkOfDarknessTOB",
			name = "Mark of Darkness",
			description = "Use the mark of darkness spell (Arceuus)",
			section = tobArceuusSpells
	)
	default boolean useMarkOfDarknessTOB() {return false;}

	// === TOB Ancient Spells === //
	@ConfigSection(
			name = "TOB Ancient Spells",
			description = "Ancient spells to use in TOB",
			position  = 9,
			closedByDefault = true
	)
	String tobAncientSpells = "TOB Ancient Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOBAncient",
			name = "", description = "",
			section = tobAncientSpells)
	default String labelTOBAncient() { return "Ancient spell requirements for TOB."; }
	@ConfigItem(position = 2,
			keyName = "useSmokeTOB",
			name = "Smoke Spell",
			description = "Use a smoke spell (Ancient)",
			section = tobAncientSpells
	)
	default boolean useSmokeTOB() {return false;}
	@ConfigItem(position = 3,
			keyName = "useShadowTOB",
			name = "Shadow Spell",
			description = "Use a shadow spell (Ancient)",
			section = tobAncientSpells
	)
	default boolean useShadowTOB() {return false;}
	@ConfigItem(position = 4,
			keyName = "useBloodTOB",
			name = "Blood Spell",
			description = "Use a blood spell (Ancient)",
			section = tobAncientSpells
	)
	default boolean useBloodTOB() {return false;}
	@ConfigItem(position = 5,
			keyName = "useIceTOB",
			name = "Ice Spell",
			description = "Use an ice spell (Ancient)",
			section = tobAncientSpells
	)
	default boolean useIceTOB() {return false;}

	// === TOB Lunar Spells === //
	@ConfigSection(
			name = "TOB Lunar Spells",
			description = "Lunar spells to use in TOB",
			position  = 10,
			closedByDefault = true
	)
	String tobLunarSpells = "TOB Lunar Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOBLunar",
			name = "", description = "",
			section = tobLunarSpells)
	default String labelTOBLunar() { return "Lunar spell requirements for TOB."; }
	@ConfigItem(position = 2,
			keyName = "useCureTOB",
			name = "Cure Spells",
			description = "Use a cure spell (Lunar)",
			section = tobLunarSpells
	)
	default boolean useCureTOB() {return false;}
	@ConfigItem(position = 3,
			keyName = "usePotShareTOB",
			name = "Pot Share",
			description = "Use a potshare spell (Lunar)",
			section = tobLunarSpells
	)
	default boolean usePotShareTOB() {return false;}
	@ConfigItem(position = 4,
			keyName = "useVengeTOB",
			name = "Venge",
			description = "Use the venge spell (Lunar)",
			section = tobLunarSpells
	)
	default boolean useVengeTOB() {return false;}
	@ConfigItem(position = 5,
			keyName = "useHumidifyTOB",
			name = "Humidify",
			description = "Use the humidify spell (Lunar)",
			section = tobLunarSpells
	)
	default boolean useHumidifyTOB() {return false;}
//endregion
//---------------------------------------------------------------------------------------------------------------------------------
//region TOA
	@ConfigSection(
			name = "TOA",
			description = "Spells to use in TOA",
			position  = 11,
			closedByDefault = true
	)
	String toaSpellbookSection = "TOA";
	@ConfigItem(
			position = 1,
			keyName = "enableTOA",
			name = "Enable",
			description = "Enabled for TOA",
			section = toaSpellbookSection
	)
	default boolean enableTOA() {return true;}
	@ConfigItem(
			position = 2,
			keyName = "spellSelectionTOA",
			name = "Spellbook selection",
			description = "Spellbook to use for TOA",
			section = toaSpellbookSection
	)
	default Spellbook spellSelectionTOA() {return Spellbook.STANDARD;}
	// === TOA Standard Spells === //
	@ConfigSection(
			name = "TOA Standard Spells",
			description = "Standard spells to use in TOA",
			position  = 12,
			closedByDefault = true
	)
	String toaStandardSpells = "TOA Standard Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOAStandard",
			name = "", description = "",
			section = toaStandardSpells)
	default String labelTOAStandard() { return "Standard spell requirements for TOA."; }
	@ConfigItem(position = 2,
			keyName = "useFireTOA",
			name = "Fire Spell",
			description = "Use a fire spell (Standard)",
			section = toaStandardSpells
	)
	default boolean useFireTOA() {return false;}
	@ConfigItem(position = 3,
			keyName = "useWaterTOA",
			name = "Water Spell",
			description = "Use a water spell (Standard)",
			section = toaStandardSpells
	)
	default boolean useWaterTOA() {return false;}
	@ConfigItem(position = 4,
			keyName = "useEarthTOA",
			name = "Earth Spell",
			description = "Use an earth spell (Standard)",
			section = toaStandardSpells
	)
	default boolean useEarthTOA() {return false;}
	@ConfigItem(position = 5,
			keyName = "useAirTOA",
			name = "Air Spell",
			description = "Use an air spell (Standard)",
			section = toaStandardSpells
	)
	default boolean useAirTOA() {return false;}

	// === TOA Arceuus Spells === //
	@ConfigSection(
			name = "TOA Arceuus Spells",
			description = "Arceuus spells to use in TOA",
			position  = 13,
			closedByDefault = true
	)
	String toaArceuusSpells = "TOA Arceuus Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOAArceuus",
			name = "", description = "",
			section = toaArceuusSpells)
	default String labelTOAArceuus() { return "Arceuus spell requirements for TOA."; }
	@ConfigItem(position = 2,
			keyName = "useGraspTOA",
			name = "Grasp Spell",
			description = "Use a grasp spell (Arceuus)",
			section = toaArceuusSpells
	)
	default boolean useGraspTOA() {return false;}
	@ConfigItem(position = 3,
			keyName = "useDemonbaneTOA",
			name = "Demonbane Spell",
			description = "Use a demonbane spell (Arceuus)",
			section = toaArceuusSpells
	)
	default boolean useDemonbaneTOA() {return false;}
	@ConfigItem(position = 4,
			keyName = "useThrallsTOA",
			name = "Thralls",
			description = "Use a thrall spell (Arceuus)",
			section = toaArceuusSpells
	)
	default boolean useThrallsTOA() {return false;}
	@ConfigItem(position = 5,
			keyName = "useDeathChargeTOA",
			name = "Death Charge",
			description = "Use the death charge spell (Arceuus)",
			section = toaArceuusSpells
	)
	default boolean useDeathChargeTOA() {return false;}
	@ConfigItem(position = 6,
			keyName = "useMarkOfDarknessTOA",
			name = "Mark of Darkness",
			description = "Use the mark of darkness spell (Arceuus)",
			section = toaArceuusSpells
	)
	default boolean useMarkOfDarknessTOA() {return false;}

	// === TOA Ancient Spells === //
	@ConfigSection(
			name = "TOA Ancient Spells",
			description = "Ancient spells to use in TOA",
			position  = 14,
			closedByDefault = true
	)
	String toaAncientSpells = "TOA Ancient Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOAAncient",
			name = "", description = "",
			section = toaAncientSpells)
	default String labelTOAAncient() { return "Ancient spell requirements for TOA."; }
	@ConfigItem(position = 2,
			keyName = "useSmokeTOA",
			name = "Smoke Spell",
			description = "Use a smoke spell (Ancient)",
			section = toaAncientSpells
	)
	default boolean useSmokeTOA() {return false;}
	@ConfigItem(position = 3,
			keyName = "useShadowTOA",
			name = "Shadow Spell",
			description = "Use a shadow spell (Ancient)",
			section = toaAncientSpells
	)
	default boolean useShadowTOA() {return false;}
	@ConfigItem(position = 4,
			keyName = "useBloodTOA",
			name = "Blood Spell",
			description = "Use a blood spell (Ancient)",
			section = toaAncientSpells
	)
	default boolean useBloodTOA() {return false;}
	@ConfigItem(position = 5,
			keyName = "useIceTOA",
			name = "Ice Spell",
			description = "Use an ice spell (Ancient)",
			section = toaAncientSpells
	)
	default boolean useIceTOA() {return false;}

	// === TOA Lunar Spells === //
	@ConfigSection(
			name = "TOA Lunar Spells",
			description = "Lunar spells to use in TOA",
			position  = 15,
			closedByDefault = true
	)
	String toaLunarSpells = "TOA Lunar Spells";
	@ConfigItem(position = 1,
			keyName = "labelTOALunar",
			name = "", description = "",
			section = toaLunarSpells)
	default String labelTOALunar() { return "Lunar spell requirements for TOA."; }
	@ConfigItem(position = 2,
			keyName = "useCureTOA",
			name = "Cure Spells",
			description = "Use a cure spell (Lunar)",
			section = toaLunarSpells
	)
	default boolean useCureTOA() {return false;}
	@ConfigItem(position = 3,
			keyName = "usePotShareTOA",
			name = "Pot Share",
			description = "Use a potshare spell (Lunar)",
			section = toaLunarSpells
	)
	default boolean usePotShareTOA() {return false;}
	@ConfigItem(position = 4,
			keyName = "useVengeTOA",
			name = "Venge",
			description = "Use the venge spell (Lunar)",
			section = toaLunarSpells
	)
	default boolean useVengeTOA() {return false;}
	@ConfigItem(position = 5,
			keyName = "useHumidifyTOA",
			name = "Humidify",
			description = "Use the humidify spell (Lunar)",
			section = toaLunarSpells
	)
	default boolean useHumidifyTOA() {return false;}
//endregion
//region Sound Effects
@ConfigSection(
		name = "Sound Effect to Play",
		description = "Sound effect to play when the player is missing runes",
		position  = 16,
		closedByDefault = true
)
String soundEffect = "Sound Effect to Play";
	@ConfigItem(position = 1,
			keyName = "soundEffect",
			name = "Sound Effect",
			description = "Sound effect to play",
			section = soundEffect
	)
	default Sound soundEffect() {return Sound.RUBY_BOLTS;}
	@Range(min = 0, max = 100)
	@Units(Units.PERCENT)
	@ConfigItem(
			position = 2, // Position under Sound ID
			keyName = "soundVolume",
			name = "Sound Volume",
			description = "Volume of the sound effect (0% = mute, 100% = full volume)",
			section = soundEffect
	)
	default int soundVolume()
	{ return 50;}
	@ConfigItem(
			position = 3, // Position under Sound ID
			keyName = "customSound",
			name = "Custom Sound",
			description = "Name of custom .wav file placed in \\raid-spell-checker\\src\\main\\resources\\sounds",
			section = soundEffect
	)
	default String customSound() { return "myfile.wav";}
//endregion
}
