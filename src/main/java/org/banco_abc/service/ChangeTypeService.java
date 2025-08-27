package org.banco_abc.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.banco_abc.client.ChangeTypeApiClient;
import org.banco_abc.client.ChangeTypeResponse;
import org.banco_abc.model.ChangeTypeQuery;
import org.banco_abc.repository.ChangeTypeQueryRepository;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.LocalDate;
import java.time.LocalDateTime;

@ApplicationScoped
public class ChangeTypeService {

    private static final int MAX_QUERY = 10;

    @Inject
    ChangeTypeQueryRepository changeTypeQueryRepository;

    @Inject
    @RestClient
    ChangeTypeApiClient changeTypeApiClient;

    @Transactional
    public ChangeTypeResponse queryChangeType(String dni) {
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        long todayQueryNumber = changeTypeQueryRepository.countQueriesToday(dni, start, end);

        if (todayQueryNumber >= MAX_QUERY) {
            throw new RuntimeException(" Daily query limit reached");
        }

        ChangeTypeResponse response = changeTypeApiClient.getChangeType();

        ChangeTypeQuery query = new ChangeTypeQuery();
        query.dni = dni;
        query.queryDate = LocalDateTime.now();
        changeTypeQueryRepository.persist(query);

        return response;
    }
}
