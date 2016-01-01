package fr.hugo4715.CrackedBackPack;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import fr.hugo4715.CrackedBackPack.Listener.EventManager;
import fr.hugo4715.CrackedBackPack.Options.Options;
import lombok.Getter;

public class Main extends JavaPlugin{
	@Getter private static Main instance;
	
	public HashMap<Player, Long> cooldown = new HashMap<Player,Long>();
	
	@Override
	public void onEnable(){
		instance = this;
		this.getDataFolder().mkdir();
		Options.load();
		this.registerRecipe();
		EventManager.registerEvents();
	}
	
	public void registerRecipe(){
		ShapedRecipe r = new ShapedRecipe(Options.getItem());
		r.shape("***","*c*","***");
		r.setIngredient('*', Material.LEATHER);
		r.setIngredient('c', Material.ENDER_CHEST);
		Bukkit.getServer().addRecipe(r);
	}
}
