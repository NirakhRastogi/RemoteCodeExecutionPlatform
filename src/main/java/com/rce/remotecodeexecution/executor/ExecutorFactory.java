package com.rce.remotecodeexecution.executor;

import com.rce.remotecodeexecution.models.PLanguage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExecutorFactory {

    @Autowired
    private Java8Executor java8Executor;

    public Executor getExecutor(PLanguage pLanguage) {
        switch (pLanguage) {
            case JAVA8 -> {
                return java8Executor;
            }
            default -> {
                throw new RuntimeException("Unsupported language -> " + pLanguage.getLanguage());
            }
        }
    }
}
