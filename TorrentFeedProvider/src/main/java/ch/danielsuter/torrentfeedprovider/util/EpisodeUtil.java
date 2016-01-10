package ch.danielsuter.torrentfeedprovider.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ch.danielsuter.torrentfeedprovider.domain.Item;
import ch.danielsuter.torrentfeedprovider.domain.Series;

public class EpisodeUtil {

	public static Series parseEpisode(Item item) {
		Pattern pattern = Pattern.compile("(.*)[Ss](\\d+)[Ee](\\d+).*");
		Matcher matcher = pattern.matcher(item.getTitle());
		if (matcher.matches()) {
			String season = matcher.group(2);
			String episode = matcher.group(3);
			return new Series(season, episode);
		}
		return null;
	}

}
