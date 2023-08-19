package com.github.dsincennes.drofirstbot;
import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CanadaComputers {
	
	DiscordApi api;
	
	public CanadaComputers (DiscordApi api) {
		this.api =  api;
	}
	
	public void ccScrape() throws IOException {

		TextChannel vid = api.getTextChannelById(840698037227618305L).get();
		vid.sendMessage("Fetching CanadaComputers.com 30xx series in stock: ");
		System.out.println("Fetching CanadaComputers.com 30xx series in stock: ");
		
	    int pageCount = 1;
	    while (pageCount <= 5) {
	    	
	    	// all 30x series link
			String ccUrl = "https://www.canadacomputers.com/index.php?sort=2a&ajax=false&cPath=43_557_559&sf=:3_3,3_4&mfr=&pr=" + Integer.toString(pageCount);
			
			// making sure i get all pages for "load more website"
			pageCount++; 
		
			Document doc = Jsoup.connect(ccUrl).timeout(6000).get();	
			Elements body = doc.select("#product-list");
	
			for(Element c : body.select(".col-xl-3")) {
				
				// Variables from scrape
				Elements title = c.select(".productTemplate_title");
				Elements stock = c.select(".btn");
				Elements price = c.select(".pq-hdr-product_price");
				String image = c.select(".pq-img-product_container img").attr("src");
				String link = c.select("a").attr("href");
        		if(stock.text().equals("Add to Cart")) {
        			
        			// discord commands
	                vid.sendMessage("\n" + title.text());
	                vid.sendMessage(link);
	                vid.sendMessage(image);
	                vid.sendMessage(price.text());
	                vid.sendMessage("in stock");
	                
	                // for console
					System.out.println("\n" + title.text());
					System.out.println(image);
					System.out.println(price.text());
					System.out.println("in stock");
					System.out.println();
        		}

			}
	    }
	    // making sure it works.
	    vid.sendMessage("End of Stock.");
		System.out.println("End of CC Stock!");
		System.out.println();
	}
}
