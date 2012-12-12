package com.hotmail.jeroenverstraelen.ChunkWars;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;


import Schedulers.BasicSchedulers;

import com.hotmail.jeroenverstraelen.Buildings.BuildingActions;
import com.hotmail.jeroenverstraelen.Buildings.BuildingStartup;
import com.hotmail.jeroenverstraelen.Buildings.BuildingsMain;
import com.hotmail.jeroenverstraelen.Chunks.CWB1CommandExecutor;
import com.hotmail.jeroenverstraelen.Economy.EconomyMain;
import com.hotmail.jeroenverstraelen.Economy.Namer;
import com.hotmail.jeroenverstraelen.Listeners.BlockListener;
import com.hotmail.jeroenverstraelen.Listeners.MobListener;

public class MainBAge1 extends JavaPlugin implements Listener{
	
	   File f1 = new File(this.getDataFolder(),"ChunkWars");
	   File DataFolder = new File(f1,"Data");
    
    
	public static List<String> Buildings = new ArrayList<String>();
	public static List<Integer> BuildingPrices = new ArrayList<Integer>();
	public static List<Integer> StartupNumber = new ArrayList<Integer>();
	public static List<Integer> ActionNumber = new ArrayList<Integer>();
	public static List<Integer> ActionTimeScheduler = new ArrayList<Integer>();
	public static List<String> BuildingRequirements = new ArrayList<String>();
	
	public static List<Entity> Entities = new ArrayList<Entity>();
	
	public static List<Integer> BA1TaskID = new ArrayList<Integer>();
	public static List<String> BA1TaskLoc = new ArrayList<String>();
	public static List<String> BA1StartupTasks = new ArrayList<String>();
	public static List<String> BA1StartupTasksEnable = new ArrayList<String>();
	
	public static List<String> FireLoc = new ArrayList<String>();
	public static List<Location> FireLocDefined = new ArrayList<Location>();
	
	
	
	@SuppressWarnings("unchecked")
	public void onEnable(){
			
    //[1.2] REGISTERING EVENTS	
	getServer().getPluginManager().registerEvents(new BlockListener(), this);	
	getServer().getPluginManager().registerEvents(new MobListener(), this);	
	getServer().getPluginManager().registerEvents(this, this);
	new BuildingActions(this);
	new BasicSchedulers(this);
	new BuildingStartup(this);
	
	//[1.3] LOADING LISTS
	YamlConfiguration BuildingConfig = YamlConfiguration.loadConfiguration(new File(f1,"Buildings.yml"));
	YamlConfiguration TaskConfig = YamlConfiguration.loadConfiguration(new File(DataFolder,"Tasks.yml"));
	
	if(BuildingConfig.get("Started") == null){
	GenDefBuildingConfig();
	}

    Buildings.addAll((Collection<? extends String>) BuildingConfig.get("Buildings"));
    BuildingPrices.addAll((Collection<? extends Integer>) BuildingConfig.get("BuildingPrices"));
	BuildingRequirements.addAll((Collection<? extends String>) BuildingConfig.get("BuildingRequirements"));
	StartupNumber.addAll((Collection<? extends Integer>) BuildingConfig.get("StartupNumber"));
	ActionNumber.addAll((Collection<? extends Integer>) BuildingConfig.get("ActionNumber"));
	ActionTimeScheduler.addAll((Collection<? extends Integer>) BuildingConfig.get("ActionTimeScheduler"));
	BA1StartupTasksEnable.addAll((Collection<? extends String>) TaskConfig.get("StartUpTasks"));
	
	for(int i =0;i<=FireLoc.size()-1;i++){
    String[] locsplt = FireLoc.get(i).split(",");
	Location newloc = new Location(Bukkit.getWorld(locsplt[0]),Float.parseFloat(locsplt[1]),Float.parseFloat(locsplt[2]),Float.parseFloat(locsplt[3]));
	FireLocDefined.add(newloc);
	}
	
	//[1.4] SCHEDULERS
	BuildingActions BA = new BuildingActions(this);
	for(int i =0;i<=BA1StartupTasksEnable.size()-1;i++){
	String[] tasksplt = BA1StartupTasksEnable.get(i).split(";");
    int number = Integer.parseInt(tasksplt[0]);
    int time = Integer.parseInt(tasksplt[1]);
    if(tasksplt[3] != null){
    Player player = Bukkit.getPlayer(tasksplt[3]);
	BA.Actions(number,time,tasksplt[2],player);
    }
    else{
	BA.Actions(number,time,tasksplt[2],null);
    }
	}
	BA1StartupTasksEnable.clear();
	
	//[1.5] COMMANDS
    getCommand("BuyBuilding").setExecutor(new CWB1CommandExecutor(this));
    getCommand("BuildingPrice").setExecutor(new CWB1CommandExecutor(this));
    getCommand("BuildingList").setExecutor(new CWB1CommandExecutor(this));
	}
	
	
	
    //**___________________________________________________________________________**\\
	
	
	
	
    //2. BUILDINGEVENTS
	
	
	@EventHandler
	public void BlockPlaceEvent(org.bukkit.event.block.BlockPlaceEvent event){
	    Material mat = event.getBlock().getType();
		//CHEST
		if(mat == Material.CHEST){
		List<String> Buildings1 = MainBAge1.Buildings;
		ItemStack it = event.getItemInHand();	
		String name = Namer.getName(it);
		Location loc = event.getBlock().getLocation();
		String worldname = loc.getWorld().getName();
	    
		if(!name.equalsIgnoreCase("Chest")){
			
		if(Buildings1.contains(name)){
		int pos = Buildings1.indexOf(name);
		int SN = StartupNumber.get(pos);
		int AN = ActionNumber.get(pos);
		int time = ActionTimeScheduler.get(pos);
		String[] Breqsplt = BuildingRequirements.get(pos).split(",");
		Boolean Breq = Boolean.parseBoolean(Breqsplt[0]);
		
		String locstr = (worldname+","+loc.getX()+","+loc.getY()+","+loc.getZ());
		
		if(Breq == false){
		BuildingStartup BS = new BuildingStartup(this);
		BuildingActions BA = new BuildingActions(this);
		BS.StartupB(SN,loc,event.getPlayer());	
		BA.Actions(AN,time,locstr,event.getPlayer());
		}
		else{
		if(BuildingsMain.ResourceCheckBuilding(event.getPlayer(), loc,Material.getMaterial(Breqsplt[1]),Material.getMaterial(Breqsplt[2]), Material.getMaterial(Breqsplt[3]), Integer.parseInt(Breqsplt[4]),Integer.parseInt(Breqsplt[5]), Integer.parseInt(Breqsplt[6]), Integer.parseInt(Breqsplt[7]), Integer.parseInt(Breqsplt[8]),Integer.parseInt(Breqsplt[9])) == true){
		BuildingStartup BS = new BuildingStartup(this);
		BuildingActions BA = new BuildingActions(this);
		BS.StartupB(SN,loc,event.getPlayer());	
		BA.Actions(AN,time,locstr,event.getPlayer());
		}
		else{
		event.setCancelled(true);	
		}
		}
		
		}
		else{
		event.setCancelled(true);
		}
		}
	}
}
	
	
	
    //**___________________________________________________________________________**\\
	
	
	
	
    //3. UNCATEGORIZED METHODS (amount 1)
	
	public void GenDefBuildingConfig(){
		YamlConfiguration BuildingConfig = YamlConfiguration.loadConfiguration(new File(DataFolder,"Buildings.yml"));
		ActionTimeScheduler.add(10);
		BuildingRequirements.add("false"+","+"NOTHING"+","+"NOTHING"+","+"NOTHING"+","+"0"+","+"0"+","+"0"+","+"0"+","+"0"+","+"0");
		StartupNumber.add(1);
		ActionNumber.add(1);
		Buildings.add("Fire");
		BuildingPrices.add(100);
		
		BuildingConfig.set("Buildings",Buildings);
		BuildingConfig.set("BuildingPrices",BuildingPrices);
		BuildingConfig.set("ActionTimeScheduler",ActionTimeScheduler);
		BuildingConfig.set("StartupNumber",StartupNumber);
		BuildingConfig.set("ActionNumber",ActionNumber);
		BuildingConfig.set("BuildingRequirements",BuildingRequirements);
		BuildingConfig.set("Started",true);
		
		try {
			BuildingConfig.save(new File(f1,"Buildings.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}	
		
		ActionTimeScheduler.clear();
		BuildingRequirements.clear();
		StartupNumber.clear();
		ActionNumber.clear();
		Buildings.clear();
		BuildingPrices.clear();
	}
	
}