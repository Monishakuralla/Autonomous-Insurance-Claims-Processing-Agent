package com.synapx.claims_agent.service;

import com.synapx.claims_agent.model.ClaimDecisionResponse;
import com.synapx.claims_agent.model.FnolRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClaimProcessingService {

    public ClaimDecisionResponse process(FnolRequest request) {
        List<String> missingFields = findMissingFields(request);
        String route = determineRoute(request, missingFields);
        String reasoning = buildReasoning(request, missingFields, route);

        Map<String, Object> extracted = new LinkedHashMap<>();
        extracted.put("policyNumber", request.getPolicyNumber());
        extracted.put("policyholderName", request.getPolicyholderName());
        extracted.put("policyEffectiveFrom", request.getPolicyEffectiveFrom());
        extracted.put("policyEffectiveTo", request.getPolicyEffectiveTo());
        extracted.put("incidentDate", request.getIncidentDate());
        extracted.put("incidentTime", request.getIncidentTime());
        extracted.put("incidentLocation", request.getIncidentLocation());
        extracted.put("incidentDescription", request.getIncidentDescription());
        extracted.put("claimantName", request.getClaimantName());
        extracted.put("thirdPartyNames", request.getThirdPartyNames());
        extracted.put("contactDetails", request.getContactDetails());
        extracted.put("assetType", request.getAssetType());
        extracted.put("assetId", request.getAssetId());
        extracted.put("estimatedDamage", request.getEstimatedDamage());
        extracted.put("claimType", request.getClaimType());
        extracted.put("attachments", request.getAttachments());
        extracted.put("initialEstimate", request.getInitialEstimate());

        return new ClaimDecisionResponse(extracted, missingFields, route, reasoning);
    }

    private List<String> findMissingFields(FnolRequest r) {
        List<String> missing = new ArrayList<>();

        if (isBlank(r.getPolicyNumber())) missing.add("policyNumber");
        if (isBlank(r.getPolicyholderName())) missing.add("policyholderName");
        if (isBlank(r.getPolicyEffectiveFrom())) missing.add("policyEffectiveFrom");
        if (isBlank(r.getPolicyEffectiveTo())) missing.add("policyEffectiveTo");

        if (isBlank(r.getIncidentDate())) missing.add("incidentDate");
        if (isBlank(r.getIncidentTime())) missing.add("incidentTime");
        if (isBlank(r.getIncidentLocation())) missing.add("incidentLocation");
        if (isBlank(r.getIncidentDescription())) missing.add("incidentDescription");

        if (isBlank(r.getClaimantName())) missing.add("claimantName");
        if (isBlank(r.getContactDetails())) missing.add("contactDetails");

        if (isBlank(r.getAssetType())) missing.add("assetType");
        if (isBlank(r.getAssetId())) missing.add("assetId");
        if (r.getEstimatedDamage() == null) missing.add("estimatedDamage");

        if (isBlank(r.getClaimType())) missing.add("claimType");
        if (isBlank(r.getAttachments())) missing.add("attachments");
        if (r.getInitialEstimate() == null) missing.add("initialEstimate");

        return missing;
    }

    private String determineRoute(FnolRequest r, List<String> missingFields) {
        if (!missingFields.isEmpty()) {
            return "MANUAL_REVIEW";
        }

        if ("injury".equalsIgnoreCase(r.getClaimType())) {
            return "SPECIALIST_QUEUE";
        }

        if (containsRiskKeyword(r.getIncidentDescription())) {
            return "INVESTIGATION";
        }

        if (r.getEstimatedDamage() != null && r.getEstimatedDamage() < 25000.0) {
            return "FAST_TRACK";
        }

        return "MANUAL_REVIEW";
    }

    private String buildReasoning(FnolRequest r, List<String> missingFields, String route) {
        List<String> reasons = new ArrayList<>();

        if (!missingFields.isEmpty()) {
            reasons.add("Mandatory fields missing: " + String.join(", ", missingFields));
        }
        if ("injury".equalsIgnoreCase(r.getClaimType())) {
            reasons.add("Claim type is injury, routed to specialist queue.");
        }
        if (containsRiskKeyword(r.getIncidentDescription())) {
            reasons.add("Incident description contains potential fraud keywords.");
        }
        if (r.getEstimatedDamage() != null && r.getEstimatedDamage() < 25000.0) {
            reasons.add("Estimated damage below 25000, eligible for fast-track.");
        }

        if (reasons.isEmpty()) {
            reasons.add("Default routing applied based on available data.");
        }

        return String.join(" ", reasons);
    }

    private boolean containsRiskKeyword(String description) {
        if (description == null) return false;
        String d = description.toLowerCase();
        return d.contains("fraud") || d.contains("inconsistent") || d.contains("staged");
    }

    private boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}
