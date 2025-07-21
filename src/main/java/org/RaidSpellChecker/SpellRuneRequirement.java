package org.RaidSpellChecker;

public class SpellRuneRequirement
{
    private final int runeId;
    private final int amount;

    public SpellRuneRequirement(int runeId, int amount)
    {
        this.runeId = runeId;
        this.amount = amount;
    }

    public int getRuneId()
    {
        return runeId;
    }

    public int getAmount()
    {
        return amount;
    }
}