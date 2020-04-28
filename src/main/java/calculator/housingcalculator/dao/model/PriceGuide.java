package calculator.housingcalculator.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "price_guide")
public class PriceGuide {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int service_id;

  @Column(name = "pricecoldwater")
  private double  priceColdWater;

  @Column(name = "pricehotwater")
  private double  priceHotWater;

  @Column(name = "pricegas")
  private double  priceGas;

  @Column(name = "priceelectricity")
  private double  priceElectricity;

  public PriceGuide() {}

  public PriceGuide(double  priceColdWater, double  priceHotWater, double  priceGas, double  priceElectricitypriceElectricity) {
    this.priceColdWater = priceColdWater;
    this.priceHotWater = priceHotWater;
    this.priceGas = priceGas;
    this.priceElectricity = priceElectricitypriceElectricity;
  }

  public PriceGuide(double  priceColdWater, double  priceHotWater, double  priceElectricitypriceElectricity) {
    this.priceColdWater = priceColdWater;
    this.priceHotWater = priceHotWater;
    this.priceElectricity = priceElectricitypriceElectricity;
  }

  public PriceGuide(int service_id, double  priceColdWater, double  priceHotWater, double  priceGas, double  priceElectricitypriceElectricity) {
    this.service_id = service_id;
    this.priceColdWater = priceColdWater;
    this.priceHotWater = priceHotWater;
    this.priceGas = priceGas;
    this.priceElectricity = priceElectricitypriceElectricity;
  }




  public int getService_id() {
    return service_id;
  }

  public void setService_id(int service_id) {
    this.service_id = service_id;
  }

  public double getPriceColdWater() {
    return priceColdWater;
  }

  public void setPriceColdWater(double  priceColdWater) {
    this.priceColdWater = priceColdWater;
  }

  public double  getPriceHotWater() {
    return priceHotWater;
  }

  public void setPriceHotWater(double  priceHotWater) {
    this.priceHotWater = priceHotWater;
  }

  public double  getPriceGas() {
    return priceGas;
  }

  public void setPriceGas(double  priceGas) {
    this.priceGas = priceGas;
  }

  public double  getPriceElectricity() {
    return priceElectricity;
  }

  public void setPriceElectricitypriceElectricity(double  priceElectricitypriceElectricity) {
    this.priceElectricity = priceElectricitypriceElectricity;
  }


  @Override
  public String toString() {
    return "PriceGuide{" +
            "service_id=" + service_id +
            ", priceColdWater=" + priceColdWater +
            ", priceHotWater=" + priceHotWater +
            ", priceGas=" + priceGas +
            ", priceElectricity=" + priceElectricity +
            '}';
  }
}
