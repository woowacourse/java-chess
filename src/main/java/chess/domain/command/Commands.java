package chess.domain.command;

import chess.domain.ChessGame;
import chess.exception.NoSuchCommandException;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private final List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = new ArrayList<>(commands);
    }

    public String execute(ChessGame chessGame, String input) {
        return commands.stream()
                .filter(command -> command.isSameCommand(input.toLowerCase()))
                .findAny()
                .orElseThrow(NoSuchCommandException::new)
                .run(chessGame, input);
    }
}
