package com.github.dsincennes.drofirstbot;
import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BestBuy {

	DiscordApi api;
	
	public BestBuy (DiscordApi api) {
		this.api =  api;
	}
	
	public void bbScrape() throws IOException {
		
		// All 30x series vid cards
		String bbUrl = "https://www.bestbuy.ca/en-ca/category/graphics-cards/20397?path=category%253AComputers%2B%2526%2BTablets%253Bcategory%253APC%2BComponents%253Bcategory%253AGraphics%2BCards%253Bcustom0graphicscardmodel%253AGeForce%2BRTX%2B3080";
		TextChannel vid = api.getTextChannelById(840698037227618305L).get();
		vid.sendMessage("Fetching Bestbuy.ca 30xx series in stock: ");
	
		Document doc = Jsoup.connect(bbUrl).userAgent("Mozilla").timeout(30000).get();
		Elements body = doc.select(".productListingContainer_3JUbO");

		for(Element c : body.select(".col-xs-12_198le")) {

			Elements title = c.select(".productItemName_3IZ3c");
			Elements price = c.select(".price_FHDfG");
			Elements stock = c.select(".container_1DAvI");
			Elements stock2 = c.select(".marketplaceName_3FG8H");
			String image = c.select("img.productItemImage_1en8J img").attr("src");
			String link = c.select("a").attr("href");
	
    	if(!stock.text().contains("Available")) {	
			EmbedBuilder bestbuyCards = new EmbedBuilder()
					.setTitle("\n" + title.text())
//					.setDescription(link)
					.setAuthor("GPU Availability", null,
					"https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/d0/d0d0c091f302e4ffa492c73792b6656d6b9af4ed_full.jpg")
	                .addField("Link to Purchase", "https://www.bestbuy.ca" + link)
					.addInlineField("Price", price.text())
					.addInlineField("In Stock", "Buy Now")
					.setColor(Color.BLUE)
					.setFooter("Donald Sincennes",
							"https://cdn.akamai.steamstatic.com/steamcommunity/public/images/avatars/d0/d0d0c091f302e4ffa492c73792b6656d6b9af4ed_full.jpg")
					.setImage(image.toString())
					.setThumbnail(new File("C:\\Users\\D\\Downloads\\pngegg.png"));

			vid.sendMessage(bestbuyCards);
    		}

		}
	    // making sure it works.
	    vid.sendMessage("End of Stock.");
	}
}
