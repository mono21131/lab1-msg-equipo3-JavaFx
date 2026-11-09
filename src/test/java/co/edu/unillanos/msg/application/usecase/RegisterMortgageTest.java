package co.edu.unillanos.msg.application.usecase;

import co.edu.unillanos.msg.infrastructure.InMemoryMortgageRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterMortgageTest {

    private final RegisterMortgageRequest request =
            new RegisterMortgageRequest("M-001", 300.00, 2600.00, 1040.00, 1000.00);

    @Test
    void savesTheNewMortgage() {
        InMemoryMortgageRepository repository = new InMemoryMortgageRepository();
        RegisterMortgage register = new RegisterMortgage(repository);

        register.execute(request);

        assertNotNull(repository.findByNumber("M-001"));
    }

    @Test
    void rejectsDuplicateMortgageNumber() {
        RegisterMortgage register = new RegisterMortgage(new InMemoryMortgageRepository());
        register.execute(request);

        assertThrows(IllegalStateException.class, () -> register.execute(request));
    }
}
