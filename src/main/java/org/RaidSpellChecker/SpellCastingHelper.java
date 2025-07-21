package org.RaidSpellChecker;

import net.runelite.api.*;

import java.util.*;

public class SpellCastingHelper
{
    private static final int[] DIVINE_RUNE_POUCH_RUNES = {
            13385, // slot 1 rune type
            13388, // slot 2 rune type
            13391, // slot 3 rune type
            14285  // slot 4 rune type
    };

    private static final int[] DIVINE_RUNE_POUCH_AMOUNTS = {
            13386, // slot 1 rune amount
            13389, // slot 2 rune amount
            13392, // slot 3 rune amount
            14286  // slot 4 rune amount
    };
    private static final Map<Integer, List<Integer>> STAFF_SUBSTITUTIONS = Map.ofEntries(
            // Air staves
            Map.entry(ItemID.STAFF_OF_AIR, List.of(ItemID.AIR_RUNE)),
            Map.entry(ItemID.AIR_BATTLESTAFF, List.of(ItemID.AIR_RUNE)),
            Map.entry(ItemID.MYSTIC_AIR_STAFF, List.of(ItemID.AIR_RUNE)),

            // Water staves
            Map.entry(ItemID.STAFF_OF_WATER, List.of(ItemID.WATER_RUNE)),
            Map.entry(ItemID.WATER_BATTLESTAFF, List.of(ItemID.WATER_RUNE)),
            Map.entry(ItemID.MYSTIC_WATER_STAFF, List.of(ItemID.WATER_RUNE)),

            // Earth staves
            Map.entry(ItemID.STAFF_OF_EARTH, List.of(ItemID.EARTH_RUNE)),
            Map.entry(ItemID.EARTH_BATTLESTAFF, List.of(ItemID.EARTH_RUNE)),
            Map.entry(ItemID.MYSTIC_EARTH_STAFF, List.of(ItemID.EARTH_RUNE)),

            // Fire staves
            Map.entry(ItemID.STAFF_OF_FIRE, List.of(ItemID.FIRE_RUNE)),
            Map.entry(ItemID.FIRE_BATTLESTAFF, List.of(ItemID.FIRE_RUNE)),
            Map.entry(ItemID.MYSTIC_FIRE_STAFF, List.of(ItemID.FIRE_RUNE)),

            // Combination staves
            Map.entry(ItemID.MIST_BATTLESTAFF, List.of(ItemID.AIR_RUNE, ItemID.WATER_RUNE)),
            Map.entry(ItemID.MYSTIC_MIST_STAFF, List.of(ItemID.AIR_RUNE, ItemID.WATER_RUNE)),

            Map.entry(ItemID.DUST_BATTLESTAFF, List.of(ItemID.AIR_RUNE, ItemID.EARTH_RUNE)),
            Map.entry(ItemID.MYSTIC_DUST_STAFF, List.of(ItemID.AIR_RUNE, ItemID.EARTH_RUNE)),

            Map.entry(ItemID.SMOKE_BATTLESTAFF, List.of(ItemID.AIR_RUNE, ItemID.FIRE_RUNE)),
            Map.entry(ItemID.MYSTIC_SMOKE_STAFF, List.of(ItemID.AIR_RUNE, ItemID.FIRE_RUNE)),

            Map.entry(ItemID.STEAM_BATTLESTAFF, List.of(ItemID.WATER_RUNE, ItemID.FIRE_RUNE)),
            Map.entry(ItemID.MYSTIC_STEAM_STAFF, List.of(ItemID.WATER_RUNE, ItemID.FIRE_RUNE)),

            Map.entry(ItemID.LAVA_BATTLESTAFF, List.of(ItemID.FIRE_RUNE, ItemID.EARTH_RUNE)),
            Map.entry(ItemID.MYSTIC_LAVA_STAFF, List.of(ItemID.FIRE_RUNE, ItemID.EARTH_RUNE))
    );
    private static final Map<Integer, List<Integer>> COMBO_RUNE_SUBSTITUTIONS = Map.ofEntries(
            Map.entry(ItemID.MUD_RUNE, List.of(ItemID.WATER_RUNE, ItemID.EARTH_RUNE)),
            Map.entry(ItemID.MIST_RUNE, List.of(ItemID.WATER_RUNE, ItemID.AIR_RUNE)),
            Map.entry(ItemID.DUST_RUNE, List.of(ItemID.EARTH_RUNE, ItemID.AIR_RUNE)),
            Map.entry(ItemID.SMOKE_RUNE, List.of(ItemID.FIRE_RUNE, ItemID.AIR_RUNE)),
            Map.entry(ItemID.STEAM_RUNE, List.of(ItemID.FIRE_RUNE, ItemID.WATER_RUNE)),
            Map.entry(ItemID.LAVA_RUNE, List.of(ItemID.FIRE_RUNE, ItemID.EARTH_RUNE)),
            Map.entry(ItemID.AETHER_RUNE, List.of(ItemID.COSMIC_RUNE, ItemID.SOUL_RUNE)),
            Map.entry(ItemID.SUNFIRE_RUNE, List.of(ItemID.FIRE_RUNE))
    );

    private static final Map<Integer, Integer> RUNE_VARBIT_ID_TO_ITEM_ID = Map.ofEntries(
            Map.entry(0, -1), // empty
            Map.entry(1, ItemID.AIR_RUNE),
            Map.entry(2, ItemID.WATER_RUNE),
            Map.entry(3, ItemID.EARTH_RUNE),
            Map.entry(4, ItemID.FIRE_RUNE),
            Map.entry(5, ItemID.MIND_RUNE),
            Map.entry(6, ItemID.CHAOS_RUNE),
            Map.entry(7, ItemID.DEATH_RUNE),
            Map.entry(8, ItemID.BLOOD_RUNE),
            Map.entry(9, ItemID.COSMIC_RUNE),
            Map.entry(10, ItemID.NATURE_RUNE),
            Map.entry(11, ItemID.LAW_RUNE),
            Map.entry(12, ItemID.BODY_RUNE),
            Map.entry(13, ItemID.SOUL_RUNE),
            Map.entry(14, ItemID.ASTRAL_RUNE),
            Map.entry(15, ItemID.WRATH_RUNE),
            Map.entry(16, ItemID.MIST_RUNE),
            Map.entry(17, ItemID.DUST_RUNE),
            Map.entry(18, ItemID.MUD_RUNE),
            Map.entry(19, ItemID.SMOKE_RUNE),
            Map.entry(20, ItemID.STEAM_RUNE),
            Map.entry(21, ItemID.LAVA_RUNE),
            Map.entry(22, ItemID.SUNFIRE_RUNE),
            Map.entry(23, ItemID.AETHER_RUNE)
    );

    public static boolean canCastSpell(Client client, SpellDefinition spell)
    {
        Set<Integer> staffRunes = getRunesProvidedByEquippedStaff(client);
        Map<Integer, Integer> runeCounts = new HashMap<>();

        // Inventory runes
        mergeRuneCounts(runeCounts, getRunesFromInventory(client));

        // Rune pouch runes
        mergeRuneCounts(runeCounts, getRunePouchContents(client));

        // Divine rune pouch runes
        mergeRuneCounts(runeCounts, getDivineRunePouchContents(client));

        for (SpellRuneRequirement req : spell.getRuneRequirements())
        {
            int runeId = req.getRuneId();
            int requiredAmount = req.getAmount();

            if (staffRunes.contains(runeId))
                continue;

            int available = runeCounts.getOrDefault(runeId, 0);
            if (available >= requiredAmount)
                continue;

            int comboRuneCount = 0;
            for (Map.Entry<Integer, List<Integer>> combo : COMBO_RUNE_SUBSTITUTIONS.entrySet())
            {
                if (combo.getValue().contains(runeId))
                {
                    comboRuneCount += runeCounts.getOrDefault(combo.getKey(), 0);
                }
            }

            if ((available + comboRuneCount) < requiredAmount)
            {
                return false;
            }
        }

        return true;
    }

    public static boolean canCastAnySpell(Client client, List<SpellDefinition> spellGroup)
    {
        for (SpellDefinition spell : spellGroup)
        {
            if (canCastSpell(client, spell))
            {
                return true;
            }
        }
        return false;
    }

    private static Map<Integer, Integer> getRunesFromInventory(Client client)
    {
        Map<Integer, Integer> result = new HashMap<>();
        ItemContainer inventory = client.getItemContainer(InventoryID.INVENTORY);
        if (inventory == null) return result;

        for (Item item : inventory.getItems())
        {
            if (item == null) continue;
            result.merge(item.getId(), item.getQuantity(), Integer::sum);
        }
        return result;
    }

    private static Map<Integer, Integer> getRunePouchContents(Client client)
    {
        Map<Integer, Integer> result = new HashMap<>();

        int[] runeVarbits = {
                Varbits.RUNE_POUCH_RUNE1,
                Varbits.RUNE_POUCH_RUNE2,
                Varbits.RUNE_POUCH_RUNE3
        };

        int[] amountVarbits = {
                Varbits.RUNE_POUCH_AMOUNT1,
                Varbits.RUNE_POUCH_AMOUNT2,
                Varbits.RUNE_POUCH_AMOUNT3
        };

        for (int i = 0; i < runeVarbits.length; i++)
        {
            int runeCode = client.getVarbitValue(runeVarbits[i]);
            int amount = client.getVarbitValue(amountVarbits[i]);
            Integer itemId = RUNE_VARBIT_ID_TO_ITEM_ID.get(runeCode);
            if (itemId != null && itemId != -1 && amount > 0)
            {
                result.merge(itemId, amount, Integer::sum);
            }
        }
        return result;
    }

    private static Map<Integer, Integer> getDivineRunePouchContents(Client client)
    {
        Map<Integer, Integer> result = new HashMap<>();

        for (int i = 0; i < DIVINE_RUNE_POUCH_RUNES.length; i++)
        {
            int runeCode = client.getVarbitValue(DIVINE_RUNE_POUCH_RUNES[i]);
            int amount = client.getVarbitValue(DIVINE_RUNE_POUCH_AMOUNTS[i]);
            Integer itemId = RUNE_VARBIT_ID_TO_ITEM_ID.get(runeCode);
            if (itemId != null && itemId != -1 && amount > 0)
            {
                result.merge(itemId, amount, Integer::sum);
            }
        }

        return result;
    }

    private static void mergeRuneCounts(Map<Integer, Integer> target, Map<Integer, Integer> source)
    {
        for (Map.Entry<Integer, Integer> entry : source.entrySet())
        {
            target.merge(entry.getKey(), entry.getValue(), Integer::sum);
        }
    }

    private static Set<Integer> getRunesProvidedByEquippedStaff(Client client)
    {
        ItemContainer equipment = client.getItemContainer(InventoryID.EQUIPMENT);
        if (equipment == null) return Set.of();

        Set<Integer> runeSubs = new HashSet<>();
        for (Item item : equipment.getItems())
        {
            if (item == null) continue;
            List<Integer> providedRunes = STAFF_SUBSTITUTIONS.get(item.getId());
            if (providedRunes != null)
            {
                runeSubs.addAll(providedRunes);
            }
        }

        return runeSubs;
    }
}