package org.RaidSpellChecker;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
public final class RaidSpellCheckerFileManager
{
    private RaidSpellCheckerFileManager() {}

    public static final Path PLUGIN_DIR = RuneLite.RUNELITE_DIR.toPath().resolve("raid-spell-checker");
    public static final Path SOUNDS_DIR = PLUGIN_DIR.resolve("sounds");

    public static void ensureDirs()
    {
        try
        {
            Files.createDirectories(SOUNDS_DIR);
        }
        catch (IOException e)
        {
            log.error("Failed to create Raid Spell Checker directories: {}", SOUNDS_DIR, e);
        }
    }
}