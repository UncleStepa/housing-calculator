package calculator.housingcalculator.controller;

import calculator.housingcalculator.dao.model.BillingPeriod;
import calculator.housingcalculator.dao.model.PriceGuide;
import calculator.housingcalculator.dao.model.TestimonyHistory;
import calculator.housingcalculator.dao.repositorys.BillingPeriodRepository;
import calculator.housingcalculator.dao.repositorys.HistoryTestimonyRepository;
import calculator.housingcalculator.dao.repositorys.PriceGuideRepository;
import calculator.housingcalculator.helper.ErrorGeneration;
import calculator.housingcalculator.helper.FlowGeneration;
import calculator.housingcalculator.helper.PriceCalculation;
import calculator.housingcalculator.model.requests.PriceChange;
import calculator.housingcalculator.model.requests.RequestSaveTestimony;
import calculator.housingcalculator.model.responses.ResponsePriceChange;
import calculator.housingcalculator.model.responses.ResponseSaveTestimony;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("services/testimony")
public class CalculatorController {

  @Autowired
  PriceGuideRepository priceGuideRepository;

  @Autowired
  BillingPeriodRepository billingPeriodRepository;

  @Autowired
  HistoryTestimonyRepository historyTestimonyRepository;

  @CrossOrigin()
  @PostMapping("/save")
  public ResponseSaveTestimony getResponseSaveTestimony(@RequestBody RequestSaveTestimony requestSaveTestimony) {
    if(requestSaveTestimony.getCurrentTestimony().getColdWater() < 0 ||
            requestSaveTestimony.getCurrentTestimony().getHotWater() < 0 ||
            requestSaveTestimony.getCurrentTestimony().getGas() < 0 ||
            requestSaveTestimony.getCurrentTestimony().getElectricity() < 0) {
      return ErrorGeneration.getValidErrorTestimony();
    }
    try {
    BillingPeriod billingPeriodFirst = billingPeriodRepository.findFirstByCurrentMonth(requestSaveTestimony.getDate());
    TestimonyHistory testimonyHistoryFindDate = historyTestimonyRepository.findFirstByCurrentMonth(requestSaveTestimony.getDate());
    if (billingPeriodRepository.count() == 0) {
      billingPeriodRepository.save(FlowGeneration.generateBillingPeriod(requestSaveTestimony));
      return ErrorGeneration.getFirsTestimony();
    } if(billingPeriodFirst != null) {
        billingPeriodRepository.delete(billingPeriodRepository.findFirstByCurrentMonth(requestSaveTestimony.getDate()));
    }
      if (testimonyHistoryFindDate != null) {
        historyTestimonyRepository.delete(historyTestimonyRepository.findFirstByCurrentMonth(requestSaveTestimony.getDate()));
      }
    }catch(Exception e) {
      return ErrorGeneration.getBDErrorTestimony();
    }
    try {
    BillingPeriod billingPeriod = billingPeriodRepository.findByCurrentMonth(requestSaveTestimony.getDate());
    PriceGuide priceGuide = priceGuideRepository.findAll().get(0);
    TestimonyHistory testimonyHistory = historyTestimonyRepository.save(FlowGeneration.
            flowTestimony(billingPeriod, priceGuide, requestSaveTestimony));
    billingPeriodRepository.save(FlowGeneration.generateBillingPeriod(requestSaveTestimony));
    return FlowGeneration.getResponseSaveTestimony(testimonyHistory);
  } catch(Exception e) {
    return ErrorGeneration.getBDErrorTestimony();
  }
}

  @CrossOrigin()
  @GetMapping("get/old/testimony/{date}")
  public ResponseSaveTestimony getResponseWhithOldTestimony(@PathVariable("date") String date) {
    try {
      TestimonyHistory testimonyHistory = historyTestimonyRepository.findFirstByCurrentMonth(date);
      return FlowGeneration.getResponseSaveTestimony(testimonyHistory);
    } catch (Exception e) {
      return ErrorGeneration.getBDErrorTestimony();
    }
  }

  @CrossOrigin()
  @PostMapping("/changePrice")
  public ResponsePriceChange getResponseChangePrice(@RequestBody PriceChange priceChange) {
    ResponsePriceChange responsePriceChange = new ResponsePriceChange();
    if(priceChange.getPrice().getPriceColdWater() <= 0 ||
            priceChange.getPrice().getPriceHotWater() <= 0 ||
            priceChange.getPrice().getPriceGas() < 0 ||
            priceChange.getPrice().getPriceElectricity() <= 0) {
      responsePriceChange.setResultCode("ERR-001");
      responsePriceChange.setResultText("Ошибка валидации");
      return responsePriceChange;
    }
    PriceGuide priceGuide = PriceCalculation.calculateionPrice(priceChange);
    try {
      priceGuideRepository.deleteAll();
      priceGuideRepository.save(priceGuide);
      responsePriceChange.setResultCode("0");
      responsePriceChange.setResultText("success");
      return responsePriceChange;
    } catch (Exception e) {
      return ErrorGeneration.getBDErrorPricey();
    }
  }
}
