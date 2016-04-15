package fr.hugo4715.CrackedBackPack.Options;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.hugo4715.CrackedBackPack.Main;
import lombok.Getter;

public class Options {
	
	@Getter private static ItemStack item;
	@Getter private static int slots;
	
	public static void load() throws FileNotFoundException, IOException, IllegalArgumentException{
		
		File file = new File(Main.getInstance().getDataFolder(), "config.yml");
		if(!file.exists()){
			IOUtils.copyLarge(Main.getInstance().getResource("config.yml"), new FileOutputStream(file));
		}
		YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		slots = config.getInt("slots");
		
		if(slots % 9 != 0){
			throw new IllegalArgumentException("Le nombre de slots du sac doit etre divisible par 9");
		}
		if(slots < 9){
			throw new IllegalArgumentException("Le nombre de slots du sac doit etre au minimum 9");
		}
		
		item = new ItemStack(Material.valueOf(config.getString("material")));
		item.setAmount(1);
		ItemMeta m = item.getItemMeta();
		m.setDisplayName(ChatColor.GREEN + "BackPack");
		item.setItemMeta(m);
		
		
	}
}
