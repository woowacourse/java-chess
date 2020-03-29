package chess.controller.dto;

import chess.controller.command.Command;

public class StartCommandDto {
    private String startCommand;

    public StartCommandDto(String startCommand) {
        Command.validateStartCommand(startCommand);
        this.startCommand = startCommand;
    }

    public Command get() {
        return Command.of(startCommand);
    }

    public boolean isStart() {
        return get() == Command.START;
    }
}
