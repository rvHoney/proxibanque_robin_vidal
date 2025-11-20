package org.formation.proxibanque_robin_vidal.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HealthController {
    @GetMapping("health")
    Boolean getHealth() {
        return true;
    }
}
