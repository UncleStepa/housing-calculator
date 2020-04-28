package calculator.housingcalculator.dao.repositorys;

import calculator.housingcalculator.dao.model.BillingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  BillingPeriodRepository extends JpaRepository<BillingPeriod, Long> {


}
