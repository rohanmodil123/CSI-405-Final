package me.derpee.creelande;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeEventFactory;

import java.util.List;
import java.util.stream.Stream;

// Initialize a new Item class.
public class Phone extends Item {
    // Accept registryName (creelande:registry_name)
    // Accept maxStackSize (Default: 1)
    // Accept group (Example: ItemGroup.CREATIVE)
    public Phone(String registryName, int MaxStackSize, ItemGroup group) {
        super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }

    // Override default right click behavior.
    // Click behavior MUST return an ActionResult with an item that
    // the player is holding, or any other item to replace.

    // Takes in world, playerEntity, hand
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        // Do not run on server. (Singleplayer only)
        if (world.isRemote) return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));

        // Get playerInventory.
        PlayerInventory inventory = player.inventory;

        // Create array of possible food items to obtain from Phone.
        Item[] randFood = {Items.COOKED_SALMON, Items.BAKED_POTATO, Items.BREAD, Items.CARROT, Items.COOKED_COD, Items.MUSHROOM_STEW, Items.BEETROOT_SOUP, Items.PUMPKIN_PIE};

        // Choose a random item from array.
        int range = randFood.length;
        Item chosenItem = randFood[(int)(Math.random() * range)];

        // Set freeSlot, and ironIngot variables.
        boolean freeSlot = false;
        boolean hasIronIngot = false;
        int ironSlot = 0;

        // Iterate through every possible Item inventory slot.
        for (int i=0; i<36; i++) {
            // Check if slot is empty (to give Item)
            if (inventory.getStackInSlot(i).equals(ItemStack.EMPTY)) {
                // Empty space open.
                freeSlot = true;
                break;
            } else if (inventory.getStackInSlot(i).getItem() == new ItemStack(chosenItem).getItem()) {
                // If not empty, but same Item, check to see if new Item can be added to stack.
                if (inventory.getStackInSlot(i).getCount() < 64) {
                    // Stackable space open.
                    freeSlot = true;
                    break;
                }
            }
        }

        // Iterate through every possible Item inventory slot.
        for (int i=0; i<36; i++) {
            // Check if slot has an Iron Ingot.
            if (inventory.getStackInSlot(i).getItem() == new ItemStack(Items.IRON_INGOT).getItem()) {
                // Has Iron Ingot, and set the inventory item index to that Item.
                hasIronIngot = true;
                ironSlot = i;
                break;
            }
        }

        // If enough space to give Item.
        if (freeSlot) {
            // If player has sufficient funds.
            if (hasIronIngot) {
                // Give Player Item, and remove Iron Ingot, show success message in chat.
                player.sendMessage(new StringTextComponent(ChatFormatting.RED + "DoreDush" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " Enjoy!"));
                inventory.addItemStackToInventory(new ItemStack(chosenItem, 1));
                inventory.getStackInSlot(ironSlot).setCount(inventory.getStackInSlot(ironSlot).getCount() - 1);
            } else {
                // Show error message in chat.
                player.sendMessage(new StringTextComponent(ChatFormatting.RED + "DoreDush" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " Not enough funds!"));
            }
        } else {
            // Show error message in chat.
            player.sendMessage(new StringTextComponent(ChatFormatting.RED + "DoreDush" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " Not enough inventory space!"));
        }

        // Return held Item.
        return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}
