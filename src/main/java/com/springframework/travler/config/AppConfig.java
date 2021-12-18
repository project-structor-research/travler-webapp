package com.springframework.travler.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	private static Logger logger = LoggerFactory.getLogger(AppConfig.class);
	
	@Bean
	public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() throws IOException {
		PropertyPlaceholderConfigurer ppc = new PropertyPlaceholderConfigurer();
		
		String resource = "/dews/property/globals.properties";
		Map<String, String> rootConfigurations = null;
		
		try {		
			Properties properties = new Properties();
			InputStream reader = AppConfig.class.getResourceAsStream(resource);
	        properties.load(reader);
	        
	        rootConfigurations = (Map<String, String>) properties.keySet().stream()
					.collect(Collectors.toMap(s -> s.toString(), s -> properties.get(s.toString()).toString()));		
			String applicationConfigurationPath = Paths.get(getOsPath(rootConfigurations.get("application.home.path")), new String[]{"config"}).toString();
			logger.info(applicationConfigurationPath);
			
			File directory = new File(applicationConfigurationPath);
			File[] files = directory.listFiles(new FilenameFilter() { 	
				@Override 
				public boolean accept(File dir, String name) { 
					return name.endsWith("properties"); 
				}
			});
			
			for (int i = 0; i < files.length; ++i) {
				if (files[i].canRead()) {
					FileInputStream fs = new FileInputStream(files[i].getPath());
					properties.load(fs);
					rootConfigurations = (Map<String, String>) properties.keySet().stream()
							.collect(Collectors.toMap(s -> s.toString(), s -> properties.get(s.toString()).toString()));
					for(String key : rootConfigurations.keySet()) {
						System.setProperty(key, rootConfigurations.get(key));
						logger.info(key + " : " + rootConfigurations.get(key));
					}
				}
			}
			
			String appHomePath = getOsPath(rootConfigurations.get("application.home.path"));
			String configPath = applicationConfigurationPath;
			String repoConfigPath = Paths.get(appHomePath, "repository").toString();
			String viewPath = Paths.get(repoConfigPath, "view").toString();
			String htmlPath = Paths.get(viewPath, "html").toString();
			String jsPath = Paths.get(viewPath, "js").toString();
			String imagePath = Paths.get(viewPath, "images").toString();
			String cssPath = Paths.get(viewPath, "css").toString();
			
			System.setProperty("comet.home.dir", appHomePath);
			System.setProperty("comet.config.dir", configPath);
			System.setProperty("comet.views.dir", viewPath);
			System.setProperty("comet.html.dir", htmlPath);
			System.setProperty("comet.images.dir", imagePath);
			System.setProperty("comet.js.dir", jsPath);
			System.setProperty("comet.css.dir", cssPath);
			
		} catch(Exception e) {
			logger.error(e.getMessage());
		}
		return ppc;
	}
	
	public static String getOsPath(String appHome) {
		if (isWindow())
			return (new File(String.format("%s:\\%s", new Object[]{getSystemDriver(), appHome}))).getPath();
		if (isMacOS())
			return (new File(String.format("/%s/%s", new Object[]{"Users", appHome}))).getPath();
		if (isLinux())
			return (new File(String.format("/%s/%s", new Object[]{"home", appHome}))).getPath();
		return (new File(String.format("/%s", new Object[]{appHome}))).getPath();
	}

	public static String replaceOsPath(String sourcePath) {
		if (isWindow())
			return sourcePath.replace("/", "\\");
		if (isMacOS())
			return sourcePath.replace("\\", "/");
		if (isLinux())
			return sourcePath.replace("\\", "/");
		return sourcePath;
	}

	public static boolean isWindow() {
		return

		(System.getProperty("os.name").toLowerCase().indexOf("win") >= 0);
	}

	public static boolean isMacOS() {
		return

		(System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0);
	}

	public static boolean isLinux() {
		return

		(System.getProperty("os.name").toLowerCase().indexOf("linux") >= 0);
	}

	public static String getLoginUserID() {
		return System.getProperty("user.name");
	}

	public static String getVirtulMachineVersion() {
		return System.getProperty("java.vm.version");
	}

	public static String getClassVersion() {
		return System.getProperty("java.class.version");
	}

	public static String getUserHomeDir() {
		return System.getProperty("user.home");
	}

	public static String getSystemDriver() {
		String driverName = null;
		if (isWindow()) {
			String userDir = getUserHomeDir();
			driverName = userDir.substring(0, 1);
		}
		return driverName;
	}
}
