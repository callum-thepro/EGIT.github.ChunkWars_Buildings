package com.hotmail.jeroenverstraelen.Buildings;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class BuildingsMain {
	
	public static boolean ResourceCheckBuilding(Player player,Location loc,Material mat1,Material mat2,Material mat3,int Ramount1,int Ramount2,int Ramount3,int xlength,int ylength,int zlength){
		World world = loc.getWorld();
		int Imat1 = 0;
		int Imat2 = 0;
		int Imat3 = 0;
		
		int y_start2 = (int) Math.round(loc.getY())-ylength;
        int x_start2 = (int) Math.round(loc.getX())-xlength;
        int z_start2 = (int) Math.round(loc.getZ())-zlength;
		int x_length2 = x_start2+xlength;
		int z_length2 = z_start2+zlength;
        int y_length2 = y_start2+ylength;
        
		for(int x_operate = x_start2; x_operate <= x_length2; x_operate++){ 
			for(int y_operate = y_start2; y_operate <= y_length2; y_operate++){
				for(int z_operate = z_start2; z_operate <= z_length2; z_operate++){
					
	 Block blockToChange = world.getBlockAt(x_operate,y_operate,z_operate);
	 Material material = blockToChange.getType();
	 if(material == mat1){
	 Imat1++;	 
	 }
	 if(material == mat2){	 
	 Imat2++;	 
	 }
	 if(material == mat3){
	 Imat3++;	 
	 }
				}
			}
		}
	if(Imat1 >= Ramount1 && Imat2 >= Ramount2 && Imat3 >= Ramount3){
	return true;
	}
	else{
		
	player.sendMessage(ChatColor.RED + "The building's requirements aren't mett.");	
	player.sendMessage(ChatColor.RED + ""+mat1+": "+Imat1+"/"+Ramount1);
	player.sendMessage(ChatColor.RED + ""+mat2+": "+Imat2+"/"+Ramount2);
	if(mat3 != Material.AIR){
	player.sendMessage(ChatColor.RED + ""+mat3+": "+Imat3+"/"+Ramount3);
	}
	return false;	
	}
	}

}
