package main.java.app;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;
import javax.json.stream.JsonParserFactory;

import org.seasar.doma.jdbc.tx.LocalTransaction;

import main.java.conf.AppConfig;
import main.java.dao.WeatherDao;
import main.java.dao.WeatherDaoImpl;
import main.java.entity.Weather;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;

@Path("willgo")
public class WillGo {

	@Path("show")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String show(@QueryParam("city") String city, @QueryParam("id") Integer id) throws Exception {
		WeatherClient wc = new WeatherClient();
		String json = wc.request();
		this.parse();

		// doma test
		domaSelect(id);

		return json;
	}

	public void parse() throws MalformedURLException, IOException {
        //JsonParserでパースしてみる
        JsonParser parser =
            Json.createParser(
                new URL("http://weather.livedoor.com/forecast/webservice/json/v1?city=260010").openStream()
            );

        //適当に出力してみる
        boolean isDateLabel = false;
        boolean isTelop = false;
        String weather = "";
        while(parser.hasNext()){
            JsonParser.Event event = parser.next();
            switch(event){
                case KEY_NAME:
                	if (parser.getString().equals("dateLabel")) {
                		isDateLabel = true;
                		isTelop = false;
                	} else if (parser.getString().equals("telop")) {
                		isDateLabel = false;
                		isTelop = true;
                	} else {
                		isDateLabel = false;
                		isTelop = false;
                	}
                    break;
                case VALUE_STRING:
                	if (isDateLabel) {
                		weather = parser.getString() + ",";
                	}

                	if (isTelop) {
                		weather = weather + parser.getString();
                		System.out.println(weather);
                		weather = "";
                	}
                    //System.out.println("Value=" + parser.getString());
                    break;
                default:
                    break;
            }
        }
	}

	public void parse(String json) {
		JsonParserFactory factory = Json.createParserFactory(null);
		JsonParser parser = factory.createParser(new StringReader(json));
		while(parser.hasNext()) {
			Event event = parser.next();
			switch(event) {
			case KEY_NAME: {
				System.out.print(parser.getString() + "="); break;
			}
			case VALUE_STRING: {
				System.out.print(parser.getString()); break;
			}
			}
		}
	}

	public void domaSelect(Integer id) {
		LocalTransaction tx = AppConfig.getLocalTransaction();
		try {
			tx.begin();
			WeatherDao dao = new WeatherDaoImpl();
			Weather weather = dao.selectById(id);
			System.out.println(weather.location);
		} catch (Exception ex) {
			tx.rollback();
		}
	}
}
