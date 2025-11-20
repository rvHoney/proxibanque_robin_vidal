package org.formation.proxibanque_robin_vidal.controller;

import lombok.RequiredArgsConstructor;
import org.formation.proxibanque_robin_vidal.entity.Agency;
import org.formation.proxibanque_robin_vidal.service.AgencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgencyController {
    private final AgencyService agencyService;

    @GetMapping("agencies")
    List<Agency> getAgencies() {
        return agencyService.getAgencies();
    }

    @PostMapping("agencies")
    Agency createAgency(@RequestBody Agency agency) {
        return agencyService.createAgency(agency);
    }

    @GetMapping("agencies/{id}")
    ResponseEntity<Agency> getAgency(@PathVariable Long id) {
        return agencyService.getAgency(id).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("agencies/{id}")
    public ResponseEntity<Agency> replaceAgency(@PathVariable Long id, @RequestBody Agency agency) {
        Agency agencyReplaced = agencyService.replaceAgency(id, agency);
        return this.getAgency(agencyReplaced.getId());
    }
}