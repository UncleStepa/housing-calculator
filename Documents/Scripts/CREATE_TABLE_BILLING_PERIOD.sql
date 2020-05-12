CREATE TABLE billing_period(
	id smallint unsigned not null auto_increment,
	current_month varchar(36),
	coldWater numeric(12),
	hotWater numeric(12),
	gas numeric(12),
	electricity numeric(50),
	CONSTRAINT pk_billing_period PRIMARY KEY (id)
);


INSERT INTO billing_period (id, current_month, coldWater, hotWater, gas, electricity) 
 VALUES ("1", "01-2020" ,0, 0, 0, 0);
 
COMMIT;