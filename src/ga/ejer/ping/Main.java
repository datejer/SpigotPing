package ga.ejer.ping;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        this.getCommand("ping").setExecutor(new CommandPing());
    }

    @Override
    public void onDisable() {

    }
}
