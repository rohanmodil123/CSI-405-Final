package me.derpee.creelande;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MinecraftGame;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

// Create new event handler class. (To be registered in Creelande.java)
public class OnHurt {
    // The following method will run when an entity is hurt.
    @SubscribeEvent
    public void interceptHurt(LivingHurtEvent event) {
        // If damaged entity is an animal.
        if (!(event.getEntity() instanceof AnimalEntity)) {
            return;
        }

        // If entity inflicting damage is a player.
        if (!(event.getSource().getTrueSource() instanceof PlayerEntity)) {
            return;
        }

        // Cancel the damage to the animal.
        event.setCanceled(true);

        // Run on client world. (Prevents "Ghosting")
        if (!event.getEntity().world.isRemote) {
            // Confirmed trueSource is a player.
            PlayerEntity player = (PlayerEntity)(event.getSource().getTrueSource());
            // Make new blindness effect.
            EffectInstance blindness = new EffectInstance(Effects.BLINDNESS, 60, 3, false, false);
            EffectInstance slowness = new EffectInstance(Effects.SLOWNESS, 60, 255, false, false);

            // If player can take effect.
            if (player.isPotionApplicable(blindness) && player.isPotionApplicable(slowness)) {
                // Apply effect and send a chat message.
                player.addPotionEffect(blindness);
                player.addPotionEffect(slowness);
                player.sendMessage(new StringTextComponent(ChatFormatting.GOLD + "Creelande" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " How dare you!"));
            } else {
                // Send a chat message.
                player.sendMessage(new StringTextComponent(ChatFormatting.GOLD + "Creelande" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " How dare you!"));
            }
        }
    }
}

