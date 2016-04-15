package fr.hugo4715.CrackedBackPack.Listener;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.hugo4715.CrackedBackPack.Main;
import fr.hugo4715.CrackedBackPack.Options.Options;
import fr.hugo4715.CrackedBackPack.Utils.ItemSerialization;

public class PlayerInteract implements Listener {
	@EventHandler
	public void onInteract(PlayerInteractEvent e){

		if(e.getItem() == null)return;
		ItemStack i = e.getItem();
		if(!(i.getType().equals(Options.getItem().getType())))return;
		if(i.getItemMeta() == null)return;
		if(i.getItemMeta().getDisplayName() == null)return;
		if(!i.getItemMeta().getDisplayName().equals(Options.getItem().getItemMeta().getDisplayName()))return;
		if(i.getAmount() > 1)return;
		
		if(Main.getInstance().cooldown.containsKey(e.getPlayer()) && Main.getInstance().cooldown.get(e.getPlayer()) > System.currentTimeMillis()){
			e.getPlayer().sendMessage(ChatColor.RED + "Please stop spamming your backpack.");
			e.setCancelled(true);
			return;
		}
		Main.getInstance().cooldown.put(e.getPlayer(), System.currentTimeMillis() + 1000);
		ItemMeta m = i.getItemMeta();

		if(m.getLore() == null || m.getLore().isEmpty()){
			//create the blank inventory
			List<String> lore = new ArrayList<String>();
			lore.add("id:" + UUID.randomUUID());
			m.setLore(lore);
			e.getItem().setItemMeta(m);
			e.getPlayer().openInventory(Bukkit.createInventory(null, Options.getSlots(), "BackPack"));
		}
		else{
			//show the inventory
			UUID id = UUID.fromString(m.getLore().get(0).substring(3));
			try {
				String serialized = new String(Files.readAllBytes(Paths.get(new File(Main.getInstance().getDataFolder().getAbsolutePath(),id.toString() + ".inventory").getAbsolutePath())), StandardCharsets.UTF_8);
				e.getPlayer().openInventory(ItemSerialization.fromBase64(serialized,"BackPack"));
			} catch (IOException e1) {
				e1.printStackTrace();
				e.getPlayer().sendMessage(ChatColor.RED + "Error while opening your backpack, please report it to an admin.");
			}
		}
		e.setCancelled(true);
	}
}
