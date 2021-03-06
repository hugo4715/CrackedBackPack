package fr.hugo4715.CrackedBackPack;

import java.io.FileNotFoundException;
import java.io.IOException;
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
		try {
			Options.load();
		} catch (FileNotFoundException e) {
			getLogger().severe("Erreur lors du chargement de la config:");
			getLogger().severe(e.getMessage());
			getLogger().severe("Erreur complete:");
			e.printStackTrace();
			
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		} catch (IllegalArgumentException e) {
			getLogger().severe("Erreur lors du chargement de la config:");
			getLogger().severe(e.getMessage());
			getLogger().severe("Erreur complete:");
			e.printStackTrace();
			
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		} catch (IOException e) {
			getLogger().severe("Erreur lors du chargement de la config:");
			getLogger().severe(e.getMessage());
			getLogger().severe("Erreur complete:");
			e.printStackTrace();
			
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
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
