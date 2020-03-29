package chess.controller.dto;

import chess.controller.command.Command;

import java.util.Arrays;
import java.util.List;

public class ContinueCommandDto {
    private static final String SPLITTER = " ";

    private List<String> continueCommand;

    public ContinueCommandDto(String continueCommand) {
        Command.validateContinueCommand(continueCommand);
        this.continueCommand = Arrays.asList(continueCommand.split(SPLITTER));
    }

    public List<String> get() {
        return continueCommand;
    }
}