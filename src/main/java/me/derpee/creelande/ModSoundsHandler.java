package me.derpee.creelande;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

// New class to streamline the process of registering a new sound.
public class ModSoundsHandler {
    public static SoundEvent MUSIC_DISC_ROLL;

    public static void registerSounds() {
        MUSIC_DISC_ROLL = register("records.roll");
    }

    private static SoundEvent register(String name) {
        ResourceLocation soundLocation = new ResourceLocation("creelande", name);
        SoundEvent soundEvent = new SoundEvent(soundLocation);
        soundEvent.setRegistryName(name);
        ForgeRegistries.SOUND_EVENTS.register(soundEvent);
        return soundEvent;
    }
}
