package co.edu.unillanos.msg.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MortgageTest {

    /** Tolerancia para comparar montos en double. */
    private static final double DELTA = 0.001;

    private final Mortgage m1 = new Mortgage("M-001", 300.00, 2600.00, 1040.00, 1000.00);
    private final Mortgage m2 = new Mortgage("M-002", 200.00, 1560.00, 520.00, 1000.00);

    @Test
    void weeklyEscrowSpreadsTaxAndInsuranceOver52Weeks() {
        assertEquals(70.00, m1.weeklyEscrow(), DELTA);
    }

    @Test
    void totalWeeklyPaymentAddsPrincipalInterestAndEscrow() {
        assertEquals(370.00, m1.totalWeeklyPayment(), DELTA);
    }

    @Test
    void grantCoversWhatExceeds28PercentOfIncome() {
        assertEquals(90.00, m1.weeklyGrant(), DELTA);
    }

    @Test
    void grantIsZeroWhenCoupleCanPayEverything() {
        assertEquals(0.00, m2.weeklyGrant(), DELTA);
    }

    @Test
    void coupleContributionIsTotalMinusGrant() {
        assertEquals(280.00, m1.weeklyCoupleContribution(), DELTA);
        assertEquals(240.00, m2.weeklyCoupleContribution(), DELTA);
    }

    @Test
    void rejectsNonPositiveIncome() {
        assertThrows(IllegalArgumentException.class,
                () -> new Mortgage("M-003", 100.00, 0, 0, 0));
    }
}
