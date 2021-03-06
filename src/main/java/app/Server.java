package main.java.app;

import java.io.IOException;
import java.net.URI;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import com.sun.net.httpserver.HttpServer;

public class Server {

	public static void main(String[] args) throws IOException {
		URI uri = URI.create("http://localhost:8080/rest/");
		ResourceConfig rc = new ResourceConfig();
		rc.register(Calc.class);
		rc.register(Hatena.class);
		rc.register(WillGo.class);
		HttpServer httpServer = JdkHttpServerFactory.createHttpServer(uri, rc);
		Runtime.getRuntime().addShutdownHook(new Thread(() -> httpServer.stop(0)));
	}
}
