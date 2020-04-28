package calculator.housingcalculator.dao.repositorys;

        import calculator.housingcalculator.dao.model.TestimonyHistory;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.stereotype.Repository;

        import java.util.List;


@Repository
public interface HistoryTestimonyRepository extends JpaRepository<TestimonyHistory, Long> {

        TestimonyHistory findAllByCurrentMonth( final String date );
}

