package co.edu.unillanos.msg.application.usecase;

import co.edu.unillanos.msg.application.port.MortgageRepository;
import co.edu.unillanos.msg.domain.Mortgage;

/**
 * Caso de uso: registrar una hipoteca nueva.
 * Rol responsable: Registro.
 *
 * Decisión D4: este caso de uso detecta el número de hipoteca repetido.
 */
public final class RegisterMortgage {

    private final MortgageRepository mortgages;

    public RegisterMortgage(MortgageRepository mortgages) {
        this.mortgages = mortgages;
    }

    /**
     * Crea la hipoteca con los datos del request, la guarda y la devuelve.
     * Rechaza con IllegalStateException un número de hipoteca repetido.
     */
    public Mortgage execute(RegisterMortgageRequest request) {
        if (mortgages.findByNumber(request.mortgageNumber()) != null) {
            throw new IllegalStateException(
                    "Ya existe la hipoteca " + request.mortgageNumber());
        }

        Mortgage mortgage = new Mortgage(
                request.mortgageNumber(),
                request.weeklyPrincipalAndInterest(),
                request.annualPropertyTax(),
                request.annualInsurancePremium(),
                request.coupleWeeklyIncome());

        mortgages.save(mortgage);
        return mortgage;
    }
}
