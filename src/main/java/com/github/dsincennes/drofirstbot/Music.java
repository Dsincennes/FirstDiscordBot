package com.github.dsincennes.drofirstbot;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;

public class Music {
	
	DiscordApi api;
	
	public Music (DiscordApi api) {
		this.api =  api;
	}
	
	public void music() {

		TextChannel music = api.getTextChannelById(831022883610296370L).get();
		
        /* 
         * SPOTIFY PLAYLISTS
         */
        
        api.addMessageCreateListener(event -> {
            if (event.getMessageContent().equalsIgnoreCase("!spot")) {
            	music.sendMessage("https://open.spotify.com/playlist/4rRFTGt2Jn5AJcLyYm4h9j");   
            } 
        });
	}
}
