package calculator.housingcalculator.dao.repositorys;

import calculator.housingcalculator.dao.model.BillingPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  BillingPeriodRepository extends JpaRepository<BillingPeriod, Long> {

  BillingPeriod findFirstByCurrentMonth(String date);

  @Query(value = "select\n" +
          "\n" +
          "t1.id,\n" +
          "t1.currentDate as currentmonth,\n" +
          "t1.coldWater,\n" +
          "t1.hotWater,\n" +
          "t1.gas,\n" +
          "t1.electricity\n" +
          "\n" +
          "from\n" +
          "\n" +
          "(select\n" +
          "id,\n" +
          "DATE_FORMAT(STR_TO_DATE(CONCAT('01-',currentmonth), '%d-%m-%Y'),'%m-%Y') as currentDate,\n" +
          "coldWater,\n" +
          "hotWater,\n" +
          "gas,\n" +
          "electricity\n" +
          "from billing_period) t1\n" +
          "\n" +
          "where t1.currentDate = DATE_FORMAT(DATE_SUB(STR_TO_DATE(CONCAT('01-', :date), '%d-%m-%Y'),INTERVAL 1 MONTH),'%m-%Y');", nativeQuery = true)
  BillingPeriod findByCurrentMonth(@Param("date") String date);



}
