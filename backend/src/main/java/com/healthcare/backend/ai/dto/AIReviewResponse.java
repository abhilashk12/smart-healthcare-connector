package com.healthcare.backend.ai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class AIReviewResponse {

    private List<String> suggestions;

    private int confidence;

}