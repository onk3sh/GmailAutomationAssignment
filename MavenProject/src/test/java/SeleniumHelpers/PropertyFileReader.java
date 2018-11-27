package SeleniumHelpers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class PropertyFileReader {

	private Properties properties;
	private final String propertyFilePath = "src//test//resources//inputdata.properties";

	public PropertyFileReader() {
		BufferedReader reader;
		try {

			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Properties not found at " + propertyFilePath);
		}
	}

	public long getNum(String str) {
		String num = properties.getProperty(str);
		if (num != null)
			return Long.parseLong(num);
		else
			throw new RuntimeException(str+" not specified in the inputdata.properties file.");
	}
	
	public String getString(String str) {
		String value = properties.getProperty(str);
		if (value != null)
			return value;
		else
			throw new RuntimeException(str+" not specified in the inputdata.properties file.");
	}

}
