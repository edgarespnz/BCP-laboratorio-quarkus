package org.banco_abc.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.banco_abc.model.ChangeTypeQuery;

import java.time.LocalDateTime;

@ApplicationScoped
public class ChangeTypeQueryRepository implements PanacheRepository<ChangeTypeQuery> {

    public long countQueriesToday(String dni, LocalDateTime start, LocalDateTime end) {
        return find("dni = ?1 and queryDate between ?2 and ?3", dni, start, end)
                .count();
    }
}
