package com.rce.remotecodeexecution.executor;

import com.rce.remotecodeexecution.config.DirectoryConfig;
import com.rce.remotecodeexecution.constants.Constants;
import com.rce.remotecodeexecution.models.CommandOutput;
import com.rce.remotecodeexecution.models.PLanguage;
import com.rce.remotecodeexecution.models.Script;
import com.rce.remotecodeexecution.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public abstract class Executor {

    public PLanguage supportedLanguage;

    @Autowired
    private DirectoryConfig directoryConfig;

    public Executor(PLanguage supportedLanguage) {
        this.supportedLanguage = supportedLanguage;
    }

    public abstract List<CommandOutput> runWindowsCommand(Script script);

    public abstract List<CommandOutput> runLinuxCommand(Script script);

    public abstract List<CommandOutput> runMacCommand(Script script);

    public List<CommandOutput> execute(Script script) {

        validateScript(script);

        switch (script.getOperatingSystem()) {
            case WIN:
                return runWindowsCommand(script);
            default:
                throw new RuntimeException("Unsupported OS, " + script.getOperatingSystem());
        }
    }

    protected String getCodeDirectory() {
        return directoryConfig.getCode();
    }

    protected String getProgramDirectory() {
        return directoryConfig.getProgram();
    }

    private void validateScript(Script script) {
        if (!supportedLanguage.equals(script.getPLanguage())) {
            throw new RuntimeException("Unsupported language, " + script.getPLanguage());
        }

        String codeDirectory = getCodeDirectory();
        String programDirectory = getProgramDirectory();
        String fileName = script.getFileName();
        String programId = script.getProgramId();
        String userId = script.getUserId();

        FileUtil.validateIfFileExists(codeDirectory + userId + Constants.FILE_PATH_SEPERATOR + programId + Constants.FILE_PATH_SEPERATOR + fileName + this.supportedLanguage.getExtension());
        FileUtil.validateIfFileExists(programDirectory + programId + Constants.FILE_PATH_SEPERATOR + Constants.INPUT_TXT);
        FileUtil.validateIfFileExists(programDirectory + programId + Constants.FILE_PATH_SEPERATOR + Constants.EXPECTED_OUTPUT_TXT);
    }
}
