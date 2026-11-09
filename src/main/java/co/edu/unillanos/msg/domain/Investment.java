package co.edu.unillanos.msg.domain;

/**
 * Inversión de la MSG Foundation.
 * Rol responsable: Inversiones.
 *
 * Decisión D3 (tomada, revisable): la clase está declarada final.
 */
public final class Investment {

    private final String investmentId;
    private final String description;
    private final double annualExpectedReturn;

    public Investment(String investmentId, String description, double annualExpectedReturn) {
        this.investmentId = investmentId;
        this.description = description;
        this.annualExpectedReturn = annualExpectedReturn;
    }

    public String investmentId() {
        return investmentId;
    }

    public String description() {
        return description;
    }

    public double annualExpectedReturn() {
        return annualExpectedReturn;
    }

    /** RN6: rendimiento anual esperado / 52. */
    public double weeklyReturn() {
        return annualExpectedReturn / Mortgage.WEEKS_PER_YEAR;
    }
}
