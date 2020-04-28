package calculator.housingcalculator.dao.model;

import javax.persistence.*;

@Entity
@Table(name = "testimony_history")
public class TestimonyHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String current_month;
  private String previous_month;

  @Column(name = "coldwater")
  private int coldWater;

  @Column(name = "hotwater")
  private int hotWater;

  private int gas;

  private int electricity;

  @Column(name = "cost_coldwater")
  private double cost_coldWater;

  @Column(name = "cost_hotwater")
  private double cost_hotWater;

  private double cost_gas;
  private double cost_electricity;
  private double total_cost;

  public double getTotal_cost() {
    return total_cost;
  }

  public void setTotal_cost(double total_cost) {
    this.total_cost = total_cost;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getCurrent_month() {
    return current_month;
  }

  public void setCurrent_month(String current_month) {
    this.current_month = current_month;
  }

  public String getPrevious_month() {
    return previous_month;
  }

  public void setPrevious_month(String previous_month) {
    this.previous_month = previous_month;
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

  public double getCost_coldWater() {
    return cost_coldWater;
  }

  public void setCost_coldWater(double cost_coldWater) {
    this.cost_coldWater = cost_coldWater;
  }

  public double getCost_hotWater() {
    return cost_hotWater;
  }

  public void setCost_hotWater(double cost_hotWater) {
    this.cost_hotWater = cost_hotWater;
  }

  public double getCost_gas() {
    return cost_gas;
  }

  public void setCost_gas(double cost_gas) {
    this.cost_gas = cost_gas;
  }

  public double getCost_electricity() {
    return cost_electricity;
  }

  public void setCost_electricity(double cost_electricity) {
    this.cost_electricity = cost_electricity;
  }

  @Override
  public String toString() {
    return "TestimonyHistory{" +
            "id='" + id + '\'' +
            ", current_month='" + current_month + '\'' +
            ", previous_month='" + previous_month + '\'' +
            ", coldWater=" + coldWater +
            ", hotWater=" + hotWater +
            ", gas=" + gas +
            ", electricity=" + electricity +
            ", cost_coldWater=" + cost_coldWater +
            ", cost_hotWater=" + cost_hotWater +
            ", cost_gas=" + cost_gas +
            ", cost_electricity=" + cost_electricity +
            '}';
  }
}
