package me.derpee.creelande;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

// Create new event handler class for Key Input and Ticking.
public class KeyInput {

    // Create new variables for player and world.
    ClientPlayerEntity player;
    ClientWorld world;

    @SubscribeEvent
    public void onKeyPress(InputEvent.KeyInputEvent event) {

        // Get the Sound Handler from the running Minecraft instance.
        // Get the location for the sound file.
        SoundHandler handler = Minecraft.getInstance().getSoundHandler();
        ResourceLocation soundLocation = new ResourceLocation("creelande", "records.roll");

        // Stop running if the player isn't on the client, or if the world isn't on the client.
        if (!(player instanceof ClientPlayerEntity)) { return; }
        if (!(world instanceof ClientWorld)) { return; }

        // Run another test, just to ensure the sound is played on the client.
        if (world.isRemote == false) { return; }

        // If the key is being released, stop.
        if (event.getAction() == 1) { return; }

        // If the player pressed R
        if (event.getKey() == 82) {
            // If the sound isn't playing, continue.
            if (Creelande.RickPlaying == false) {
                Iterable<ItemStack> armor = player.getArmorInventoryList();

                // If the player has AyrePods equipped, set the playing variable to true, and play the song.
                for (ItemStack stack : armor) {
                    if (stack.getItem().equals(Creelande.AYRE_PODS)) {
                        Creelande.RickPlaying = true;
                        world.playSound(player, player.getPosition(), ModSoundsHandler.MUSIC_DISC_ROLL, SoundCategory.RECORDS, 1.0f, 1.0f);
                    }
                }
            } else { // If the song IS playing, set the playing variable to false and stop the song. (NOTE: Does not pause.)
                Creelande.RickPlaying = false;
                handler.stop(soundLocation, SoundCategory.RECORDS);
            }
        }
    }

    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        // Get the client player and world every tick.
        if (event.player.world.isRemote == true) {
            player = (ClientPlayerEntity)event.player;
            world = (ClientWorld)player.world;
        }
    }
}
