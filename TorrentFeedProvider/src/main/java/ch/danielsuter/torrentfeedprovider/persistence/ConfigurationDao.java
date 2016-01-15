package ch.danielsuter.torrentfeedprovider.persistence;

import java.io.File;

import javax.xml.bind.JAXB;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.danielsuter.torrentfeedprovider.domain.Configuration;

public class ConfigurationDao {

	private final static Logger logger = LoggerFactory.getLogger(ConfigurationDao.class);
	private static final String CONFIGURATION_FILENAME = "configuration.xml";

	private File getLocation() {
		String configPath = System.getProperty("configPath");
		if (configPath == null) {
			File file = new File(CONFIGURATION_FILENAME);
			logger.warn("Property configPath is not defined, using default: {}", file.getAbsolutePath());
			return file;
		} else {
			return new File(configPath, CONFIGURATION_FILENAME);
		}
	}

	public Configuration readConfiguration() {
		Configuration configuration = JAXB.unmarshal(getLocation(), Configuration.class);
		return configuration;
	}

	public void write(Configuration configuration) {
		JAXB.marshal(configuration, getLocation());
	}
}
