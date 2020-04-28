package calculator.housingcalculator.helper;

import calculator.housingcalculator.dao.model.BillingPeriod;
import calculator.housingcalculator.dao.model.PriceGuide;
import calculator.housingcalculator.dao.model.TestimonyHistory;
import calculator.housingcalculator.model.requests.RequestSaveTestimony;
import calculator.housingcalculator.model.responses.Consumed;
import calculator.housingcalculator.model.responses.Cost;
import calculator.housingcalculator.model.responses.ResponseSaveTestimony;
import org.springframework.util.ConcurrencyThrottleSupport;

public class FlowGeneration {

  public static TestimonyHistory flowTestimony(BillingPeriod billingPeriod, PriceGuide priceGuide, RequestSaveTestimony requestSaveTestimony) {
    TestimonyHistory testimonyHistory = new TestimonyHistory();
    testimonyHistory.setCurrent_month(requestSaveTestimony.getDate());
    testimonyHistory.setPrevious_month(billingPeriod.getCurrent_month());
    testimonyHistory.setColdWater(requestSaveTestimony.getCurrentTestimony().getColdWater() - billingPeriod.getColdWater());
    testimonyHistory.setHotWater(requestSaveTestimony.getCurrentTestimony().getHotWater() - billingPeriod.getHotWater());
    testimonyHistory.setGas(requestSaveTestimony.getCurrentTestimony().getGas() - billingPeriod.getGas());
    testimonyHistory.setElectricity(requestSaveTestimony.getCurrentTestimony().getElectricity() - billingPeriod.getElectricity());
    testimonyHistory.setCost_coldWater(billingPeriod.getColdWater() * priceGuide.getPriceColdWater());
    testimonyHistory.setCost_hotWater(billingPeriod.getHotWater() * priceGuide.getPriceHotWater());
    testimonyHistory.setCost_gas(billingPeriod.getGas() * priceGuide.getPriceGas());
    testimonyHistory.setCost_electricity(billingPeriod.getElectricity() * priceGuide.getPriceElectricity());
    testimonyHistory.setTotal_cost(testimonyHistory.getCost_coldWater() + testimonyHistory.getCost_hotWater() + testimonyHistory.getCost_gas() + testimonyHistory.getCost_electricity());
    return testimonyHistory;


  }

  public static ResponseSaveTestimony getresponseSaveTestimony(TestimonyHistory testimonyHistory) {
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
            return responseSaveTestimony;
  }
}
