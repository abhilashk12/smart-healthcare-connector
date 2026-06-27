package com.healthcare.backend.ai.prompt;

import com.healthcare.backend.authorization.entity.AuthorizationRequest;
import org.springframework.stereotype.Component;

@Component
public class HealthcarePromptBuilder {

    public String buildPrompt(AuthorizationRequest request) {

        return """
You are an experienced healthcare insurance authorization reviewer.

Review the authorization request below and determine whether it is complete.

Authorization Request
---------------------
Patient
- Name: %s %s
- Gender: %s
- Date of Birth: %s
- Insurance Number: %s

Provider
- Name: %s

Payer
- Name: %s

Medical Information
- Diagnosis: %s
- Procedure Code: %s
- Doctor Notes: %s

Instructions
------------
1. Diagnosis, Procedure Code, and Doctor Notes are mandatory.
2. If any mandatory field is missing or blank, overallStatus must be "INCOMPLETE".
3. If all mandatory fields are present but could be improved, overallStatus should be "REVIEW_REQUIRED".
4. If everything looks good, overallStatus should be "READY".
5. confidence must be an integer between 1 and 100.
6. summary should be one short sentence.
7. suggestions must always be an array.
8. If there are no suggestions, return an empty array.

Return ONLY this JSON object:

{
  "summary": "string",
  "confidence": 95,
  "overallStatus": "READY",
  "suggestions": [
    "Suggestion 1",
    "Suggestion 2"
  ]
}

IMPORTANT:
- Return raw JSON only.
- Do not use markdown.
- Do not use ```json.
- Do not include comments.
- Do not include explanations.
- Do not return any text before or after the JSON.
- The response must be directly parseable by Jackson ObjectMapper.
"""
                .formatted(
                        request.getPatient().getFirstName(),
                        request.getPatient().getLastName(),
                        request.getPatient().getGender(),
                        request.getPatient().getDateOfBirth(),
                        request.getPatient().getInsuranceNumber(),
                        request.getProvider().getFullName(),
                        request.getPayer().getFullName(),
                        request.getDiagnosis(),
                        request.getProcedureCode(),
                        request.getDoctorNotes()
                );

    }

}