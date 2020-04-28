package calculator.housingcalculator.controller;


import calculator.housingcalculator.dao.model.BillingPeriod;
import calculator.housingcalculator.dao.model.PriceGuide;
import calculator.housingcalculator.dao.model.TestimonyHistory;
import calculator.housingcalculator.dao.repositorys.BillingPeriodRepository;
import calculator.housingcalculator.dao.repositorys.HistoryTestimonyRepository;
import calculator.housingcalculator.dao.repositorys.PriceGuideRepository;
import calculator.housingcalculator.helper.FlowGeneration;
import calculator.housingcalculator.helper.PriceCalculation;
import calculator.housingcalculator.model.requests.Price;
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

  @PostMapping("/save")
  public ResponseSaveTestimony getResponseSavaTestimony(@RequestBody RequestSaveTestimony requestSaveTestimony) {
    TestimonyHistory testimonyHistory = new TestimonyHistory();
    BillingPeriod billingPeriod = billingPeriodRepository.findAll().get(0);
    PriceGuide priceGuide = priceGuideRepository.findAll().get(0);

    testimonyHistory = historyTestimonyRepository.save(FlowGeneration.flowTestimony(billingPeriod, priceGuide, requestSaveTestimony));
    System.out.println(testimonyHistory.toString());
    return FlowGeneration.getresponseSaveTestimony(testimonyHistory);
  }


  public static ResponseSaveTestimony getNoralResponse() {
    ResponseSaveTestimony responseSeveTestimony = new ResponseSaveTestimony();
    responseSeveTestimony.setDate("04-2020");
    responseSeveTestimony.setPreviousDate("03-2020");

    return  responseSeveTestimony;
  }

  @GetMapping("get/old/testimony/{date}")
    public ResponseSaveTestimony getResponseWhithOldTestimony(@PathVariable("date") String date) {
      return  getNoralResponse();
  }

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
