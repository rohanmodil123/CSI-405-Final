package me.derpee.creelande;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

// Initialize a new Item class.

public class Daddys_Credit_Card extends Item {
    // Accept registryName (creelande:registry_name)
    // Accept maxStackSize (Default: 1)
    // Accept group (Example: ItemGroup.CREATIVE)
    public Daddys_Credit_Card(String registryName, int MaxStackSize, ItemGroup group) {
        super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }

    @Override

    //uses any world, only works when a player uses it, and either hand works.
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        // Do not run on server. (Singleplayer only)
        if (world.isRemote) return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));





        // Get playerInventory.
        PlayerInventory inventory = player.inventory;

        inventory.addItemStackToInventory(new ItemStack(Creelande.CASH, 20));



        /* doesn't matter what it returns, this return type is needed for the @override right-click functionality.
           I blame forge for it being so dumb */
        return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}
