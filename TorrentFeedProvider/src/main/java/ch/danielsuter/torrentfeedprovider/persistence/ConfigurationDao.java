package ch.danielsuter.torrentfeedprovider.persistence;

import java.io.File;

import javax.xml.bind.JAXB;

import ch.danielsuter.torrentfeedprovider.domain.Configuration;

public class ConfigurationDao {
	
	private static final String CONFIGURATION_FILENAME = "configuration.xml";
	
	public Configuration readConfiguration() {
		Configuration configuration = JAXB.unmarshal(new File(CONFIGURATION_FILENAME), Configuration.class);
		return configuration;
	}
	
	public void write(Configuration configuration) {
		JAXB.marshal(configuration, new File(CONFIGURATION_FILENAME));
	}
}
