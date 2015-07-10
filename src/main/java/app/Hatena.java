package main.java.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("hatena")
public class Hatena {

	@Path("keyword")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String keyword(@QueryParam("word") String word) {
		WeatherClient wc = new WeatherClient();
		String response = wc.request();
		return response;
	}
}