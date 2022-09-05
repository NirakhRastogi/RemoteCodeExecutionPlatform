package com.rce.remotecodeexecution.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "directory")
public class DirectoryConfig {

    private String code;
    private String program;

}
