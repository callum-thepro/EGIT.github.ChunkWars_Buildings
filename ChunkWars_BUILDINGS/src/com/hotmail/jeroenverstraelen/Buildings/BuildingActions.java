package com.hotmail.jeroenverstraelen.Buildings;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;

import Schedulers.BasicSchedulers;

import com.hotmail.jeroenverstraelen.ChunkWars.MainBAge1;
import com.hotmail.jeroenverstraelen.Chunks.ChunksMain;

public class BuildingActions{
	
	private MainBAge1 plugin;
	 
	
	//REGISTERING PLUGIN
	public BuildingActions(MainBAge1 plugin){
	        this.plugin = plugin;
	    }
		
	
	
	//LISTS AND FOLDERS
	public static List<String> FireLoc = MainBAge1.FireLoc;
	public static List<Location> FireLocDefined = MainBAge1.FireLocDefined;
	public static List<Integer> BA1TaskID = MainBAge1.BA1TaskID;
	public static List<String> BA1TaskLoc = MainBAge1.BA1TaskLoc;
	public static List<String> BA1StartupTasks = MainBAge1.BA1StartupTasks;
	
   File f1 = new File(getDataFolder(),"ChunkWars");
   File DataFolder = new File(f1,"Data");
   File PlayerFolder = new File(f1,"Players");
   
   
   
   

   /*
    *    ACTIONS
    * 
    */
   
   
   
   public void Actions(Integer BA,Integer time,final String loc,Player player){
 	   String[] locsplt = loc.split(",");
 	   Location L = new Location(Bukkit.getWorld(locsplt[0]),Float.parseFloat(locsplt[1]),Float.parseFloat(locsplt[2]),Float.parseFloat(locsplt[3]));
	   
	   
	//[1.0]FIRE
	if(BA==1){
		
   	int taskid = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
   		
 	   String[] locsplt = loc.split(",");
 	   Location L = new Location(Bukkit.getWorld(locsplt[0]),Float.parseFloat(locsplt[1]),Float.parseFloat(locsplt[2]),Float.parseFloat(locsplt[3]));
       Chunk chunk = L.getChunk();
       World world = L.getWorld();
       
	   private MainBAge1 plugin;
       
	   
   	      @Override
   	      public void run(){
   	      if(world.getBlockAt(L).getType() == Material.FIRE){
          Entity[] EntityL = chunk.getEntities();
   	      for(int i =0;i<=(EntityL.length)-1;i++){
   	      if(EntityL[i] instanceof Skeleton || EntityL[i] instanceof Zombie || EntityL[i] instanceof Spider){
   	      double Y = EntityL[i].getLocation().getY();
   	      if(Y>=L.getY() && Y<=L.getY()+5){
   	      ((LivingEntity) EntityL[i]).setHealth(0);
   	      }
   	      }
   	      }
   	      }
   	      //SHUTDOWN
   	      else{
		BasicSchedulers BS = new BasicSchedulers(this.plugin);
   		BS.NodeStopScheduler(1,loc);
   	    }
   	    }    }, time*20L, time*20L);

    BA1TaskID.add(taskid);
    BA1TaskLoc.add(1+","+loc);
   	
   	return;
	      }
	
	
	 
	
	
	
    //[2.0]
   if(BA==2){
	}
   
   
	
    }






private String getDataFolder() {
	return null;
}
   
   
   
}
	

