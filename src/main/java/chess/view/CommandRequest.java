package chess.view;

import chess.controller.Command;

import java.util.List;

public class CommandRequest {

    public static final int COMMAND_INDEX_IN_COMMANDLINE =0;
    public static final int START_SOURCE_INDEX_IN_COMMANDLINE = 1;
    public static final int TARGET_SOURCE_INDEX_IN_COMMANDLINE = 2;

    private final List<String> commandLine;

    public CommandRequest(final List<String> commandLine) {
        this.commandLine = commandLine;
    }

    public Command getCommand() {
        return Command.findCommandByString(commandLine.get(COMMAND_INDEX_IN_COMMANDLINE));
    }

    public List<String> getCommandLine() {
        return commandLine;
    }

    public String getStartPosition() {
        return commandLine.get(START_SOURCE_INDEX_IN_COMMANDLINE);
    }

    public String getEndPosition() {
        return commandLine.get(TARGET_SOURCE_INDEX_IN_COMMANDLINE);
    }
}
