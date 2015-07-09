package app;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import javax.ws.rs.core.Application;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

import app.Calc;;

public class CalcTest extends JerseyTest {
/*
	@Test
	public void test_add() throws Exception {
		int c = target("rest/calc/add)").queryParam("a", 2)
									.queryParam("b",  3)
									.request()
									.get(int.class);

		assertThat(c,  is(5));
	}

	@Test
	public void test_subtract() throws Exception {
		int c = target("calc/subtract)").queryParam("a", 5)
										.queryParam("b",  3)
										.request()
										.get(int.class);

		assertThat(c,  is(2));
	}
*/
	@Override
	protected Application configure() {
		return new ResourceConfig(Calc.class);
	}

}
