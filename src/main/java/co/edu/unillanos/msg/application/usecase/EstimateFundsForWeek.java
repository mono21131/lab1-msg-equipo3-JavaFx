package co.edu.unillanos.msg.application.usecase;

import co.edu.unillanos.msg.application.port.InvestmentRepository;
import co.edu.unillanos.msg.application.port.MortgageRepository;
import co.edu.unillanos.msg.application.port.OperatingExpensesRepository;
import co.edu.unillanos.msg.domain.Investment;
import co.edu.unillanos.msg.domain.Mortgage;

/**
 * Caso de uso: estimar los fondos disponibles para la semana.
 * Rol responsable: Integrador.
 *
 * Regla: esta clase no contiene ninguna fórmula de RN2 a RN7.
 * Le pide cada valor a la clase experta y solo suma.
 *
 * Decisión D6 (tomada, revisable): las sumas sobre todas las hipotecas e
 * inversiones están en métodos privados de este caso de uso.
 */
public final class EstimateFundsForWeek {

    private final MortgageRepository mortgages;
    private final InvestmentRepository investments;
    private final OperatingExpensesRepository expenses;

    public EstimateFundsForWeek(MortgageRepository mortgages,
                                InvestmentRepository investments,
                                OperatingExpensesRepository expenses) {
        this.mortgages = mortgages;
        this.investments = investments;
        this.expenses = expenses;
    }

    public double execute() {
        return totalInvestmentIncome()
                + totalCoupleContributions()
                - weeklyOperatingExpenses()
                - totalEscrowPaid();
    }

    /** Gasto operativo de la semana. */
    private double weeklyOperatingExpenses() {
        return expenses.current().weeklyAmount();
    }

    /** Suma del rendimiento semanal de todas las inversiones. */
    private double totalInvestmentIncome() {
        double total = 0;
        for (Investment investment : investments.findAll()) {
            total = total + investment.weeklyReturn();
        }
        return total;
    }

    /** Suma del aporte semanal de todas las parejas. */
    private double totalCoupleContributions() {
        double total = 0;
        for (Mortgage mortgage : mortgages.findAll()) {
            total = total + mortgage.weeklyCoupleContribution();
        }
        return total;
    }

    /** Suma del impuesto y seguro semanal de todas las hipotecas (RN8). */
    private double totalEscrowPaid() {
        double total = 0;
        for (Mortgage mortgage : mortgages.findAll()) {
            total = total + mortgage.weeklyEscrow();
        }
        return total;
    }
}
