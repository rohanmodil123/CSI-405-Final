package me.derpee.creelande;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

// Initialize new ArmorItem (Different from Item)
public class Superior_Armor extends ArmorItem {

    // Main constructor, returns a different type of ArmorItem based on EquipmentSlotType.
    // Material determines the textures and enum to look for.
    public Superior_Armor(IArmorMaterial material, EquipmentSlotType equipmentSlot, String registryName, ItemGroup group) {
        // Set registryName and make ArmorItem.
        super(material, equipmentSlot, new Item.Properties().group(group));
        this.setRegistryName("creelande", registryName);
    }
}
