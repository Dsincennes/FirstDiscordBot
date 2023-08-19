package com.github.dsincennes.drofirstbot;

import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.awt.Color;
import java.io.File;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class NewEgg {

	DiscordApi api;

	public NewEgg(DiscordApi api) {
		this.api = api;
	}

	public void neScrape() throws IOException {

		TextChannel vid = api.getTextChannelById(840698037227618305L).get();
		vid.sendMessage("Fetching NewEgg 30xx series in stock");
		
		int pageCount = 1;
		//incase multiple pages
//		while (pageCount <= 7) {
		// All 30x series vid cards
		String neUrl = "https://www.newegg.ca/p/pl?N=100007708%20601357248&page="+ Integer.toString(pageCount);
		Document doc = Jsoup.connect(neUrl).timeout(6000).get();
		Elements body = doc.select(".item-cells-wrap");

		

			for (Element c : body.select(".item-cell")) {
	
				// variables from scrape
				Elements title = c.select(".item-title");
				Elements stock = c.select(".item-button-area");
				Elements price = c.select(".price-current");
				String img = c.select(".item-img img").attr("src");
				String link = c.select("a").attr("href");
	
				if (stock.text().equals("Add to cart")) {
					EmbedBuilder newEggCards = new EmbedBuilder()
							.setTitle("\n" + title.text())
//							.setDescription(link)
							.setAuthor("GPU Availability", null,
							"https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/d0/d0d0c091f302e4ffa492c73792b6656d6b9af4ed_full.jpg")
			                .addField("Link to Purchase", link)
							.addInlineField("Price", price.text())
							.addInlineField("In Stock", "Buy Now")
							.setColor(Color.BLUE)
							.setFooter("Donald Sincennes",
									"https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/d0/d0d0c091f302e4ffa492c73792b6656d6b9af4ed_full.jpg")
							.setImage(img)
							.setThumbnail(new File("C:\\Users\\D\\Downloads\\pngegg.png"));
	
					vid.sendMessage(newEggCards);
				}
			}
//			pageCount++;
//	    }

//			vid.sendMessage("End of Stock.");
	}
}

//				System.out.println("\n" + title.text());
//				System.out.println(img);
//				System.out.println(price.text());
//				System.out.println(link);
//				System.out.println("in stock");
//				System.out.println();