package ch.danielsuter.torrentfeedprovider.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.zip.GZIPInputStream;

import javax.xml.bind.JAXB;

import ch.danielsuter.torrentfeedprovider.domain.Rss;

public class TorrentRssReader {

	public Rss getFeed(URL url) throws IOException {
		InputStream inputStream = url.openStream();
		GZIPInputStream gzis = new GZIPInputStream(inputStream);
		return getFeed(gzis);
	}
	
	public Rss getFeed(InputStream is) throws IOException {
		return JAXB.unmarshal(is, Rss.class);
	}
}
