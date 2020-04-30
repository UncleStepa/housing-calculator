CREATE TABLE price_guide(
	service_id smallint unsigned not null auto_increment,
	priceColdWater decimal(15,2),
    priceHotWater decimal(15,2),
    priceGas decimal(15,2),
    priceElectricity decimal(15,2),
	CONSTRAINT pk_price_guide PRIMARY KEY (service_id)); 
    
    
INSERT INTO price_guide (service_id, priceColdWater, priceHotWater, priceGas, priceElectricity) 
 VALUES ("1", 3.2, 9.4, 8, 3.1);
 
COMMIT;


