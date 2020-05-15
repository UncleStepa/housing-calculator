package calculator.housingcalculator.helper;

import calculator.housingcalculator.model.requests.PriceChange;
import calculator.housingcalculator.model.responses.Faultcode;
import calculator.housingcalculator.model.responses.ResponsePriceChange;
import calculator.housingcalculator.model.responses.ResponseSaveTestimony;

public class ErrorGeneration {
  public static ResponseSaveTestimony getBDErrorTestimony() {
    ResponseSaveTestimony responseSaveTestimony = new ResponseSaveTestimony();
    Faultcode faultcode = new Faultcode();
    faultcode.setResultCode("ERR-002");
    faultcode.setResultText("Ошибка сохранения в БД");
    responseSaveTestimony.setFaultcode(faultcode);
    return responseSaveTestimony;
  }

  public static ResponseSaveTestimony getValidErrorTestimony() {
    ResponseSaveTestimony responseSaveTestimony = new ResponseSaveTestimony();
    Faultcode faultcode = new Faultcode();
    faultcode.setResultCode("ERR-001");
    faultcode.setResultText("Ошибка валидации");
    responseSaveTestimony.setFaultcode(faultcode);
    return responseSaveTestimony;
  }

  public static ResponseSaveTestimony getFirsTestimony() {
    ResponseSaveTestimony responseSaveTestimony = new ResponseSaveTestimony();
    Faultcode faultcode = new Faultcode();
    faultcode.setResultCode("0");
    faultcode.setResultText("Первичные показания сохранены успешно");
    responseSaveTestimony.setFaultcode(faultcode);
    return responseSaveTestimony;
  }

  public static ResponsePriceChange getBDErrorPricey() {
    ResponsePriceChange responsePriceChange = new ResponsePriceChange();
    responsePriceChange.setResultCode("ERR-002");
    responsePriceChange.setResultText("Ошибка сохранения в БД");
    return responsePriceChange;
  }

}
