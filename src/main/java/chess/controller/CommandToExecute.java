package chess.controller;

import chess.controller.dto.request.CommandRequestDTO;
import chess.controller.type.Command;

public class CommandToExecute {
    private final Command command;
    private final CommandRequestDTO commandRequestDTO;

    public CommandToExecute(Command command, CommandRequestDTO commandRequestDTO) {
        this.command = command;
        this.commandRequestDTO = commandRequestDTO;
    }

    public Command getCommand() {
        return command;
    }

    public CommandRequestDTO getCommandRequestDTO() {
        return commandRequestDTO;
    }
}
