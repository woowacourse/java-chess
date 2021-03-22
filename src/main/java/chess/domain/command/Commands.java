package chess.domain.command;

import chess.domain.ChessGame;
import chess.exception.GameIsNotStartException;
import chess.exception.NoSuchCommandException;

import java.util.ArrayList;
import java.util.List;

public class Commands {
    private final List<Command> commands;

    public Commands(List<Command> commands) {
        this.commands = new ArrayList<>(commands);
    }

    public String execute(ChessGame chessGame, CommandInput commandInput) throws GameIsNotStartException {
        return commands.stream()
                .filter(command -> command.isSameCommand(commandInput))
                .findAny()
                .orElseThrow(NoSuchCommandException::new)
                .run(chessGame, commandInput);
    }
}
