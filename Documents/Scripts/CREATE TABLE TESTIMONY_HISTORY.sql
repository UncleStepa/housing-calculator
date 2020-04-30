CREATE TABLE testimony_history(
	id smallint unsigned not null auto_increment,
	currentmonth varchar(36),
    previous_month varchar(36),
	coldWater numeric(12),
	hotWater numeric(12),
	gas numeric(12),
	electricity numeric(50),
	cost_coldWater decimal(15,2),
	cost_hotWater decimal(15,2),
	cost_gas decimal(15,2),
	cost_electricity decimal(15,2),
	total_cost decimal(15,2),
	CONSTRAINT pk_testimony_history PRIMARY KEY (id)); 

COMMIT;


