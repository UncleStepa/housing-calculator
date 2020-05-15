package calculator.housingcalculator.helper;

import calculator.housingcalculator.dao.model.BillingPeriod;
import calculator.housingcalculator.dao.model.PriceGuide;
import calculator.housingcalculator.dao.model.TestimonyHistory;
import calculator.housingcalculator.model.requests.RequestSaveTestimony;
import calculator.housingcalculator.model.responses.Consumed;
import calculator.housingcalculator.model.responses.Cost;
import calculator.housingcalculator.model.responses.Faultcode;
import calculator.housingcalculator.model.responses.ResponseSaveTestimony;
import org.decimal4j.util.DoubleRounder;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class FlowGeneration {

  public static TestimonyHistory flowTestimony(BillingPeriod billingPeriod, PriceGuide priceGuide, RequestSaveTestimony requestSaveTestimony) {
    TestimonyHistory testimonyHistory = new TestimonyHistory();
    testimonyHistory.setCurrent_month(requestSaveTestimony.getDate());
    testimonyHistory.setPrevious_month(billingPeriod.getCurrentMonth());
    testimonyHistory.setColdWater(requestSaveTestimony.getCurrentTestimony().getColdWater() - billingPeriod.getColdWater());
    testimonyHistory.setHotWater(requestSaveTestimony.getCurrentTestimony().getHotWater() - billingPeriod.getHotWater());
    testimonyHistory.setGas(requestSaveTestimony.getCurrentTestimony().getGas() - billingPeriod.getGas());
    testimonyHistory.setElectricity(requestSaveTestimony.getCurrentTestimony().getElectricity() - billingPeriod.getElectricity());
    testimonyHistory.setCost_coldWater(roundToTwoPlaces(testimonyHistory.getColdWater() * priceGuide.getPriceColdWater()));
    testimonyHistory.setCost_hotWater(roundToTwoPlaces(testimonyHistory.getHotWater() * priceGuide.getPriceHotWater()));
    testimonyHistory.setCost_gas(roundToTwoPlaces(testimonyHistory.getGas() * priceGuide.getPriceGas()));
    testimonyHistory.setCost_electricity(roundToTwoPlaces(testimonyHistory.getElectricity() * priceGuide.getPriceElectricity()));
    testimonyHistory.setTotal_cost(roundToTwoPlaces(testimonyHistory.getCost_coldWater() + testimonyHistory.getCost_hotWater() + testimonyHistory.getCost_gas() + testimonyHistory.getCost_electricity()));
    return testimonyHistory;
  }
  public static double roundToTwoPlaces(double d) {
    BigDecimal bd = new BigDecimal(Double.toString(d));
    return (bd.setScale(2, RoundingMode.HALF_UP)).doubleValue();
  }

  public static ResponseSaveTestimony getResponseSaveTestimony(TestimonyHistory testimonyHistory) {
    ResponseSaveTestimony responseSaveTestimony = new ResponseSaveTestimony();
    responseSaveTestimony.setDate(testimonyHistory.getCurrent_month());
    responseSaveTestimony.setPreviousDate(testimonyHistory.getPrevious_month());
    Consumed consumed = new Consumed();
    consumed.setColdWater(testimonyHistory.getColdWater());
    consumed.setHotWater(testimonyHistory.getHotWater());
    consumed.setGas(testimonyHistory.getGas());
    consumed.setElectricity(testimonyHistory.getElectricity());
    responseSaveTestimony.setConsumed(consumed);
    Cost cost = new Cost();
    cost.setColdWater(testimonyHistory.getCost_coldWater());
    cost.setHotWater(testimonyHistory.getCost_hotWater());
    cost.setGas(testimonyHistory.getCost_gas());
    cost.setElectricity(testimonyHistory.getCost_electricity());
    responseSaveTestimony.setCost(cost);
    responseSaveTestimony.setTotalCost(testimonyHistory.getTotal_cost());
    Faultcode faultcode = new Faultcode();
    faultcode.setResultCode("0");
    faultcode.setResultText("success");
    responseSaveTestimony.setFaultcode(faultcode);
    return responseSaveTestimony;
  }

  public static BillingPeriod generateBillingPeriod(RequestSaveTestimony requestSaveTestimony) {
    BillingPeriod billingPeriod = new BillingPeriod();
    billingPeriod.setCurrentMonth(requestSaveTestimony.getDate());
    billingPeriod.setColdWater(requestSaveTestimony.getCurrentTestimony().getColdWater());
    billingPeriod.setHotWater(requestSaveTestimony.getCurrentTestimony().getHotWater());
    billingPeriod.setGas(requestSaveTestimony.getCurrentTestimony().getGas());
    billingPeriod.setElectricity(requestSaveTestimony.getCurrentTestimony().getElectricity());
    return billingPeriod;
  }
}
