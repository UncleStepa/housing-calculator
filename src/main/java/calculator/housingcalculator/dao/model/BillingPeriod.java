package calculator.housingcalculator.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "billing_period")
public class BillingPeriod {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY )
  private long id;

  @Column(name = "currentmonth")
  private String currentMonth;

  @Column(name = "coldwater")
  private int coldWater;

  @Column(name = "hotwater")
  private int hotWater;

  private int gas;
  private int electricity;

  public BillingPeriod(String currentMonth, int coldWater, int hotWater, int gas, int electricity) {
    this.currentMonth = currentMonth;
    this.coldWater = coldWater;
    this.hotWater = hotWater;
    this.gas = gas;
    this.electricity = electricity;
  }

  public BillingPeriod(long id, String currentMonth, int coldWater, int hotWater, int gas, int electricity) {
    this.id = id;
    this.currentMonth = currentMonth;
    this.coldWater = coldWater;
    this.hotWater = hotWater;
    this.gas = gas;
    this.electricity = electricity;
  }

  public BillingPeriod() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCurrentMonth() {
    return currentMonth;
  }

  public void setCurrentMonth(String currentMonth) {
    this.currentMonth = currentMonth;
  }

  public int getColdWater() {
    return coldWater;
  }

  public void setColdWater(int coldWater) {
    this.coldWater = coldWater;
  }

  public int getHotWater() {
    return hotWater;
  }

  public void setHotWater(int hotWater) {
    this.hotWater = hotWater;
  }

  public int getGas() {
    return gas;
  }

  public void setGas(int gas) {
    this.gas = gas;
  }

  public int getElectricity() {
    return electricity;
  }

  public void setElectricity(int electricity) {
    this.electricity = electricity;
  }

  @Override
  public String toString() {
    return "BillingPeriod{" +
            "id='" + id + '\'' +
            ", currentMonth='" + currentMonth + '\'' +
            ", coldWater=" + coldWater +
            ", hotWater=" + hotWater +
            ", gas=" + gas +
            ", electricity=" + electricity +
            '}';
  }
}

