package com.github.dsincennes.drofirstbot;

import java.io.IOException;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.ServerChannel;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.invite.Invite;
import org.javacord.api.entity.server.invite.InviteBuilder;

public class Main {

	public static void main(String[] args) throws IOException {

		BotRun run = new BotRun(); // Creating Object for Bot run class
		DiscordApi api = run.runBot(); // Creating DiscordApi object that'll get passed into every class to run the bot.

		/*
		 * Announcing self
		 */
//		TextChannel general = api.getTextChannelById(234475270554255371L).get();
//		general.sendMessage("I am here!");

//		Embed emb = new Embed(api); // Embed maker currently has player profiles, TODO trying to include video cards
//		emb.embed();

//		Music music = new Music(api); // TODO work on music bot
//		music.music();

		/*
		 * All Website sources that are being data scraped.
		 */
//		CanadaComputers cc = new CanadaComputers(api);
		NewEgg ne = new NewEgg(api);
//		MemoryExpress me = new MemoryExpress(api);
		BestBuy bb = new BestBuy(api);

//		Crypto crypto = new Crypto(api); // TODO work on adding my current crypto stocks.

		api.addMessageCreateListener(event -> {
			if (event.getMessageContent().equalsIgnoreCase("!gpu")) {
				event.getChannel().sendMessage("Check #videocards for results");
				try {
//					cc.ccScrape();
					ne.neScrape();
//					me.meScrape();
					bb.bbScrape();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
}

/*
 * 
 * HYDROS DISCORD TEXT CHANNELS
 * -----------------------------------------------------------------------------
 * ------- TextChannel general =
 * api.getTextChannelById(234475270554255371L).get(); TextChannel music =
 * api.getTextChannelById(831022883610296370L).get(); TextChannel eft =
 * api.getTextChannelById(793254304776454204L).get(); TextChannel clips =
 * api.getTextChannelById(759998127423225858L).get(); TextChannel vid =
 * api.getTextChannelById(840698037227618305L).get();
 * 
 * GREETINGS
 * -----------------------------------------------------------------------------
 * ------- api.getCachedUserById(172485592783912962L).ifPresent(user ->
 * general.sendMessage("All Hail King CrowKakaww"));
 * 
 * api.getCachedUserById(121365666098839555L).ifPresent(user ->
 * general.sendMessage("Dro test"));
 * 
 * BOT INVITE
 * -----------------------------------------------------------------------------
 * -------
 * System.out.println("You can invite the bot by using the following url: " +
 * api.createBotInvite());
 * 
 */