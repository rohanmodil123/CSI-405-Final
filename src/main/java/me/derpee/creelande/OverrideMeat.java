package me.derpee.creelande;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class OverrideMeat {
    @SubscribeEvent
    public void startEatMeat(LivingEntityUseItemEvent.Start event) {
        // Get the item being used, and the entity using it.
        // Also create a blank Food variable for later use.
        Item item = event.getItem().getItem();
        ItemStack itemStack = event.getItem();
        LivingEntity entity = event.getEntityLiving();
        Food food;

        // If Entity is not a player, cancel.
        if ((!(entity instanceof PlayerEntity)) || (!(item.isFood()))) {
            return;
        }

        // If Food is not meat, cancel. Otherwise, run.
        food = item.getFood();
        if (!(food.isMeat())) {
            return;
        }

        // Cancel the eat event.
        event.setCanceled(true);

        // Remove the meat from the player's inventory.
        itemStack.setCount(0);

        // Only run on server world. (Affecting movement speed)
        if (entity.world.isRemote) return;

        // Get the Player, and play a sound to them.
        // Also apply potion effects to the player.
        // Also send a chat message to the player.
        PlayerEntity player = (PlayerEntity)entity;
        player.playSound(new SoundEvent(new ResourceLocation("block.anvil.hit")), 1.0f, 1.0f);
        player.sendMessage(new StringTextComponent(ChatFormatting.GOLD + "Creelande" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " How dare you!"));

        EffectInstance blindness = new EffectInstance(Effects.BLINDNESS, 60, 3, false, false);
        EffectInstance slowness = new EffectInstance(Effects.SLOWNESS, 60, 255, false, false);

        if (player.isPotionApplicable(blindness) && player.isPotionApplicable(slowness)) {
            player.addPotionEffect(blindness);
            player.addPotionEffect(slowness);
        }
    }
}
