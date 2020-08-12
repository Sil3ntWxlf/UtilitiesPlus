package io.github.sil3ntwxlf;

import com.google.gson.*;
import io.github.sil3ntwxlf.commands.CommandAliases;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;


public final class UtilitiesPlus extends JavaPlugin {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static HashMap<String, Object> map = new HashMap<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        int pluginId = 8424; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        //HashMap onLoad
        map.put("onLoad", "Load");
        System.out.println("UtilitiesPlus has been loaded!" + map.get("onLoad"));

        // Optional: Add custom charts
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "servers"));

        //Command Aliases Ahoy
        getCommand("gamemode").setExecutor(new CommandAliases());

        //Configuration Generation
        saveResource("config.json", false);
        try {
            final JsonObject root = (JsonObject) new JsonParser().parse(new FileReader(new File(getDataFolder(),"config.json")));
            String test = root.getAsJsonPrimitive("Test").getAsString();
        } catch (IOException e) {
            getLogger().log(Level.SEVERE, "Error loading config.json file", e);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        //HashMap onUnload
        map.put("onUnload", "Unload");
        System.out.println("UtilitiesPlus has been disabled!" + map.get("onUnload"));
    }
}
