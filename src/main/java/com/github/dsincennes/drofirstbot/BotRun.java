package com.github.dsincennes.drofirstbot;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

public class BotRun {
	
	public DiscordApi runBot() {
		
        String fileName = "token.txt";
        String content = "";
        try {
            content = readFileContent(fileName);
            System.out.println("File content:\n" + content);
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        
        DiscordApi api = new DiscordApiBuilder().setToken(content).login().join();
        return api;
	}
	
	
    public static String readFileContent(String fileName) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line).append("\n");
            }
        }

        return contentBuilder.toString();
    }
}
