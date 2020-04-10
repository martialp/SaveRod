package me.qintinator.saverod;

import me.qintinator.saverod.contracts.IConfigPropertyService;
import me.qintinator.saverod.contracts.ISaverodService;
import me.qintinator.saverod.eventlisteners.OnEntityPickupItem;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.qintinator.saverod.commands.RodCommand;
import me.qintinator.saverod.commands.SaveRodCommand;
import me.qintinator.saverod.eventlisteners.OnPlayerDeath;
import me.qintinator.saverod.statics.Bootstrapper;
import me.qintinator.saverod.statics.ConfigPropertyMapper;

public class Main extends JavaPlugin{
	
	
	@Override
	public void onEnable() {
			
		saveDefaultConfig();
		ISaverodService saveRodService = Bootstrapper.getSaverodService();

		// running the bootstrapper
		Bootstrapper.run();

		// running the property mapper
		ConfigPropertyMapper.run(this);

		// loading the commands
		Bukkit.getPluginCommand("rod").setExecutor(new RodCommand(saveRodService));
		Bukkit.getPluginCommand("saverod").setExecutor(new SaveRodCommand(this));
		
		// loading all events
		IConfigPropertyService configPropertyService = Bootstrapper.getConfigPropertyService();
		Bukkit.getPluginManager().registerEvents(new OnPlayerDeath(saveRodService, configPropertyService), this);
		Bukkit.getPluginManager().registerEvents(new OnEntityPickupItem(saveRodService), this);
	}
}
