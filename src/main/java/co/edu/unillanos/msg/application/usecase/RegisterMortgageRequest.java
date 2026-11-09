package co.edu.unillanos.msg.application.usecase;

/** Datos que digita el usuario para registrar una hipoteca. */
public final class RegisterMortgageRequest {

    private final String mortgageNumber;
    private final double weeklyPrincipalAndInterest;
    private final double annualPropertyTax;
    private final double annualInsurancePremium;
    private final double coupleWeeklyIncome;

    public RegisterMortgageRequest(String mortgageNumber, double weeklyPrincipalAndInterest,
                                   double annualPropertyTax, double annualInsurancePremium,
                                   double coupleWeeklyIncome) {
        this.mortgageNumber = mortgageNumber;
        this.weeklyPrincipalAndInterest = weeklyPrincipalAndInterest;
        this.annualPropertyTax = annualPropertyTax;
        this.annualInsurancePremium = annualInsurancePremium;
        this.coupleWeeklyIncome = coupleWeeklyIncome;
    }

    public String mortgageNumber() {
        return mortgageNumber;
    }

    public double weeklyPrincipalAndInterest() {
        return weeklyPrincipalAndInterest;
    }

    public double annualPropertyTax() {
        return annualPropertyTax;
    }

    public double annualInsurancePremium() {
        return annualInsurancePremium;
    }

    public double coupleWeeklyIncome() {
        return coupleWeeklyIncome;
    }
}
