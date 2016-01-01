package fr.hugo4715.CrackedBackPack.Options;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;

public class Options {
	
	@Getter private static ItemStack item;
	@Getter private static ItemStack closeItem;
	
	
	public static void load(){
		item = new ItemStack(Material.FIREWORK_CHARGE);
		item.setAmount(1);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(ChatColor.GREEN + "Sac a dos");
		item.setItemMeta(m);
		
		
		closeItem = new ItemStack(Material.BARRIER);
		ItemMeta m2 = closeItem.getItemMeta();
		m2.setDisplayName("Close");
	}
}
