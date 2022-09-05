package com.rce.remotecodeexecution.models;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PLanguage {

    JAVA8("JAVA8", ".java"),
    JAVA11("JAVA11", ".java"),
    JAVA12("JAVA12", ".java"),
    PYTHON2("PYTHON2", ".py"),
    PYTHON3("PYTHON3", ".py");

    private final String language;
    private final String extension;

    PLanguage(String language, String extension) {
        this.language = language;
        this.extension = extension;
    }

    @JsonCreator
    public static PLanguage fromText(String language) {
        for (PLanguage r : PLanguage.values()) {
            if (r.language.equals(language)) {
                return r;
            }
        }
        throw new IllegalArgumentException();
    }

    public String getExtension() {
        return extension;
    }

    public String getLanguage() {
        return language;
    }

    public String valueOf() {
        return this.language;
    }

    @Override
    public String toString() {
        return this.language;
    }
}
