package ch.danielsuter.torrentfeedprovider.domain;

public class TorrentFeed {
	private String url;
	private Series lastSeries;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public Series getLastSeries() {
		return lastSeries;
	}

	public void setLastSeries(Series lastSeries) {
		this.lastSeries = lastSeries;
	}
}	
