package com.rce.remotecodeexecution.service;

import com.rce.remotecodeexecution.models.ProgramInput;
import com.rce.remotecodeexecution.models.ProgramResult;
import org.springframework.util.MultiValueMap;

public interface ProgramService {
    ProgramResult submitProgram(ProgramInput input, MultiValueMap<String, String> headers);
}
