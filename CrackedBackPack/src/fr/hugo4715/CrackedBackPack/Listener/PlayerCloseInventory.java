package fr.hugo4715.CrackedBackPack.Listener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;

import fr.hugo4715.CrackedBackPack.Main;
import fr.hugo4715.CrackedBackPack.Utils.ItemSerialization;

public class PlayerCloseInventory implements Listener {
	@EventHandler
	public void onCloseInventory(InventoryCloseEvent e){

		ItemStack i = e.getPlayer().getItemInHand();
		if(!i.hasItemMeta())return;
		if(i.getItemMeta().getLore() == null || i.getItemMeta().getLore().isEmpty())return;
		if(!e.getInventory().getName().equals("BackPack"))return;
		
		Main.getInstance().cooldown.put(Bukkit.getPlayer(e.getPlayer().getName()), System.currentTimeMillis() + 1000);
		UUID id = UUID.fromString(i.getItemMeta().getLore().get(0).substring(3));
		
		String serialized = ItemSerialization.toBase64(e.getInventory());
		File file = new File(Main.getInstance().getDataFolder().getAbsolutePath(), id.toString() + ".inventory");
		try {
			PrintWriter w = new PrintWriter(file);
			w.write(serialized);
			w.flush();
			w.close();
			w = null;
			
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
	}
}
