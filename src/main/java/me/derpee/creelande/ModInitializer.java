package me.derpee.creelande;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;

public class ModInitializer extends Item {
    // Accept registryName (creelande:registry_name)
    // Accept maxStackSize (Default: 1)
    // Accept group (Example: ItemGroup.CREATIVE)

    public ModInitializer(String registryName, int MaxStackSize, ItemGroup group) {
        super(new Item.Properties().maxStackSize(MaxStackSize).group(group));
        this.setRegistryName("creelande", registryName);
    }

    @Override
    //uses any world, only works when a player uses it, and either hand works.
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {

        // Do not run on server. (Singleplayer only)
        if (world.isRemote) return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));

        player.sendMessage(new StringTextComponent(ChatFormatting.RED + "Mod starting-up. Please wait for Done Message. May take a while"));

        OakStreet oak = new OakStreet();
        oak.GenOakStreet(world);

        player.sendMessage(new StringTextComponent(ChatFormatting.BLUE + "Done! You may now play as normal."));
        /* doesn't matter what it returns, this return type is needed for the @override right-click functionality.
        I blame forge for it being so dumb */
        return new ActionResult(ActionResultType.SUCCESS, player.getHeldItem(hand));
    }
}
