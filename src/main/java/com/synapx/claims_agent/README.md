# Autonomous Insurance Claims Processing Agent (Lite)

This project is a lightweight FNOL (First Notice of Loss) claims-processing agent. It accepts structured FNOL data as JSON, extracts and echoes key fields, identifies missing mandatory information, applies simple routing rules, and returns a recommended workflow route along with human-readable reasoning.The goal is to demonstrate clear logic, clean structure, and explainable decisions for insurance claim triage.

## Tech stack

- Java 17+
- Spring Boot 4.x (Spring Web)
- REST API: `POST /claims/process`

## Fields handled

The agent expects a JSON body that maps to the `FnolRequest` model with these groups of fields:

- Policy information  
  - `policyNumber`  
  - `policyholderName`  
  - `policyEffectiveFrom`  
  - `policyEffectiveTo`
- Incident information  
  - `incidentDate`  
  - `incidentTime`  
  - `incidentLocation`  
  - `incidentDescription`
- Involved parties  
  - `claimantName`  
  - `thirdPartyNames`  
  - `contactDetails`
- Asset details  
  - `assetType`  
  - `assetId`  
  - `estimatedDamage`
- Other mandatory fields  
  - `claimType`  
  - `attachments`  
  - `initialEstimate`

## Routing rules implemented

The `ClaimProcessingService` applies the business rules from the assessment brief to decide the route:

- If **any mandatory field is missing** → `MANUAL_REVIEW`  
- Else if **claimType = "injury"** → `SPECIALIST_QUEUE`  
- Else if **description contains** keywords `"fraud"`, `"inconsistent"`, or `"staged"` → `INVESTIGATION` (investigation flag)  
- Else if **estimatedDamage < 25000** → `FAST_TRACK`  
- Otherwise → `MANUAL_REVIEW`

For every request, the response also includes a `reasoning` string that explains which rules fired (missing fields, injury, fraud keywords, low damage).

## How to run

From the project root: .\mvnw.cmd spring-boot:run



By default the application runs on port `8081` (configured via `application.properties`). The main API endpoint is:

- **URL:** `http://localhost:8081/claims/process`  
- **Method:** `POST`  
- **Headers:** `Content-Type: application/json`  

Sample request JSON files are available under the `samples/` directory, for example:

- `samples/sample-fast-track.json`  
- `samples/sample-missing-fields-manual-review.json`  
- `samples/sample-injury-specialist.json`  
- `samples/sample-fraud-investigation.json`  
- `samples/sample-combined-injury-fraud.json`

## Example request and response

Example request (`sample-fast-track.json`):

{
"policyNumber": "POL123",
"policyholderName": "John Doe",
"policyEffectiveFrom": "2025-01-01",
"policyEffectiveTo": "2025-12-31",
"incidentDate": "2025-03-10",
"incidentTime": "10:30",
"incidentLocation": "Hyderabad",
"incidentDescription": "Minor rear-end collision in traffic.",
"claimantName": "John Doe",
"thirdPartyNames": "Jane Smith",
"contactDetails": "john@example.com, +91-9999999999",
"assetType": "Car",
"assetId": "KA01AB1234",
"estimatedDamage": 15000,
"claimType": "motor",
"attachments": "photo1.jpg,photo2.jpg",
"initialEstimate": 15000
}

text

Example response:

{
"extractedFields": {
"policyNumber": "POL123",
"policyholderName": "John Doe",
"policyEffectiveFrom": "2025-01-01",
"policyEffectiveTo": "2025-12-31",
"incidentDate": "2025-03-10",
"incidentTime": "10:30",
"incidentLocation": "Hyderabad",
"incidentDescription": "Minor rear-end collision in traffic.",
"claimantName": "John Doe",
"thirdPartyNames": "Jane Smith",
"contactDetails": "john@example.com, +91-9999999999",
"assetType": "Car",
"assetId": "KA01AB1234",
"estimatedDamage": 15000.0,
"claimType": "motor",
"attachments": "photo1.jpg,photo2.jpg",
"initialEstimate": 15000.0
},
"missingFields": [],
"recommendedRoute": "FAST_TRACK",
"reasoning": "Estimated damage below 25000, eligible for fast-track."
}

