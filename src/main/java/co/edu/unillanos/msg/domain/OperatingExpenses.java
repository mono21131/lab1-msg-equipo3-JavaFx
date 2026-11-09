package co.edu.unillanos.msg.domain;

/**
 * Gastos operativos anuales estimados de la MSG Foundation.
 * Rol responsable: Inversiones.
 */
public final class OperatingExpenses {

    private final double annualAmount;

    public OperatingExpenses(double annualAmount) {
        this.annualAmount = annualAmount;
    }

    public double annualAmount() {
        return annualAmount;
    }

    /** RN7: gasto anual repartido igual entre 52 semanas. */
    public double weeklyAmount() {
        return annualAmount / Mortgage.WEEKS_PER_YEAR;
    }
}
