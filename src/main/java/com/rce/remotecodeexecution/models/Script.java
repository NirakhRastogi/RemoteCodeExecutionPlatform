package com.rce.remotecodeexecution.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Script {

    private UUID scriptId;
    private String programId;
    private String userId;
    private String fileName;
    private List<Permisson> permissons;
    private OperatingSystem operatingSystem;
    private PLanguage pLanguage;

}
