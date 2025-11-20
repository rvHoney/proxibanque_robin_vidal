package org.formation.proxibanque_robin_vidal.service;

import org.formation.proxibanque_robin_vidal.entity.Agency;

import java.util.List;
import java.util.Optional;

public interface AgencyService {
    List<Agency> getAgencies();
    Agency createAgency(Agency agency);
    Optional<Agency> getAgency(Long id);
    Agency replaceAgency(Long id, Agency agency);
}
