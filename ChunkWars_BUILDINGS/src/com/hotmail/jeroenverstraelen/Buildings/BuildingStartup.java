package com.hotmail.jeroenverstraelen.Buildings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.hotmail.jeroenverstraelen.ChunkWars.MainBAge1;
import com.hotmail.jeroenverstraelen.Chunks.ChunksMain;

public class BuildingStartup{
	
	private MainBAge1 plugin;

	//REGISTERING PLUGIN
	public BuildingStartup(MainBAge1 plugin){
	        this.plugin = plugin;
	    }
	
	
	public void StartupB(int number,Location loc,Player player){
	    File f1 = new File(this.getDataFolder(),"ChunkWars");
	    File DataFolder = new File(f1,"Data");
	    File PlayerFolder = new File(f1,"Players");
		
		//NUMBER 1
		if(number == 1){
			
			
		World world = loc.getWorld();
		final Block block1 = world.getBlockAt(loc);
		double Y = loc.getY()-1;
		
		int y_start = (int) Math.round(loc.getY()-1);
	    int x_start = (int) Math.round(loc.getX()-1);
	    int z_start = (int) Math.round(loc.getZ()-1);
		
		int x_length = x_start + 2;
		int z_length = z_start + 2;
	    int y_length = y_start;
		
		for(int x_operate = x_start; x_operate <= x_length; x_operate++){ 
			for(int y_operate = y_start; y_operate <= y_length; y_operate++){
				for(int z_operate = z_start; z_operate <= z_length; z_operate++){
	 
					Block blockToChange = world.getBlockAt(x_operate,y_operate,z_operate);
					blockToChange.setTypeId(1);
				}
			}
		}
		Location loc2 = new Location(world,loc.getX(),Y,loc.getZ());
		Block block2 = world.getBlockAt(loc2);
		block2.setType(Material.NETHERRACK);
		
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, new Runnable() {
		@Override
		public void run() {
	    block1.setType(Material.FIRE);
  	  	}
		}, 20L);
		
		
		List<String> Buildings = MainBAge1.Buildings;
		List<Integer> ActionTimeScheduler = MainBAge1.ActionTimeScheduler;
		List<String> BA1StartupTasks = MainBAge1.BA1StartupTasks;
		
		int pos = Buildings.indexOf("Fire");
		int time = ActionTimeScheduler.get(pos);
		
		String BA1StartupTask = (number+";"+time+";"+loc.getWorld().getName()+","+loc.getX()+","+loc.getY()+","+loc.getZ()+";"+"null");
		BA1StartupTasks.add(BA1StartupTask);
	    
	    
		YamlConfiguration TaskConfig = YamlConfiguration.loadConfiguration(new File(DataFolder,"Tasks.yml"));
		TaskConfig.set("StartUpTasks",BA1StartupTasks);
		
		
		try {
			TaskConfig.save(new File(DataFolder,"Tasks.yml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return;
}
	
		
		//NUMBER 2
	  if(number==2){
		   String playername = player.getPlayerListName();
		   YamlConfiguration PlayerConfig = YamlConfiguration.loadConfiguration(new File(PlayerFolder,playername+".yml"));
		   YamlConfiguration ChunkConfig = YamlConfiguration.loadConfiguration(new File(DataFolder,"Chunks.yml"));
		   Chunk chunk = loc.getChunk();
			   
			if(ChunksMain.isChunkBuilt(chunk)==false){
			int randompoints = PlayerConfig.getInt("RandomPoints");
			int newrandompoints = randompoints+=1;
			PlayerConfig.set("RandomPoints",newrandompoints);
			
			try {
				PlayerConfig.save(new File(PlayerFolder,playername+".yml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String locstr = (loc.getWorld().getName()+","+loc.getX()+","+loc.getY()+","+loc.getZ());
			ChunkConfig.set(locstr,1);
			
			try {
				ChunkConfig.save(new File(DataFolder,"Chunks.yml"));
			} catch (IOException e) {
				e.printStackTrace();
			}
			}
			
			return;
	  }
		
	}

	private String getDataFolder() {
		return null;
	}



}
