package org.RaidSpellChecker;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;
import net.runelite.client.audio.AudioPlayer;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Inject})
public class SoundPlayer
{
    private static final String CUSTOM_DIR_NAME = "raid-spell-checker-sounds";
    private static final Path CUSTOM_DIR = RuneLite.RUNELITE_DIR.toPath().resolve(CUSTOM_DIR_NAME);

    private final AudioPlayer audioPlayer;
    private final RaidSpellCheckerConfig config;

    public void play(String fileName, int volume)
    {
        try
        {
            // Ensure folder exists so users know where to put files
            ensureCustomDirExists();

            float gainDb = 20f * (float) Math.log10(config.soundVolume() / 100f);

            // 1) Try custom file on disk first
            Path customPath = CUSTOM_DIR.resolve(fileName);
            if (Files.exists(customPath) && Files.isRegularFile(customPath))
            {
                audioPlayer.play(customPath.toFile(), gainDb);
                return;
            }

            // 2) Fallback to bundled resource in src/main/resources/sounds/
            audioPlayer.play(this.getClass(), "/sounds/" + fileName, gainDb);
        }
        catch (Exception e)
        {
            log.error("Failed to play sound '{}'", fileName, e);
        }
    }

    private static void ensureCustomDirExists() throws IOException
    {
        if (!Files.exists(CUSTOM_DIR))
        {
            Files.createDirectories(CUSTOM_DIR);
            // Optional: drop a tiny readme so people immediately understand the folder
            Files.writeString(
                    CUSTOM_DIR.resolve("README.txt"),
                    "Put custom .wav files here for Raid Spell Checker.\n" +
                            "Then set the exact filename in the plugin config (e.g. MySound.wav).\n"
            );
        }
    }
}