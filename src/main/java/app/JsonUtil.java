package main.java.app;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {

	public static <T> T parse(Class<T> dto, String json){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return (T) mapper.readValue(json,  dto);
		} catch (IOException e) {
			return null;
		}
	}

	public static String convert(Object dto) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			String json = mapper .writerWithDefaultPrettyPrinter().writeValueAsString(dto);
			return json;
		} catch (JsonProcessingException e) {
			return null;
		}
	}

}
