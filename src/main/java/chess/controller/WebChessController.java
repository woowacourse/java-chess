package chess.controller;

import chess.domain.command.Command;
import chess.domain.command.Commands;

public class WebChessController {

    private final Commands commands;

    public WebChessController(final Commands commands) {
        this.commands = commands;
    }

    public void turn(String input) {
        Command command = commands.getAppropriateCommand(input);
        command.handle(input);
    }

}
