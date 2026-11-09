package co.edu.unillanos.msg.domain;

/**
 * Hipoteca otorgada por la MSG Foundation a una pareja.
 * Rol responsable: Hipotecas.
 */
public final class Mortgage {

    /** Semanas del año: se usa para pasar valores anuales a semanales. */
    static final double WEEKS_PER_YEAR = 52;

    /** RN4: porcentaje máximo del ingreso semanal que aporta la pareja. */
    private static final double MAX_INCOME_PERCENT = 28;

    private final String mortgageNumber;
    private final double weeklyPrincipalAndInterest;
    private final double annualPropertyTax;
    private final double annualInsurancePremium;
    private final double coupleWeeklyIncome;

    // Decisión D2: el subsidio y el aporte de la pareja no se guardan como
    // atributos; se calculan cada vez que se piden.

    public Mortgage(String mortgageNumber, double weeklyPrincipalAndInterest,
                    double annualPropertyTax, double annualInsurancePremium,
                    double coupleWeeklyIncome) {
        if (coupleWeeklyIncome <= 0) {
            throw new IllegalArgumentException(
                    "El ingreso semanal de la pareja debe ser positivo");
        }
        this.mortgageNumber = mortgageNumber;
        this.weeklyPrincipalAndInterest = weeklyPrincipalAndInterest;
        this.annualPropertyTax = annualPropertyTax;
        this.annualInsurancePremium = annualInsurancePremium;
        this.coupleWeeklyIncome = coupleWeeklyIncome;
    }

    public String mortgageNumber() {
        return mortgageNumber;
    }

    /** RN2: costo semanal de impuesto predial y seguro. */
    public double weeklyEscrow() {
        return (annualPropertyTax + annualInsurancePremium) / WEEKS_PER_YEAR;
    }

    /** RN3: capital e intereses más el costo semanal de impuesto y seguro. */
    public double totalWeeklyPayment() {
        return weeklyPrincipalAndInterest + weeklyEscrow();
    }

    /** RN4: lo máximo que la pareja aporta por semana. */
    public double maxCoupleContribution() {
        return coupleWeeklyIncome * MAX_INCOME_PERCENT / 100;
    }

    /** RN5: lo que cubre la fundación; nunca es negativo. */
    public double weeklyGrant() {
        double grant = totalWeeklyPayment() - maxCoupleContribution();
        if (grant < 0) {
            grant = 0;
        }
        return grant;
    }

    /** Lo que paga la pareja: cuota total menos subsidio. */
    public double weeklyCoupleContribution() {
        return totalWeeklyPayment() - weeklyGrant();
    }
}
