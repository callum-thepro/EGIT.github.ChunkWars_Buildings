package com.hotmail.jeroenverstraelen.Listeners;


import java.io.File;
import java.util.List;

import org.bukkit.event.Listener;

import com.hotmail.jeroenverstraelen.ChunkWars.MainBAge1;

public class BlockListener implements Listener{
	File f1 = new File(this.getDataFolder(),"ChunkWars");
	File DataFolder = new File(f1,"Data");
	public static List<String> FireLoc = MainBAge1.FireLoc;
	
	private String getDataFolder() {
		return null;
	}
}

