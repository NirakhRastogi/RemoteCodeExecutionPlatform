package com.rce.remotecodeexecution.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommandOutput {

    private String output;
    private String error;
    private int status;

}
