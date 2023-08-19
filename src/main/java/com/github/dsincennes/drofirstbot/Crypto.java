package com.github.dsincennes.drofirstbot;
import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Crypto {

	DiscordApi api;
	
	public Crypto (DiscordApi api) {
		this.api =  api;
	}

	public void cryptoScrape() throws IOException {
		
		String neUrl = "https://coinmarketcap.com/tokens/";
		TextChannel cry = api.getTextChannelById(843354014166679553L).get();
		Document doc = Jsoup.connect(neUrl).timeout(6000).get();
		Elements body = doc.select(".tableWrapper___3utdq");
		
		System.out.println("Fetching Crypto Stock info");
		cry.sendMessage("Fetching Crypto Stock info");

		for(Element c : body.select(".cmc-table tr")) {
			
			// variables from scrape
			Elements title = c.select("sc-1eb5slv-0");
			Elements code = c.select(".sc-1teo54s-2");
			Elements day = c.select(".sc-1v2ivon-0");
			Elements week = c.select(".sc-1v2ivon-0");
			String img = c.select("coin-logo").attr("src");
			Elements price = c.select(".price___3rj7O");
			String link = c.select("a").attr("href");
						
        	if(title.text().contains("Dogecoin")) {
        		
        		// code for console.
				System.out.print("\n" + title.text());
				System.out.println(code.text());
				System.out.println(img);
				System.out.println(day.text());
				System.out.println(week.text());
				System.out.println(link);
				System.out.println();
				
				// code for discord
            	cry.sendMessage("\n" + title.text());
            	cry.sendMessage(img);
            	cry.sendMessage(price.text());
            	cry.sendMessage(day.text());
            	cry.sendMessage(week.text());
            	cry.sendMessage(link);
            	cry.sendMessage("\n ");
        	}
		}
		// print statement for console to let me know it scanned properly.
		cry.sendMessage("End of Stocks.");
		System.out.println("End of crypto Stock!");
		System.out.println();
	}
}

