package co.edu.unillanos.msg;

import co.edu.unillanos.msg.application.port.InvestmentRepository;
import co.edu.unillanos.msg.application.port.MortgageRepository;
import co.edu.unillanos.msg.application.port.OperatingExpensesRepository;
import co.edu.unillanos.msg.application.usecase.EstimateFundsForWeek;
import co.edu.unillanos.msg.application.usecase.RegisterMortgage;
import co.edu.unillanos.msg.application.usecase.RegisterMortgageRequest;
import co.edu.unillanos.msg.domain.Investment;
import co.edu.unillanos.msg.domain.OperatingExpenses;
import co.edu.unillanos.msg.infrastructure.InMemoryInvestmentRepository;
import co.edu.unillanos.msg.infrastructure.InMemoryMortgageRepository;
import co.edu.unillanos.msg.infrastructure.InMemoryOperatingExpensesRepository;

/**
 * Raíz de composición de la consola: el único lugar que conoce todas las capas
 * y las conecta.
 * Rol responsable: Registro.
 *
 * En el Laboratorio 2 se agrega una ventana JavaFX; esta clase se conserva.
 *
 * Se ejecuta con: mvn -q compile exec:java
 */
public final class Main {

    public static void main(String[] args) {
        // 1. Los tres repositorios en memoria
        MortgageRepository mortgages = new InMemoryMortgageRepository();
        InvestmentRepository investments = new InMemoryInvestmentRepository();
        OperatingExpensesRepository expenses =
                new InMemoryOperatingExpensesRepository(new OperatingExpenses(39000.00));

        // 2. Las hipotecas del ejemplo de verificación
        RegisterMortgage register = new RegisterMortgage(mortgages);
        register.execute(new RegisterMortgageRequest("M-001", 300.00, 2600.00, 1040.00, 1000.00));
        register.execute(new RegisterMortgageRequest("M-002", 200.00, 1560.00, 520.00, 1000.00));

        // 3. Las inversiones
        investments.save(new Investment("I-01", "Bonos del Tesoro", 52000.00));
        investments.save(new Investment("I-02", "CDT", 26000.00));

        // 4. Los fondos disponibles para la semana (esperado: 1160.00)
        EstimateFundsForWeek estimateFunds =
                new EstimateFundsForWeek(mortgages, investments, expenses);
        double funds = estimateFunds.execute();

        System.out.printf("Fondos disponibles para la semana: %.2f%n", funds);
    }
}
