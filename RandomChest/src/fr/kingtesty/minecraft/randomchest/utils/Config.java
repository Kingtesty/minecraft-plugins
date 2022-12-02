package fr.kingtesty.forrun.randomchest.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import fr.kingtesty.forrun.randomchest.Randomchest;


public class Config
{
  private final String NAME;
  private FileConfiguration config;
  private File file;
  
  public Config(String NAME)
  {
    this.NAME = NAME;
    
    setupConfig();
  }
  
  private void setupConfig()
  {
    this.file = new File(Randomchest.getPlugin().getDataFolder(), this.NAME + ".yml");
    if (!this.file.exists())
    {
      try
      {
        this.file.createNewFile();
        Randomchest.getPlugin().saveResource(this.NAME + ".yml", true);
      }
      catch (IOException e)
      {
        e.printStackTrace();
        Bukkit.getLogger().severe("There was an error while creating " + this.NAME + ".yml please send this log to the developer");
        return;
      }
      this.config = YamlConfiguration.loadConfiguration(this.file);
      
//      Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "configsetup " + (this.config == null));
    }
  }
  
  public FileConfiguration getConfig()
  {
    if (this.config == null)
    {
      this.config = YamlConfiguration.loadConfiguration(this.file);
      return YamlConfiguration.loadConfiguration(this.file);
    }
    return this.config;
  }
  
  public void reload()
  {
    this.config = YamlConfiguration.loadConfiguration(this.file);
  }
  
  public void erase()
  {
	  this.file.delete();
	  setupConfig();
	  this.config = YamlConfiguration.loadConfiguration(this.file);
  }
  
  public void save()
  {
    if (this.file == null) {
      return;
    }
    try
    {
      this.config.save(this.file);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
