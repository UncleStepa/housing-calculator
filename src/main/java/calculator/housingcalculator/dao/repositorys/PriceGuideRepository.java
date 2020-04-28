package calculator.housingcalculator.dao.repositorys;


import calculator.housingcalculator.dao.model.PriceGuide;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriceGuideRepository  extends JpaRepository<PriceGuide, Long> {

  List<PriceGuide> findByPriceColdWaterAndPriceHotWaterAndPriceGasAndPriceElectricity(double text, double text2, double text3, double text4);

}
