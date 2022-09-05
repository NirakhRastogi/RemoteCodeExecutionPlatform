package com.rce.remotecodeexecution.executor;

import com.rce.remotecodeexecution.models.CommandOutput;
import com.rce.remotecodeexecution.models.PLanguage;
import com.rce.remotecodeexecution.models.Script;
import com.rce.remotecodeexecution.util.ShellCommandRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class Java8Executor extends Executor {

    public Java8Executor() {
        super(PLanguage.JAVA8);
    }

    @Override
    public List<CommandOutput> runWindowsCommand(Script script) {

        log.info("Executing program on executor supporting language={} and OS={}", this.supportedLanguage, script.getOperatingSystem());

        String mountPath = "/home/code";
        String containerName = getContainerName(script);
        String imageName = getImageName(script);
        String userDir = getUserDirectory(script);
        String fileName = getFileName(script);

        return ShellCommandRunner.runCommands("docker run -v " + userDir + ":" + mountPath + " --env mountPath=" + mountPath
                + " --env fileName=" + fileName + " --name " + containerName + " " + imageName, "docker rm -f " + getContainerName(script));
    }

    @Override
    public List<CommandOutput> runLinuxCommand(Script script) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<CommandOutput> runMacCommand(Script script) {
        // TODO Auto-generated method stub
        return null;
    }

    private String getFileName(Script script) {
        return script.getFileName();
    }

    private String getUserDirectory(Script script) {
        return getCodeDirectory() + script.getUserId() + "/" + script.getProgramId();
    }

    private String getImageName(Script script) {
        return "rce-java-8:1.2";
    }

    private String getContainerName(Script script) {
        return script.getProgramId() + "-" + script.getUserId() + "-" + script.getScriptId();
    }

}
