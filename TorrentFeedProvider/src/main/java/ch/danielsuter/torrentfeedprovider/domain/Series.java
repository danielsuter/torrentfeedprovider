
package ch.danielsuter.torrentfeedprovider.domain;

public class Series {
	private String season;
	private String episode;

	public Series() {
	}

	public Series(String season, String episode) {
		this.season = season;
		this.episode = episode;
	}

	public void setEpisode(String episode) {
		this.episode = episode;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getSeason() {
		return season;
	}

	public String getEpisode() {
		return episode;
	}

	public boolean isNewerThan(Series lastSeries) {
		int thisSeason = Integer.parseInt(season);
		int thisEpisode = Integer.parseInt(episode);
		int lastSeason = Integer.parseInt(lastSeries.getSeason());
		int lastEpisode = Integer.parseInt(lastSeries.getEpisode());

		if (thisSeason < lastSeason) {
			return false;
		}

		return thisEpisode > lastEpisode;
	}

}
