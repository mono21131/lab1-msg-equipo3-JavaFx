package co.edu.unillanos.msg.infrastructure;

import co.edu.unillanos.msg.application.port.MortgageRepository;
import co.edu.unillanos.msg.domain.Mortgage;

import java.util.ArrayList;
import java.util.List;

/** Implementación en memoria del puerto MortgageRepository. */
public final class InMemoryMortgageRepository implements MortgageRepository {

    private final List<Mortgage> mortgages = new ArrayList<>();

    @Override
    public void save(Mortgage mortgage) {
        mortgages.add(mortgage);
    }

    @Override
    public Mortgage findByNumber(String mortgageNumber) {
        for (Mortgage mortgage : mortgages) {
            if (mortgage.mortgageNumber().equals(mortgageNumber)) {
                return mortgage;
            }
        }
        return null;
    }

    @Override
    public List<Mortgage> findAll() {
        return new ArrayList<>(mortgages);
    }
}
