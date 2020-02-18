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

public class Phone extends Item {
    // Make new item through constructor
    public Phone(String registryName, int MaxStackSize, ItemGroup group) {
        super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }

    // Override default right click behavior
    // Default believed to be blank i think, return held item in ActionResult
    // test comment
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (world.isRemote) return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));

        PlayerInventory inventory = player.inventory;
        Item[] randFood = {Items.COOKED_SALMON, Items.BAKED_POTATO, Items.BREAD, Items.CARROT, Items.COOKED_COD, Items.MUSHROOM_STEW, Items.BEETROOT_SOUP, Items.PUMPKIN_PIE};

        int range = randFood.length;
        Item chosenItem = randFood[(int)(Math.random() * range)];

        boolean freeSlot = false;
        boolean hasIronIngot = false;
        int ironSlot = 0;

        for (int i=0; i<36; i++) {
            if (inventory.getStackInSlot(i).equals(ItemStack.EMPTY)) {
                freeSlot = true;
                break;
            } else if (inventory.getStackInSlot(i).getItem() == new ItemStack(chosenItem).getItem()) {
                if (inventory.getStackInSlot(i).getCount() < 64) {
                    freeSlot = true;
                    break;
                }
            }
        }

        for (int i=0; i<36; i++) {
            if (inventory.getStackInSlot(i).getItem() == new ItemStack(Items.IRON_INGOT).getItem()) {
                hasIronIngot = true;
                ironSlot = i;
                break;
            }
        }

        if (freeSlot) {
            if (hasIronIngot) {
                player.sendMessage(new StringTextComponent(ChatFormatting.RED + "Phone" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " Enjoy!"));
                inventory.addItemStackToInventory(new ItemStack(chosenItem, 1));
                inventory.getStackInSlot(ironSlot).setCount(inventory.getStackInSlot(ironSlot).getCount() - 1);
            } else {
                player.sendMessage(new StringTextComponent(ChatFormatting.RED + "Phone" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " Not enough funds!"));
            }
        } else {
            player.sendMessage(new StringTextComponent(ChatFormatting.RED + "Phone" + ChatFormatting.GRAY + ">" + ChatFormatting.WHITE + " Not enough inventory space!"));
        }

        return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}
