package com.github.dsincennes.drofirstbot;
import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MemoryExpress {
	
	DiscordApi api;
	
	public MemoryExpress (DiscordApi api) {
		this.api =  api;
	}
	
	public void meScrape() throws IOException {

		// All 30x series vid cards
		TextChannel vid = api.getTextChannelById(840698037227618305L).get();
		String neUrl = "https://www.memoryexpress.com/Category/VideoCards?FilterID=0307ddc4-d08e-8113-7f91-cc2f992e99a3&Sort=Manufacturer&PageSize=120";
		vid.sendMessage("Fetching MemoryExpress.com 30xx series in stock: ");
		System.out.println("Fetching MemoryExpress 30xx series in stock");
	
		Document doc = Jsoup.connect(neUrl).timeout(6000).get();
		Elements body = doc.select(".c-shca-container");

		for(Element c : body.select(".c-shca-icon-item")) {

			Elements title = c.select(".c-shca-icon-item__body-name");
			Elements stock = c.select(".c-shca-icon-item__body-inventory");
			Elements price = c.select(".c-shca-icon-item__summary-list");
			String img = c.select("a").attr("src"); // review
			String link = c.select("a").attr("href");

			if(!stock.text().equals("Out of Stock")) {
				vid.sendMessage("\n" + title.text());
                vid.sendMessage(link);
                vid.sendMessage(img);
                vid.sendMessage(price.text());
                vid.sendMessage("in stock");
                
				System.out.println("\n" + title.text());
				System.out.println(img);
				System.out.println(price.text());
				System.out.println("in stock");
				System.out.println();
			}				
		}
		vid.sendMessage("End of Stock.");
		System.out.println("End of MemoryExpress Stock!");
		System.out.println();
	}
}



