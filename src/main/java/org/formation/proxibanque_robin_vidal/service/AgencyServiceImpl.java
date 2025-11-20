package org.formation.proxibanque_robin_vidal.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.entity.Agency;
import org.formation.proxibanque_robin_vidal.repository.AgencyRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgencyServiceImpl implements AgencyService {
    private final AgencyRepository agencyRepository;

    @Override
    public List<Agency> getAgencies() {
        return agencyRepository.findAll();
    }

    @Override
    public Agency createAgency(Agency agency) {
        return agencyRepository.save(agency);
    }

    @Override
    public Optional<Agency> getAgency(Long id) {
        return agencyRepository.findById(id);
    }

    @Override
    public Agency replaceAgency(Long id, Agency agency) {
        agency.setId(id);
        return agencyRepository.save(agency);
    }
}
