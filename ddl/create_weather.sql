-- Table: weather

-- DROP TABLE weather;

CREATE TABLE weather
(
  id integer NOT NULL,
  gps character varying(100),
  location character varying(100),
  date_label character varying(20),
  telop character varying(20),
  version_no integer,
  CONSTRAINT pk_weather PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE weather
  OWNER TO postgres;
