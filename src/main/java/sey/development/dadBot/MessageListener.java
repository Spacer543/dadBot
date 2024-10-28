package sey.development.dadBot;

import io.papermc.paper.event.player.AsyncChatEvent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MessageListener implements Listener {

    private final DadBot dadBot;

    public MessageListener(DadBot dadBot) {
        this.dadBot = dadBot;
    }

    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        int secondsToWait = 1; // Default value
        if (this.dadBot.getConfig().contains("seconds_to_wait")) {
            secondsToWait = this.dadBot.getConfig().getInt("seconds_to_wait");
        }

        String message = ((net.kyori.adventure.text.TextComponent) event.message()).content().toLowerCase();

        if (message.startsWith("i'm") || message.startsWith("i am") || message.startsWith("im")) {
            String name;

            if (message.startsWith("i'm")) {
                name = message.split("i'm", 2)[1].trim();
            } else if (message.startsWith("im")) {
                name = message.split("im", 2)[1].trim();
            } else {
                name = message.split("i am", 2)[1].trim();
            }

            if (!name.matches("^[a-zA-Z0-9_ '\"-]+$")) {
                Bukkit.getScheduler().runTaskLater(dadBot, () -> event.getPlayer().sendMessage("Failed to make dad joke. Invalid name: Only alphanumeric names, underscores, dashes, single or double quotes, and spaces are allowed."), 0);
                return;
            }


            if (!name.isEmpty()) {
                Bukkit.getScheduler().runTaskLater(dadBot, () -> {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendMessage("Hi " + name + "! I'm dad.");
                    }
                }, secondsToWait * 20L); // Convert seconds to ticks (20 ticks = 1 second)
            }
        }
    }
}
