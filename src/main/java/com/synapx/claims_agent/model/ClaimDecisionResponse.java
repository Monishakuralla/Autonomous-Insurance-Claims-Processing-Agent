package com.synapx.claims_agent.model;

import java.util.List;
import java.util.Map;

public class ClaimDecisionResponse {

    private Map<String, Object> extractedFields;
    private List<String> missingFields;
    private String recommendedRoute;
    private String reasoning;

    public ClaimDecisionResponse() {
    }

    public ClaimDecisionResponse(Map<String, Object> extractedFields,
                                 List<String> missingFields,
                                 String recommendedRoute,
                                 String reasoning) {
        this.extractedFields = extractedFields;
        this.missingFields = missingFields;
        this.recommendedRoute = recommendedRoute;
        this.reasoning = reasoning;
    }

    public Map<String, Object> getExtractedFields() {
        return extractedFields;
    }

    public void setExtractedFields(Map<String, Object> extractedFields) {
        this.extractedFields = extractedFields;
    }

    public List<String> getMissingFields() {
        return missingFields;
    }

    public void setMissingFields(List<String> missingFields) {
        this.missingFields = missingFields;
    }

    public String getRecommendedRoute() {
        return recommendedRoute;
    }

    public void setRecommendedRoute(String recommendedRoute) {
        this.recommendedRoute = recommendedRoute;
    }

    public String getReasoning() {
        return reasoning;
    }

    public void setReasoning(String reasoning) {
        this.reasoning = reasoning;
    }
}
