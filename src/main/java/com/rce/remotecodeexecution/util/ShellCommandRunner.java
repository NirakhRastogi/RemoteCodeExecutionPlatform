package com.rce.remotecodeexecution.util;

import com.rce.remotecodeexecution.models.CommandOutput;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ShellCommandRunner {

    public static List<CommandOutput> runCommands(String... commands) {

        List<CommandOutput> commandOutputs = new ArrayList<>();

        for (String command : commands) {
            commandOutputs.add(runCommand(command));
        }

        return commandOutputs;

    }

    public static CommandOutput runCommand(String command) {

        log.info("Running command : ${}", command);

        String _out, _err;
        StringBuilder sb;
        CommandOutput commandOutput = new CommandOutput();
        Process p;

        try {
            p = Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            throw new RuntimeException("Unable to start process.", e);
        }
        BufferedReader outputStream = new BufferedReader(new InputStreamReader(p.getInputStream()));
        BufferedReader errorStream = new BufferedReader(new InputStreamReader(p.getErrorStream()));

        try {
            log.info("Reading output....");
            sb = new StringBuilder();
            while ((_out = outputStream.readLine()) != null) {
                sb.append(_out).append("\n");
            }
            log.info("Reading output completed....");
            if (!sb.isEmpty()) {
                commandOutput.setOutput(sb.toString());
                commandOutput.setStatus(commandOutput.getStatus() + 1);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read output.", e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Unable to close output stream.", e);
            }
        }

        try {
            log.info("Reading error...");
            sb = new StringBuilder();
            while ((_err = errorStream.readLine()) != null) {
                sb.append(_err).append("\n");
            }
            log.info("Reading error completed....");
            if (!sb.isEmpty()) {
                commandOutput.setError(sb.toString());
                commandOutput.setStatus(commandOutput.getStatus() + 2);
            }
        } catch (IOException e) {
            throw new RuntimeException("Unable to read error.", e);
        } finally {
            try {
                errorStream.close();
            } catch (IOException e) {
                throw new RuntimeException("Unable to close error stream.", e);
            }
        }

        return commandOutput;

    }

}
