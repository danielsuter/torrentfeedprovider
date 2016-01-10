package ch.danielsuter.torrentfeedprovider.domain;

public class Item {
	private String title;
	private String category;
	private String author;
	private String guid;

	private Integer seeds;
	private Integer peers;
	private Integer verified;

	private Enclosure enclosure;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	@Override
	public String toString() {
		return "Item [title=" + title + ", category=" + category + ", author=" + author + ", guid=" + guid
				+ ", enclosure=" + enclosure + "]";
	}

	public Enclosure getEnclosure() {
		return enclosure;
	}

	public void setEnclosure(Enclosure enclosure) {
		this.enclosure = enclosure;
	}

	public Integer getSeeds() {
		return seeds;
	}

	public void setSeeds(Integer seeds) {
		this.seeds = seeds;
	}

	public Integer getPeers() {
		return peers;
	}

	public void setPeers(Integer peers) {
		this.peers = peers;
	}

	public Integer getVerified() {
		return verified;
	}

	public void setVerified(Integer verified) {
		this.verified = verified;
	}

}
