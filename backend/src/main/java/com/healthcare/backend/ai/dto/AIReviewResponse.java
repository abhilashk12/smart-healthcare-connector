package com.healthcare.backend.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AIReviewResponse {

    private String summary;

    private Integer confidence;

    private String overallStatus;

    private List<String> suggestions;

}