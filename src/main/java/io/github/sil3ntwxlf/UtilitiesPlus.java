package io.github.sil3ntwxlf;

import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;

public final class UtilitiesPlus extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        int pluginId = 8424; // <-- Replace with the id of your plugin!
        Metrics metrics = new Metrics(this, pluginId);

        // Optional: Add custom charts
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "servers"));

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
