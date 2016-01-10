package ch.danielsuter.torrentfeedprovider.domain;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class Configuration {
	
	private List<TorrentFeed> feeds;
	
	@XmlElement(name="feed")
	public List<TorrentFeed> getFeeds() {
		if(feeds == null) {
			feeds = new LinkedList<>();
		}
		return feeds;
	}
	
	public void setFeeds(List<TorrentFeed> feeds) {
		this.feeds = feeds;
	}
	
	public void addFeed(TorrentFeed feed) {
		getFeeds().add(feed);
	}
}
