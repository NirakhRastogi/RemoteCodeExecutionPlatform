package com.rce.remotecodeexecution.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProgramInput {

    private String code;
    private String language;
    private PLanguage pLanguage;

}
