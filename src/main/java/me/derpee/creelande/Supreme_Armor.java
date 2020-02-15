package me.derpee.creelande;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;

public class Supreme_Armor extends ArmorItem {
    public Supreme_Armor(IArmorMaterial material, EquipmentSlotType equipmentSlot, String registryName, ItemGroup group) {
        super(material, equipmentSlot, new Item.Properties().group(group));
        this.setRegistryName("creelande", registryName);
    }
}
