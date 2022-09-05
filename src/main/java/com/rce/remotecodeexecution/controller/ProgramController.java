package com.rce.remotecodeexecution.controller;

import com.rce.remotecodeexecution.models.PLanguage;
import com.rce.remotecodeexecution.models.ProgramInput;
import com.rce.remotecodeexecution.models.ProgramResult;
import com.rce.remotecodeexecution.service.ProgramService;
import lombok.RequiredArgsConstructor;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProgramController {

    private final ProgramService programService;

    @PostMapping("/v1/program")
    public ProgramResult submitProgram(
            @RequestBody ProgramInput input,
            @RequestHeader MultiValueMap<String, String> headers) {

        input.setPLanguage(PLanguage.fromText(input.getLanguage()));
        return this.programService.submitProgram(input, headers);

    }

}
