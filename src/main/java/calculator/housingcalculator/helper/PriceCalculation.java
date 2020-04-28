package calculator.housingcalculator.helper;

import calculator.housingcalculator.dao.model.PriceGuide;
import calculator.housingcalculator.model.requests.PriceChange;

public class PriceCalculation {

  public static PriceGuide calculateionPrice(PriceChange priceChange) {
    PriceGuide actualpriceGuide = new PriceGuide();
    actualpriceGuide.setPriceColdWater(priceChange.getPrice().getPriceColdWater());
    actualpriceGuide.setPriceHotWater(priceChange.getPrice().getPriceHotWater());
    actualpriceGuide.setPriceGas(priceChange.getPrice().getPriceGas());
    actualpriceGuide.setPriceElectricitypriceElectricity(priceChange.getPrice().getPriceElectricity());
    return actualpriceGuide;
  }

}
