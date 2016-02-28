package ch.danielsuter.torrentfeedprovider.domain;

public class TorrentFeed {
	private String url;
	private Series lastSeries;
	private Long minContentLength;
	
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

	public Long getMinContentLength() {
		return minContentLength;
	}

	public void setMinContentLength(Long minContentLength) {
		this.minContentLength = minContentLength;
	}
}	
