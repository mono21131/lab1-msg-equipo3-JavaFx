package co.edu.unillanos.msg.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperatingExpensesTest {

    @Test
    void weeklyAmountSpreadsAnnualExpensesOver52Weeks() {
        OperatingExpenses expenses = new OperatingExpenses(39000.00);
        assertEquals(750.00, expenses.weeklyAmount(), 0.001);
    }
}
