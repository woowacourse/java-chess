package chess.dto;

import chess.controller.command.Command;

public class ContinueCommandDto {
    private String continueCommand;

    public ContinueCommandDto(String continueCommand) {
        Command.validateContinueCommand(continueCommand);
        this.continueCommand = continueCommand;
    }

    public String get() {
        return continueCommand;
    }
}