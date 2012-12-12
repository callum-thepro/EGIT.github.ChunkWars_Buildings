package com.hotmail.jeroenverstraelen.Economy;


import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.hotmail.jeroenverstraelen.ChunkWars.MainBAge1;

public class EconomyMain {

	static File f1 = new File(getDataFolder(),"ChunkWars");
	static File NewDataFolder = new File(f1,"Players");
	
	static List<String> Buildings = MainBAge1.Buildings;
	static List<Integer> BuildingPrices = MainBAge1.BuildingPrices;
	
	public static int BuildingGetPrice(String building){
	int pos = Buildings.indexOf(building);	
	int price = BuildingPrices.get(pos);
	return price;	
	}
	
	public static boolean MoneyCheck (Integer amount, Player player){
		
    String playername = player.getPlayerListName();
		
	YamlConfiguration PlayerConfig = YamlConfiguration.loadConfiguration(new File(NewDataFolder,playername+".yml"));	
	if(PlayerConfig.get("Money")!= null || PlayerConfig.get("Money") == "0"){
	int money = (Integer) PlayerConfig.get("Money");
	
	if(money < amount){
	player.sendMessage(ChatColor.YELLOW + "You do not have enough money.");
	return false;
	}
    if(money >= amount){
    player.sendMessage(ChatColor.YELLOW + "You have enough money. [DEBUG]");
    return true;	
    }
	}
    player.sendMessage(ChatColor.YELLOW + "You don't have enough money.");
    return false;
	}

	
	private static String getDataFolder() {
		return null;
	}


	public static void Pay(Integer amount,String playername){
		YamlConfiguration PlayerConfig = YamlConfiguration.loadConfiguration(new File(NewDataFolder,playername+".yml"));
		int money = (Integer) PlayerConfig.get("Money")-amount;
		PlayerConfig.set("Money",money);
		
		try {
			PlayerConfig.save(new File(NewDataFolder,playername+".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Recieve(Integer amount,String playername){
		YamlConfiguration PlayerConfig = YamlConfiguration.loadConfiguration(new File(NewDataFolder,playername+".yml"));
		int money = (Integer) PlayerConfig.get("Money")+amount;
		PlayerConfig.set("Money",money);
		
		try {
			PlayerConfig.save(new File(NewDataFolder,playername+".yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static int ShowMoney(String playername){
		YamlConfiguration PlayerConfig = YamlConfiguration.loadConfiguration(new File(NewDataFolder,playername+".yml"));
		int money = (Integer) PlayerConfig.get("Money");
    return money;	
	}

	public static void SetBuildingInHand(String building,Player player){
	    int ItemId = player.getItemInHand().getTypeId();
		if(ItemId == 0){		
		player.setItemInHand(Namer.setName(new ItemStack(Material.CHEST), building));
		}
		else{
		player.sendMessage(ChatColor.YELLOW + "Your hand needs to be empty.");
		}	
	}
}