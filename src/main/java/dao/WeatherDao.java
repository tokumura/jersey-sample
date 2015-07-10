package main.java.dao;

import main.java.conf.AppConfig;
import main.java.entity.Weather;
import org.seasar.doma.Dao;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

@Dao(config = AppConfig.class)
public interface WeatherDao {

	@Select
	Weather selectById(Integer id);

}
