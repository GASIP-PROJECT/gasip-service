package com.example.gasip.professor.dto;

import com.example.gasip.professor.model.Professor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfessorResponse {

    private Long profId;
    private String profName;
    private Long majorId;
    private String majorName;

    public static ProfessorResponse fromEntity(Professor professor) {
        return ProfessorResponse.builder()
                .profId(professor.getProfId())
                .majorId(professor.getMajor().getMajorId())
                .majorName(professor.getMajor().getMajorName())
                .profName(professor.getProfName())
                .build();
    }
}
