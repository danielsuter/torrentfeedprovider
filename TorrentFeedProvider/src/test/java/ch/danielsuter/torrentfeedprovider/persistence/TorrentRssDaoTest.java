package ch.danielsuter.torrentfeedprovider.persistence;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import ch.danielsuter.torrentfeedprovider.domain.Item;
import ch.danielsuter.torrentfeedprovider.domain.TorrentFeed;

public class TorrentRssDaoTest {

	private TorrentRssDao dao = new TorrentRssDao();
	
	@Test
	public void test() {
		Item item = new Item();
		item.setAuthor("Some author");
		item.setVerified(1);
		dao.addItem(new TorrentFeed(), item);
		
		assertTrue(new File(TorrentRssDao.FEED_FILENAME).exists());
	}

}
