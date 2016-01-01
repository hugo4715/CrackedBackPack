package fr.hugo4715.CrackedBackPack.Listener;


import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.hugo4715.CrackedBackPack.Options.Options;

public class PlayerClickInventory implements Listener {
	@EventHandler
	public void onClickInv(InventoryClickEvent e){
		if(!e.getInventory().getTitle().equals("BackPack"))return;
		if(e.getCurrentItem() == null)return;
		if(!e.getCurrentItem().getType().equals(Options.getItem().getType()))return;
		if(!e.getCurrentItem().hasItemMeta())return;
		if(!e.getCurrentItem().getItemMeta().getDisplayName().equals(Options.getItem().getItemMeta().getDisplayName()))return;
		e.setCancelled(true);
	}
}
