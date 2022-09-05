package com.rce.remotecodeexecution.service.impl;

import com.rce.remotecodeexecution.config.DirectoryConfig;
import com.rce.remotecodeexecution.constants.Constants;
import com.rce.remotecodeexecution.executor.ExecutorFactory;
import com.rce.remotecodeexecution.models.*;
import com.rce.remotecodeexecution.service.ProgramService;
import com.rce.remotecodeexecution.util.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProgramServiceImpl implements ProgramService {

    private final DirectoryConfig directoryConfig;

    private final ExecutorFactory executorFactory;

    @Override
    public ProgramResult submitProgram(ProgramInput input, MultiValueMap<String, String> headers) {
        //Extract headers
        String userId = HeaderUtil.getHeaderValue(headers, Constants.X_USER_ID);
        String programId = HeaderUtil.getHeaderValue(headers, Constants.X_PROGRAM_ID);
        String fileName = Constants.PROGRAM_FILE_NAME;

        //Copy input to user program directory
        FileUtil.copyFile(directoryConfig.getProgram() + programId + Constants.FILE_PATH_SEPERATOR + Constants.INPUT_TXT,
                directoryConfig.getCode() + userId + Constants.FILE_PATH_SEPERATOR + programId,
                Constants.INPUT_TXT);

        //Modify and save code
        String code = CodeUtil.modifyCode(input.getCode(), getTemplatePath(programId, input.getPLanguage()));
        FileUtil.saveToFile(code,
                directoryConfig.getCode() + userId + Constants.FILE_PATH_SEPERATOR + programId,
                fileName + input.getPLanguage().getExtension());

        //Create a script object and execute
        Script script = Script.builder()
                .programId(programId)
                .userId(userId)
                .fileName(fileName)
                .scriptId(UUID.randomUUID())
                .operatingSystem(OSUtil.getCurrentOperationSystem())
                .pLanguage(input.getPLanguage())
                .build();

        List<CommandOutput> commandOutputs = executorFactory.getExecutor(input.getPLanguage()).execute(script);

        return ResultMatcher.matchResult(commandOutputs.get(0), directoryConfig.getProgram() + programId + Constants.FILE_PATH_SEPERATOR + Constants.EXPECTED_OUTPUT_TXT);

    }

    private String getTemplatePath(String programId, PLanguage pLanguage) {
        return directoryConfig.getProgram() + Constants.FILE_PATH_SEPERATOR + programId + Constants.FILE_PATH_SEPERATOR + Constants.TEMPLATE_FILE_NAME + pLanguage.getExtension();
    }
}
