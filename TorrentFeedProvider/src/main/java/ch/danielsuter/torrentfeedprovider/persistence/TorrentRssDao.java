package ch.danielsuter.torrentfeedprovider.persistence;

import java.io.File;

import javax.xml.bind.JAXB;

import ch.danielsuter.torrentfeedprovider.domain.Channel;
import ch.danielsuter.torrentfeedprovider.domain.Item;
import ch.danielsuter.torrentfeedprovider.domain.Rss;
import ch.danielsuter.torrentfeedprovider.domain.TorrentFeed;

public class TorrentRssDao {

	static final String FEED_FILENAME = "feed.xml";

	public void addItem(TorrentFeed torrentFeed, Item item) {
		Rss rss = getOrCreate();
		rss.getChannel().getItems().add(item);
		writeFeed(rss);
	}

	public Rss getOrCreate() {
		File feedFile = new File(FEED_FILENAME);
		if (!feedFile.exists()) {
			createInitialFeed();
		}

		Rss feed = JAXB.unmarshal(feedFile, Rss.class);
		return feed;
	}

	private void createInitialFeed() {
		Rss rss = new Rss();
		Channel channel = new Channel();
		channel.setTitle("Custom feed");
		rss.setChannel(channel);
		writeFeed(rss);
	}

	private void writeFeed(Rss rss) {
		JAXB.marshal(rss, new File(FEED_FILENAME));
	}
}
