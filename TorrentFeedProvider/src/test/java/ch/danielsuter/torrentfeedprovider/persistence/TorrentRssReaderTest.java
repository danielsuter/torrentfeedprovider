package ch.danielsuter.torrentfeedprovider.persistence;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import org.junit.Test;

import ch.danielsuter.torrentfeedprovider.domain.Channel;
import ch.danielsuter.torrentfeedprovider.domain.Item;
import ch.danielsuter.torrentfeedprovider.domain.Rss;

public class TorrentRssReaderTest {
	
	private TorrentRssReader reader = new TorrentRssReader();
	
	@Test
	public void test() throws Exception{
		try(FileInputStream inputStream = new FileInputStream("src/test/resources/exampleFeed.xml")) {
			Rss feed = reader.getFeed(inputStream);
			
			Channel channel = feed.getChannel();
			assertNotNull(channel);
			assertEquals(2, channel.getItems().size());
			
			Item item = channel.getItems().get(0);
			assertEquals(23, item.getSeeds().intValue());
			assertEquals(25, item.getPeers().intValue());
			assertEquals(259733218, item.getContentLength().intValue());
		}		
		
	}

}
