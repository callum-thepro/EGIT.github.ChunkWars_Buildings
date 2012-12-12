package com.hotmail.jeroenverstraelen.Chunks;

import java.io.File;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.hotmail.jeroenverstraelen.ChunkWars.MainBAge1;
import com.hotmail.jeroenverstraelen.Economy.EconomyMain;

public class CWB1CommandExecutor implements CommandExecutor{

	private MainBAge1 plugin;
	
	File f1 = new File(this.getDataFolder(),"ChunkWars");
	File DataFolder = new File(f1,"Data");
	   
	public static List<String> Buildings = MainBAge1.Buildings;
	public static List<Integer> BuildingPrices = MainBAge1.BuildingPrices;
	 
	
	
	public CWB1CommandExecutor(MainBAge1 plugin) {
		this.plugin = plugin;
	}
 
	
	

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player)sender;
		String playername = player.getPlayerListName();
		
		
		//[1.0]BUILDING RELATED COMMANDS
		//BUYBUILDING
		if (cmd.getName().equalsIgnoreCase("BuyBuilding")){
			
		if(args.length == 1){
		if(Buildings.contains(args[0])){
		if(EconomyMain.MoneyCheck(EconomyMain.BuildingGetPrice(args[0]), player) == true){	
		EconomyMain.SetBuildingInHand(args[0],player);	
		EconomyMain.Pay(EconomyMain.BuildingGetPrice(args[0]), playername);
		return true;	
		}
		}
		}
		
		return false;	
		}
		else{
		
			
			
			
		//BUILDINGPRICE
		if (cmd.getName().equalsIgnoreCase("BuildingPrice")){	
		
		if(args.length ==1){
		if(Buildings.contains(args[0])){
		int pos = Buildings.indexOf(args[0]);
		int price = BuildingPrices.get(pos);
		player.sendMessage(ChatColor.YELLOW + "This building costs "+price);
		}		
		}
		return false;
		}
		else{
			
			
			
			
			
		//BUILDINGLIST
		if (cmd.getName().equalsIgnoreCase("BuildingList")){
		for(int i=0;i<=Buildings.size()-1;i++){
		player.sendMessage(ChatColor.YELLOW + ""+i+". "+Buildings.get(i));	
		}
		return true;
		}
			
			
			
			
		}}
		return false;
}
	
	//Methods
	private String getDataFolder() {
		return null;
	}

}
