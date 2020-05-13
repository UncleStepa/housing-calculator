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

import calculator.housingcalculator.model.responses.Faultcode;
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


    if (billingPeriodRepository.count() == 0) {
      ResponseSaveTestimony responseSaveTestimony = new ResponseSaveTestimony();
      try {
        billingPeriodRepository.save(FlowGeneration.generateBillingPeriod(requestSaveTestimony));
        Faultcode faultcode = new Faultcode();
        faultcode.setResultCode("0");
        faultcode.setResultText("Первичные показания сохранены успешно");
        responseSaveTestimony.setFaultcode(faultcode);
        return responseSaveTestimony;
      } catch (Exception e) {
        return  ErrorGeneration.getBDErrorTestimony();
      }
    }
      try {
        BillingPeriod billingPeriod = billingPeriodRepository.findAll().get(0);
        PriceGuide priceGuide = priceGuideRepository.findAll().get(0);

        TestimonyHistory testimonyHistory = historyTestimonyRepository.save(FlowGeneration.
                flowTestimony(billingPeriod, priceGuide, requestSaveTestimony));
        billingPeriodRepository.deleteAll();
        billingPeriodRepository.save(FlowGeneration.generateBillingPeriod(requestSaveTestimony));
        return FlowGeneration.getResponseSaveTestimony(testimonyHistory);
      } catch (Exception e) {
        return  ErrorGeneration.getBDErrorTestimony();
      }
    }


  @CrossOrigin()
  @GetMapping("get/old/testimony/{date}")
    public ResponseSaveTestimony getResponseWhithOldTestimony(@PathVariable("date") String date) {
    try {
      TestimonyHistory testimonyHistory = historyTestimonyRepository.findFirstByCurrentMonth(date);
      return  FlowGeneration.getResponseSaveTestimony(testimonyHistory);
    }
    catch (Exception e) {
      return  ErrorGeneration.getBDErrorTestimony();
    }
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
    return ErrorGeneration.getBDErrorPricey();
    }
  }


}
