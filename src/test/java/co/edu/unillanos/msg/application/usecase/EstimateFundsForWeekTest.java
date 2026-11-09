package co.edu.unillanos.msg.application.usecase;

import co.edu.unillanos.msg.domain.Investment;
import co.edu.unillanos.msg.domain.OperatingExpenses;
import co.edu.unillanos.msg.infrastructure.InMemoryInvestmentRepository;
import co.edu.unillanos.msg.infrastructure.InMemoryMortgageRepository;
import co.edu.unillanos.msg.infrastructure.InMemoryOperatingExpensesRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstimateFundsForWeekTest {

    @Test
    void estimatesFundsWithCaseData() {
        InMemoryMortgageRepository mortgages = new InMemoryMortgageRepository();
        InMemoryInvestmentRepository investments = new InMemoryInvestmentRepository();
        InMemoryOperatingExpensesRepository expenses =
                new InMemoryOperatingExpensesRepository(new OperatingExpenses(39000.00));

        RegisterMortgage register = new RegisterMortgage(mortgages);
        register.execute(new RegisterMortgageRequest("M-001", 300.00, 2600.00, 1040.00, 1000.00));
        register.execute(new RegisterMortgageRequest("M-002", 200.00, 1560.00, 520.00, 1000.00));
        investments.save(new Investment("I-01", "Bonos del Tesoro", 52000.00));
        investments.save(new Investment("I-02", "CDT", 26000.00));

        EstimateFundsForWeek estimateFunds =
                new EstimateFundsForWeek(mortgages, investments, expenses);

        assertEquals(1160.00, estimateFunds.execute(), 0.001);
    }
}
