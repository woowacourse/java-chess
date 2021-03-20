package chess.domain.command;

import chess.domain.ChessGame;
import chess.exception.NoSuchCommandException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Commands {
    private final List<Command> commands;

    public Commands() {
        commands = Arrays.asList(
                new EndOnCommand(),
                new MoveOnCommand(),
                new StartOnCommand(),
                new StatusOnCommand()
        );
    }

    public String execute(String input, ChessGame chessGame) {
        return commands.stream()
                .filter(command -> command.isSameCommand(input.toLowerCase()))
                .findAny()
                .orElseThrow(NoSuchCommandException::new)
                .run(input, chessGame);
    }
}
