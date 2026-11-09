package co.edu.unillanos.msg.application.port;

import co.edu.unillanos.msg.domain.Mortgage;

import java.util.List;

/** Puerto: lo que la aplicación necesita para guardar y recuperar hipotecas. */
public interface MortgageRepository {

    void save(Mortgage mortgage);

    /** Devuelve la hipoteca con ese número, o null si no existe. */
    Mortgage findByNumber(String mortgageNumber);

    List<Mortgage> findAll();
}
