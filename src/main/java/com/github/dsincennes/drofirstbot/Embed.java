package com.github.dsincennes.drofirstbot;
import java.awt.Color;
import java.io.File;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class Embed {
	
	DiscordApi api;
	
	public Embed (DiscordApi api) {
		this.api =  api;
	}

    // Create the embed
	public void embed() {
		
        EmbedBuilder donald = new EmbedBuilder()
        		
                .setTitle("Donald")
                .setDescription("Goat")
                .setAuthor("Donald Sincennes", "http://google.com/", "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/d0/d0d0c091f302e4ffa492c73792b6656d6b9af4ed_full.jpg")
                .addField("Hydro", "Greatest Valorant Player Ever")
                .addInlineField("Location", "Bind")
                .addInlineField("Job", "Crows Intern")
                .setColor(Color.BLUE)
                .setFooter("Footer", "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/d0/d0d0c091f302e4ffa492c73792b6656d6b9af4ed_full.jpg")
                .setImage(new File("C:/Users/D/Pictures/don.jpg"))
                .setThumbnail(new File("C:/Users/D/Pictures/test.png"));

            api.addMessageCreateListener(event -> {
                if (event.getMessageContent().equalsIgnoreCase("!hydro")) {
                    event.getChannel().sendMessage(donald);
                    
                }
            });
            
            EmbedBuilder brandon = new EmbedBuilder()
            		
                    .setTitle("Brandon Yip")
                    .setDescription("Best coder alive")
                    .setAuthor("Crowkakawww", "https://tracker.gg/valorant/profile/riot/crowKAKAWWW%23NA1/overview", "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/650330/658d7a42b02f59a94b0ea2a97ee46b4323b66c78.gif")
                    .addField("Crow", "Second greatest Valorant Player Ever")
                    .addInlineField("Location", "China")
                    .addInlineField("Job", "Engineer")
                    .setColor(Color.BLUE)
                    .setFooter("Crow", "https://cdn.akamai.steamstatic.com/steamcommunity/public/images/items/650330/658d7a42b02f59a94b0ea2a97ee46b4323b66c78.gif")
                    .setImage(new File("C:/Users/D/Pictures/maisie.jpg"))
                    .setThumbnail(new File("C:/Users/D/Pictures/crow.gif"));

                api.addMessageCreateListener(event -> {
                    if (event.getMessageContent().equalsIgnoreCase("!crow")) {
             
                        event.getChannel().sendMessage(brandon);
                        
                    }
                });
	}
}
