CREATE TABLE billing_period(
	id smallint unsigned not null auto_increment,
	currentmonth varchar(36),
	coldWater numeric(12),
	hotWater numeric(12),
	gas numeric(12),
	electricity numeric(50),
	CONSTRAINT pk_billing_period PRIMARY KEY (id)
);

 
COMMIT;