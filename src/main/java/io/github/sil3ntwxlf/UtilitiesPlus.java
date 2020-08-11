package io.github.sil3ntwxlf;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.github.sil3ntwxlf.commands.GamemodeAliasesCommand;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Map;


public final class UtilitiesPlus extends JavaPlugin {
    public /*static*/ final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public static HashMap<String, Object> map = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        int pluginId = 8424; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        //HashMap onLoad
        map.put("onLoad", "Load");
        System.out.println("UtilitiesPlus has been loaded!"+ map.get("onLoad"));

        // Optional: Add custom charts
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "servers"));

        //Command Aliases Ahoy
        getCommand("gamemode").setExecutor(new GamemodeAliasesCommand());

        //Configuration Generation
        try {
            File defaultConfigFile = new File(getClass().getClassLoader().getResource("config.json").getFile());
            File configFile = new File(getDataFolder(), "config.json");
            if ((configFile.getParentFile() != null && configFile.getParentFile().mkdirs()) || configFile.createNewFile()) {
                configFile.createNewFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(configFile));
                bw.write(new String(Files.readAllBytes(Paths.get(defaultConfigFile.toURI()))));
                bw.close();
            }
            String rawContent = new String(Files.readAllBytes(Paths.get(configFile.toURI())));
            JsonObject json = new JsonParser().parse(rawContent).getAsJsonObject();
            System.out.println(rawContent);
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //HashMap onUnload
        map.put("onUnload", "Unload");
        System.out.println("UtilitiesPlus has been disabled!"+ map.get("onUnload"));
    }
}
