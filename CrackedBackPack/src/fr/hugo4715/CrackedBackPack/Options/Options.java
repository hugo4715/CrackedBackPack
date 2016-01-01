package fr.hugo4715.CrackedBackPack.Options;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import lombok.Getter;

public class Options {
	
	@Getter private static ItemStack item;
	
	
	public static void load(){
		item = new ItemStack(Material.FIREWORK_CHARGE);
		item.setAmount(1);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(ChatColor.GREEN + "BackPack");
		item.setItemMeta(m);
		
		
	}
}
