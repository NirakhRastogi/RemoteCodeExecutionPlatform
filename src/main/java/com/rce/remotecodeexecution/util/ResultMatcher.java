package com.rce.remotecodeexecution.util;

import com.rce.remotecodeexecution.models.CommandOutput;
import com.rce.remotecodeexecution.models.ProgramResult;

import java.util.Objects;

public class ResultMatcher {

    public static ProgramResult matchResult(CommandOutput commandOutput, String expectedResultPath) {

        if (commandOutput.getStatus() == 2 && Objects.nonNull(commandOutput.getError())) {
            return ProgramResult.builder()
                    .status(-1)
                    .output(commandOutput.getOutput() +"\n" +  commandOutput.getError())
                    .build();
        }

        String expectedResult = FileUtil.readFile(expectedResultPath);

        if (expectedResult.trim().equals(commandOutput.getOutput().trim())) {
            return ProgramResult
                    .builder()
                    .status(1)
                    .output(commandOutput.getOutput())
                    .build();
        }

        return ProgramResult
                .builder()
                .status(0)
                .output(commandOutput.getOutput())
                .build();

    }

}
