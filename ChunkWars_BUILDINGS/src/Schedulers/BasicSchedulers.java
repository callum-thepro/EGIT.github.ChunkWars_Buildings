package Schedulers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import com.hotmail.jeroenverstraelen.ChunkWars.MainBAge1;


public class BasicSchedulers{
	private MainBAge1 plugin;
	 
	//REGISTERING PLUGIN
	public BasicSchedulers(MainBAge1 plugin){
		this.plugin = plugin;
	    }
	
	//LISTS AND FOLDERS
	public static List<Integer> BA1TaskID = MainBAge1.BA1TaskID;
	public static List<String> BA1TaskLoc = MainBAge1.BA1TaskLoc;
	
	File f1 = new File(getDataFolder(),"ChunkWars");
	File DataFolder = new File(f1,"Data");
	
	
	//[1.0]NODESTOPSCHEDULER
	public void NodeStopScheduler(Integer number,String loc){
	int i = BA1TaskLoc.indexOf(number+","+loc);
	int taskid = BA1TaskID.get(i);
	Bukkit.getServer().getScheduler().cancelTask(taskid);
	BA1TaskLoc.remove(i);
	BA1TaskID.remove(i);
	YamlConfiguration TaskConfig = YamlConfiguration.loadConfiguration(new File(DataFolder,"Tasks.yml"));
	TaskConfig.set("BA1TaskID",BA1TaskID);
	TaskConfig.set("BA1TaskLoc",BA1TaskLoc);
	try {
		TaskConfig.save(new File(DataFolder,"Tasks.yml"));
	} catch (IOException e) {
		e.printStackTrace();
	}
	}


	private String getDataFolder() {
		return null;
	}
	
}
