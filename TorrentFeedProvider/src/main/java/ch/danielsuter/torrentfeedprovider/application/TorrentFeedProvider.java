package ch.danielsuter.torrentfeedprovider.application;

import java.io.IOException;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.xml.bind.JAXB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.danielsuter.torrentfeedprovider.domain.Configuration;
import ch.danielsuter.torrentfeedprovider.domain.Item;
import ch.danielsuter.torrentfeedprovider.domain.Rss;
import ch.danielsuter.torrentfeedprovider.domain.Series;
import ch.danielsuter.torrentfeedprovider.domain.TorrentFeed;
import ch.danielsuter.torrentfeedprovider.persistence.ConfigurationDao;
import ch.danielsuter.torrentfeedprovider.persistence.TorrentRssDao;
import ch.danielsuter.torrentfeedprovider.persistence.TorrentRssReader;
import ch.danielsuter.torrentfeedprovider.util.EpisodeUtil;

public class TorrentFeedProvider {
	private final static Logger logger = LoggerFactory.getLogger(TorrentFeedProvider.class);

	private ConfigurationDao configurationDao = new ConfigurationDao();
	private TorrentRssReader torrentRssReader = new TorrentRssReader();
	private TorrentRssDao torrentRssDao = new TorrentRssDao();

	public static void main(String[] args) {
		Configuration config = new Configuration();
		TorrentFeed feed = new TorrentFeed();
//		feed.setUrl(
//				"https://kat.cr/usearch/the%20big%20bang%20theory%201080p%20category%3Atv%20lang_id%3A2%20verified%3A1/?rss=1");
		feed.setUrl("file:C:/Users/suter/workspace/TorrentFeedProvider/src/test/resources/fullDump.xml");
		feed.setLastSeries(new Series("09", "01"));
		config.addFeed(feed);


		Rss torrents = new TorrentFeedProvider().getTorrents();

		JAXB.marshal(torrents, System.out);
	}

	public Rss getTorrents() {
		Configuration configuration = configurationDao.readConfiguration();
		List<TorrentFeed> feeds = configuration.getFeeds();
		for (TorrentFeed torrentFeed : feeds) {
			try {
				Rss feed = torrentRssReader.getFeed(new URL(torrentFeed.getUrl()));
				List<Item> feedItems = feed.getChannel().getItems();
				logger.debug("All items: ");
				logItems(feedItems);
				
				List<Item> itemsWithHigherEpisode = filterOlder(feedItems, torrentFeed);
				logger.debug("Higher episode:");
				logItems(itemsWithHigherEpisode);
				
				Optional<Item> newItem = filterDuplicates(itemsWithHigherEpisode, torrentFeed);
				
				if (newItem.isPresent()) {
					torrentRssDao.addItem(torrentFeed, newItem.get());
					Series series = EpisodeUtil.parseEpisode(newItem.get());
					torrentFeed.setLastSeries(series);
				}
			} catch (IOException e) {
				logger.error("Failed to refresh " + torrentFeed.getUrl(), e);
			}
		}

		configurationDao.write(configuration);

		return torrentRssDao.getOrCreate();
	}

	private void logItems(List<Item> items) {
		for (Item item : items) {
			logger.debug(" {}", item.getTitle());
		}
	}

	private Optional<Item> filterDuplicates(List<Item> items, TorrentFeed torrentFeed) {
		return items.stream().max(new Comparator<Item>() {
			@Override
			public int compare(Item o1, Item o2) {
				return Integer.compare(o1.getSeeds(), o2.getSeeds());
			}
		});
	}

	private List<Item> filterOlder(List<Item> items, TorrentFeed torrentFeed) {
		Series lastSeries = torrentFeed.getLastSeries();
		
		return items.stream().filter(item -> {
			Series series = EpisodeUtil.parseEpisode(item);
			if(series == null) {
				logger.debug("Could not parse series {}", item.getTitle());
				return false;
			}
			return series.isNewerThan(lastSeries);
		}).collect(Collectors.toList());
	}
}
