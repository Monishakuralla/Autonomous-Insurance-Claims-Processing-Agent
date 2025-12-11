package com.synapx.claims_agent.controller;

import com.synapx.claims_agent.model.ClaimDecisionResponse;
import com.synapx.claims_agent.model.FnolRequest;
import com.synapx.claims_agent.service.ClaimProcessingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/claims")
public class ClaimController {

    private final ClaimProcessingService claimProcessingService;

    public ClaimController(ClaimProcessingService claimProcessingService) {
        this.claimProcessingService = claimProcessingService;
    }

    @PostMapping("/process")
    public ResponseEntity<ClaimDecisionResponse> processClaim(@RequestBody FnolRequest request) {
        ClaimDecisionResponse response = claimProcessingService.process(request);
        return ResponseEntity.ok(response);
    }
}
