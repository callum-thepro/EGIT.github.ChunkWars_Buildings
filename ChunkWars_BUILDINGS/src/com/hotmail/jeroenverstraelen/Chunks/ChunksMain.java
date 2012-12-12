package com.hotmail.jeroenverstraelen.Chunks;

import java.io.File;

import org.bukkit.Chunk;
import org.bukkit.configuration.file.YamlConfiguration;

public class ChunksMain {
    static File f1 = new File(getDataFolder(),"ChunkWars");
    static File DataFolder = new File(f1,"Data");
    
	static YamlConfiguration ChunkConfig = YamlConfiguration.loadConfiguration(new File(DataFolder,"Chunks.yml"));
    
	private static String getDataFolder() {
		return null;
	}
	
	
		public static boolean isChunkBuilt(Chunk chunk){
			String world = chunk.getWorld().getName();
			int Chunkx = chunk.getX();
			int Chunkz = chunk.getZ();
			if(ChunkConfig.getInt(world+","+Chunkx+","+Chunkz) == 1){
			return true;	
			}
		return false;	
		}
		
}