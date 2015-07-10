package main.java.entity;

import java.sql.Date;
import org.seasar.doma.Entity;
import org.seasar.doma.Id;
import org.seasar.doma.Version;
import org.seasar.doma.jdbc.entity.NamingType;

@Entity(naming = NamingType.SNAKE_UPPER_CASE)
public class Weather {

	@Id
	public Integer id;

	public String gps;

	public String location;

	public String dateLabel;

	public String telop;

	@Version
	public Integer versionNo;

}
