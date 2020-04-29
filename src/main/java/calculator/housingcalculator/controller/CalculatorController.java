package calculator.housingcalculator.controller;

import calculator.housingcalculator.dao.model.BillingPeriod;
import calculator.housingcalculator.dao.model.PriceGuide;
import calculator.housingcalculator.dao.model.TestimonyHistory;
import calculator.housingcalculator.dao.repositorys.BillingPeriodRepository;
import calculator.housingcalculator.dao.repositorys.HistoryTestimonyRepository;
import calculator.housingcalculator.dao.repositorys.PriceGuideRepository;
import calculator.housingcalculator.helper.FlowGeneration;
import calculator.housingcalculator.helper.PriceCalculation;
import calculator.housingcalculator.model.requests.PriceChange;
import calculator.housingcalculator.model.requests.RequestSaveTestimony;
import calculator.housingcalculator.model.responses.ResponsePriceChange;
import calculator.housingcalculator.model.responses.ResponseSaveTestimony;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
  public ResponseSaveTestimony getResponseSavaTestimony(@RequestBody RequestSaveTestimony requestSaveTestimony) {
    BillingPeriod billingPeriod = billingPeriodRepository.findAll().get(0);
    PriceGuide priceGuide = priceGuideRepository.findAll().get(0);

    TestimonyHistory testimonyHistory = historyTestimonyRepository.save(FlowGeneration.
            flowTestimony(billingPeriod, priceGuide, requestSaveTestimony));
    billingPeriodRepository.deleteAll();
    billingPeriodRepository.save(FlowGeneration.generateBillingPeriod(requestSaveTestimony));
    return FlowGeneration.getResponseSaveTestimony(testimonyHistory);
  }

  @CrossOrigin()
  @GetMapping("get/old/testimony/{date}")
    public ResponseSaveTestimony getResponseWhithOldTestimony(@PathVariable("date") String date) {
    TestimonyHistory testimonyHistory = historyTestimonyRepository.findAllByCurrentMonth(date);
      return  FlowGeneration.getResponseSaveTestimony(testimonyHistory);
  }

  @CrossOrigin()
  @PostMapping("/changePrice")
  public ResponsePriceChange getResponseChangePrice(@RequestBody PriceChange priceChange) {
    PriceGuide priceGuide = PriceCalculation.calculateionPrice(priceChange);
    try {
      priceGuideRepository.deleteAll();
      priceGuideRepository.save(priceGuide);
      ResponsePriceChange responsePriceChange = new ResponsePriceChange();
      responsePriceChange.setResultCode("0");
      responsePriceChange.setResultText("success");
      return responsePriceChange;
    }catch (Exception e) {
      ResponsePriceChange responsePriceChange = new ResponsePriceChange();
      responsePriceChange.setResultCode("ERR-002");
      responsePriceChange.setResultText("Ошибка сохранения в БД");
      return responsePriceChange;
    }
  }


}
