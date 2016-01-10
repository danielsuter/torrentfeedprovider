package ch.danielsuter.torrentfeedprovider.transport;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.danielsuter.torrentfeedprovider.application.TorrentFeedProvider;
import ch.danielsuter.torrentfeedprovider.domain.Rss;

@RestController
@RequestMapping("customFeed")
public class RssFeedController {
	
	@RequestMapping(method=RequestMethod.GET, produces=MediaType.TEXT_XML_VALUE)
	public Rss getFeed() {
		TorrentFeedProvider provider = new TorrentFeedProvider();
		return provider.getTorrents();
	}
}
