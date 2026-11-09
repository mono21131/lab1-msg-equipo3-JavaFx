package co.edu.unillanos.msg.infrastructure;

import co.edu.unillanos.msg.application.port.InvestmentRepository;
import co.edu.unillanos.msg.domain.Investment;

import java.util.ArrayList;
import java.util.List;

/** Implementación en memoria del puerto InvestmentRepository. */
public final class InMemoryInvestmentRepository implements InvestmentRepository {

    private final List<Investment> investments = new ArrayList<>();

    @Override
    public void save(Investment investment) {
        investments.add(investment);
    }

    @Override
    public List<Investment> findAll() {
        return new ArrayList<>(investments);
    }
}
