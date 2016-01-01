package fr.hugo4715.CrackedBackPack.Listener;

import org.bukkit.Bukkit;

import fr.hugo4715.CrackedBackPack.Main;

public class EventManager {
	public static void registerEvents(){
		Bukkit.getPluginManager().registerEvents(new PlayerInteract(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new PlayerCloseInventory(), Main.getInstance());
		Bukkit.getPluginManager().registerEvents(new PlayerClickInventory(), Main.getInstance());
	}
}
