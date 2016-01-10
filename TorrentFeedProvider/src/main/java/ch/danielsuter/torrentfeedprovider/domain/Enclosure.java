package ch.danielsuter.torrentfeedprovider.domain;

import javax.xml.bind.annotation.XmlAttribute;

public class Enclosure {
	private String url;
	private Long length;
	private String type;
	
	@XmlAttribute
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@XmlAttribute
	public Long getLength() {
		return length;
	}
	public void setLength(Long length) {
		this.length = length;
	}
	@XmlAttribute
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "Enclosure [url=" + url + ", length=" + length + ", type=" + type + "]";
	}
	
	
}
