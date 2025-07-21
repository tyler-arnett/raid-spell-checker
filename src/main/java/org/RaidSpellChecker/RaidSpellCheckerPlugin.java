package org.RaidSpellChecker;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.coords.LocalPoint;
import net.runelite.api.coords.WorldArea;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.GameTick;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@PluginDescriptor(
	name = "Raid Spell Checker"
)
public class RaidSpellCheckerPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private RaidSpellCheckerConfig config;

	private WorldPoint lastPlayerLocation;
	private WorldPoint currentPlayerLocation;
	private static final WorldArea toaZone = new WorldArea(3357,9113,5,4,0);
	private static final WorldArea tobZone = new WorldArea(3670,3215,7,8,0);
	private WorldArea coxZone;
    private boolean justLoadedRaidScene = false;
	private boolean raidLayoutChecked = false;
	private int lastRaidBaseRegion = -1;
	private long lastSceneIdentity = -1;
	private int currentRaidLayout = -1;
	private boolean wrongSpellbook;

	@Override
	protected void startUp() throws Exception
	{
		log.info("Raid Rune Checker started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("Raid Rune Checker stopped!");
	}

	public WorldPoint getPlayerLocation()
	{
		if (client.getLocalPlayer() == null) return null;
		return client.getLocalPlayer().getWorldLocation();
	}

	public List<String> getSelectedSpellsCOX()
	{
		RaidSpellCheckerConfig.Spellbook activeSpellbook = config.spellSelectionCOX();
		List<String> selected = new ArrayList<>();
		int currentSpellbook = client.getVarbitValue(Varbits.SPELLBOOK);
		if (currentSpellbook != activeSpellbook.ordinal())
		{
			selected.add("WRONG SPELLBOOK");
			return selected;
		}
		switch (activeSpellbook)
		{
			case STANDARD:
				if (config.useAirCOX()) selected.add("Air Spell");
				if (config.useEarthCOX()) selected.add("Earth Spell");
				if (config.useFireCOX()) selected.add("Fire Spell");
				if (config.useWaterCOX()) selected.add("Water Spell");
				break;
			case ARCEUUS:
				if (config.useDemonbaneCOX()) selected.add("Demonbane");
				if (config.useGraspCOX()) selected.add("Grasp");
				if (config.useThrallsCOX()) selected.add("Thralls");
				if (config.useDeathChargeCOX()) selected.add("Death Charge");
				if (config.useMarkOfDarknessCOX()) selected.add("Mark of Darkness");
				break;
			case ANCIENT:
				if (config.useSmokeCOX()) selected.add("Smoke Spell");
				if (config.useShadowCOX()) selected.add("Shadow Spell");
				if (config.useBloodCOX()) selected.add("Blood Spell");
				if (config.useIceCOX()) selected.add("Ice Spell");
				break;
			case LUNAR:
				if (config.useCureCOX()) selected.add("Cure Spell");
				if (config.usePotShareCOX()) selected.add("Pot Share");
				if (config.useVengeCOX()) selected.add("Venge");
				if (config.useHumidifyCOX()) selected.add("Humidify");
				break;
		}
		return selected;
	}
	public List<String> getSelectedSpellsTOB()
	{
		RaidSpellCheckerConfig.Spellbook activeSpellbook = config.spellSelectionTOB();
		List<String> selected = new ArrayList<>();
		int currentSpellbook = client.getVarbitValue(Varbits.SPELLBOOK);
		if (currentSpellbook != activeSpellbook.ordinal())
		{
			selected.add("WRONG SPELLBOOK");
			return selected;
		}
		switch (activeSpellbook)
		{
			case STANDARD:
				if (config.useAirTOB()) selected.add("Air Spell");
				if (config.useEarthTOB()) selected.add("Earth Spell");
				if (config.useFireTOB()) selected.add("Fire Spell");
				if (config.useWaterTOB()) selected.add("Water Spell");
				break;
			case ARCEUUS:
				if (config.useDemonbaneTOB()) selected.add("Demonbane");
				if (config.useGraspTOB()) selected.add("Grasp");
				if (config.useThrallsTOB()) selected.add("Thralls");
				if (config.useDeathChargeTOB()) selected.add("Death Charge");
				if (config.useMarkOfDarknessTOB()) selected.add("Mark of Darkness");
				break;
			case ANCIENT:
				if (config.useSmokeTOB()) selected.add("Smoke Spell");
				if (config.useShadowTOB()) selected.add("Shadow Spell");
				if (config.useBloodTOB()) selected.add("Blood Spell");
				if (config.useIceTOB()) selected.add("Ice Spell");
				break;
			case LUNAR:
				if (config.useCureTOB()) selected.add("Cure Spell");
				if (config.usePotShareTOB()) selected.add("Pot Share");
				if (config.useVengeTOB()) selected.add("Venge");
				if (config.useHumidifyTOB()) selected.add("Humidify");
				break;
		}
		return selected;
	}
	public List<String> getSelectedSpellsTOA()
	{
		RaidSpellCheckerConfig.Spellbook activeSpellbook = config.spellSelectionTOA();
		List<String> selected = new ArrayList<>();
		int currentSpellbook = client.getVarbitValue(Varbits.SPELLBOOK);
		if (currentSpellbook != activeSpellbook.ordinal())
		{
			selected.add("WRONG SPELLBOOK");
			return selected;
		}
		switch (activeSpellbook)
		{
			case STANDARD:
				if (config.useAirTOA()) selected.add("Air Spell");
				if (config.useEarthTOA()) selected.add("Earth Spell");
				if (config.useFireTOA()) selected.add("Fire Spell");
				if (config.useWaterTOA()) selected.add("Water Spell");
				break;
			case ARCEUUS:
				if (config.useDemonbaneTOA()) selected.add("Demonbane");
				if (config.useGraspTOA()) selected.add("Grasp");
				if (config.useThrallsTOA()) selected.add("Thralls");
				if (config.useDeathChargeTOA()) selected.add("Death Charge");
				if (config.useMarkOfDarknessTOA()) selected.add("Mark of Darkness");
				break;
			case ANCIENT:
				if (config.useSmokeTOA()) selected.add("Smoke Spell");
				if (config.useShadowTOA()) selected.add("Shadow Spell");
				if (config.useBloodTOA()) selected.add("Blood Spell");
				if (config.useIceTOA()) selected.add("Ice Spell");
				break;
			case LUNAR:
				if (config.useCureTOA()) selected.add("Cure Spell");
				if (config.usePotShareTOA()) selected.add("Pot Share");
				if (config.useVengeTOA()) selected.add("Venge");
				if (config.useHumidifyTOA()) selected.add("Humidify");
				break;
		}
		return selected;
	}

	@Subscribe
	public void onGameTick(GameTick event)
	{
		if (client.getLocalPlayer() == null) return;
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
			currentPlayerLocation = getPlayerLocation();
			if (lastPlayerLocation == null) {
				lastPlayerLocation = currentPlayerLocation;
				return;
			}
			WorldPoint previous = lastPlayerLocation;
			lastPlayerLocation = currentPlayerLocation;
			if (config.enableTOA() && !previous.equals(currentPlayerLocation) && !previous.isInArea(toaZone) && currentPlayerLocation.isInArea(toaZone))
			{
				if (!processSpells(getSelectedSpellsTOA()))
				{
					if (wrongSpellbook) client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "WRONG SPELLBOOK", null);
					else client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "WRONG RUNES", null);
					playSound(config.soundEffect());
				}
			}
			else if (config.enableTOB() && !previous.equals(currentPlayerLocation) && !previous.isInArea(tobZone) && currentPlayerLocation.isInArea(tobZone))
			{
				if (!processSpells(getSelectedSpellsTOB()))
				{
					if (wrongSpellbook) client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "WRONG SPELLBOOK", null);
					else client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "WRONG RUNES", null);
					playSound(config.soundEffect());
				}

			}
			// === COX HANDLING. IF I EVER MEET WHO DESIGNED THIS SHIT WE'RE THROWING HANDS === //
			else if (config.enableCox())
			{
				int flowerID = 29864;
				GameObject flower = findObjectInScene(client,flowerID);
				if (flower != null && justLoadedRaidScene && !raidLayoutChecked && client.isInInstancedRegion())
				{
					int[] regions = client.getMapRegions();
					if (regions == null || regions.length == 0)
						return;
					int baseRegion = regions[0];
					long sceneIdentity = flower.getWorldLocation().hashCode();
					if (baseRegion != lastRaidBaseRegion || sceneIdentity != lastSceneIdentity) {
						lastRaidBaseRegion = baseRegion;
						lastSceneIdentity = sceneIdentity;
						raidLayoutChecked = true;
						justLoadedRaidScene = false;
						//int raidLayout = getRaidLayout(currentPlayerLocation);
						//client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "RAID DETECTED. LAYOUT: " + raidLayout + "      RAID REGION: " + baseRegion, null);
					}
				}
				if (!previous.equals(currentPlayerLocation) && !previous.isInArea(coxZone) && currentPlayerLocation.isInArea(coxZone))
				{
					if (!processSpells(getSelectedSpellsCOX()))
					{
						if (wrongSpellbook) client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "WRONG SPELLBOOK", null);
						else client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "WRONG RUNES", null);
						playSound(config.soundEffect());
					}
				}
				// === //
			}
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged event)
	{
		if (event.getGameState() == GameState.LOADING)
		{
			// Scene is unloading, reset flags
			justLoadedRaidScene = true;
			raidLayoutChecked = false;
		}
	}
	public boolean processSpells(List<String> spells)
	{
		wrongSpellbook = false;
		for (String spell : spells)
		{
            switch (spell) {
                case "WRONG SPELLBOOK":
					wrongSpellbook = true;
                    return false;
                case "Air Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.AIR_SPELLS)) return false;
                    break;
                case "Earth Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.EARTH_SPELLS)) return false;
                    break;
                case "Fire Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.FIRE_SPELLS)) return false;
                    break;
                case "Water Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.WATER_SPELLS)) return false;
                    break;
                case "Demonbane":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.DEMONBANE_SPELLS)) return false;
                    break;
                case "Grasp":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.GRASP_SPELLS)) return false;
                    break;
                case "Thralls":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.THRALL_SPELLS)) return false;
                    break;
                case "Death Charge":
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.DEATH_CHARGE)) return false;
                    break;
                case "Mark of Darkness":
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.MARK_OF_DARKNESS)) return false;
                    break;
                case "Smoke Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.SMOKE_SPELLS)) return false;
                    break;
                case "Shadow Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.SHADOW_SPELLS)) return false;
                    break;
                case "Blood Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.BLOOD_SPELLS)) return false;
                    break;
                case "Ice Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.ICE_SPELLS)) return false;
                    break;
                case "Cure Spell":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.CURE_SPELLS)) return false;
                    break;
                case "Pot Share":
                    if (!SpellCastingHelper.canCastAnySpell(client, Spellbook.POT_SHARE_SPELLS)) return false;
                    break;
                case "Venge":
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.VENGEANCE)) return false;
                    break;
                case "Humidify":
                    if (!SpellCastingHelper.canCastSpell(client, Spellbook.HUMIDIFY)) return false;
                    break;
                default:
                    return true;
            }
        }
		return true;
	}
	public int getRaidLayout(WorldPoint playerLocation)
	{
		boolean layout1 = false;
		LocalPoint localPoint = LocalPoint.fromWorld(client, currentPlayerLocation);
		client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "scene x: " + localPoint.getSceneX() + "   scene y: " + localPoint.getSceneY(), null);
		Tile tile = client.getScene().getTiles()[playerLocation.getPlane()][localPoint.getSceneX()+3][localPoint.getSceneY()+11];
		for (GameObject g : tile.getGameObjects())
		{
			if (g != null && g.getId() == 29864) layout1 = true;
		}
		if (layout1)
		{
			currentRaidLayout = 1;
			//SET REGION OF DETECTION
			coxZone = new WorldArea(playerLocation.getX()-9,playerLocation.getY()+11,5,6, playerLocation.getPlane());
			return 1;
		}
		boolean layout2 = false;
		for (GameObject g : client.getScene().getTiles()[playerLocation.getPlane()][localPoint.getSceneX()+13][localPoint.getSceneY()+8].getGameObjects())
		{
			if (g != null && g.getId() == 29864) layout2 = true;
		}
		if (layout2)
		{
			currentRaidLayout = 2;
			//SET REGION OF DETECTION
			coxZone = new WorldArea(playerLocation.getX()+6,playerLocation.getY()+13,6,4, playerLocation.getPlane());
			return 2;
		}
		boolean layout3 = false;
		for (GameObject g : client.getScene().getTiles()[playerLocation.getPlane()][localPoint.getSceneX()+4][localPoint.getSceneY()+1].getGameObjects())
		{
			if (g != null && g.getId() == 29864) layout3 = true;
		}
		if (layout3)
		{
			currentRaidLayout = 3;
			//SET REGION OF DETECTION
			coxZone = new WorldArea(playerLocation.getX()+15,playerLocation.getY()+6,7,5, playerLocation.getPlane());
			return 3;
		}
		//DO NOT MOVE REGION OF DETECTION!!!!!!
		return currentRaidLayout;
    }
	public GameObject findObjectInScene(Client client, int targetObjectId)
	{
		for (int x = 0; x < Constants.SCENE_SIZE; x++)
		{
			for (int y = 0; y < Constants.SCENE_SIZE; y++)
			{
				Tile tile = client.getScene().getTiles()[client.getPlane()][x][y];
				if (tile == null)
					continue;

				for (GameObject obj : tile.getGameObjects())
				{
					if (obj != null && obj.getId() == targetObjectId)
					{
						return obj;
					}
				}
			}
		}
		return null;
	}

	public void playSound(RaidSpellCheckerConfig.Sound sound)
	{
		if (sound.equals(RaidSpellCheckerConfig.Sound.RUBY_BOLTS))
		{
			SoundPlayer.play("Ruby_bolts_(e).wav", config.soundVolume());
		}
		else if (sound.equals(RaidSpellCheckerConfig.Sound.ICE_BARRAGE))
		{
			SoundPlayer.play("Ice_Barrage.wav", config.soundVolume());
		}
		else if (sound.equals(RaidSpellCheckerConfig.Sound.FAIRY_RING))
		{
			SoundPlayer.play("Fairy_rings.wav", config.soundVolume());
		}
		else if (sound.equals(RaidSpellCheckerConfig.Sound.EEEEEEEE))
		{
			SoundPlayer.play("ACB.wav", config.soundVolume());
		}
		else if (sound.equals(RaidSpellCheckerConfig.Sound.CUSTOM))
		{
			SoundPlayer.play(config.customSound(), config.soundVolume());
		}
	}

	@Provides
	RaidSpellCheckerConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(RaidSpellCheckerConfig.class);
	}
}
