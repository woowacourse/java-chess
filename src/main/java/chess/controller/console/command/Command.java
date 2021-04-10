package chess.controller.console.command;

import chess.domain.ChessGame;
import chess.domain.board.position.Position;

public abstract class Command {
    private static final String SEPARATOR_OF_PARAMETERS = " ";
    private static final int INDEX_OF_SOURCE = 1;
    private static final int INDEX_OF_TARGET = 2;

    private final String[] parameters;

    Command(final String line) {
        parameters = line.split(SEPARATOR_OF_PARAMETERS);
    }

    public abstract Command read(final String line);

    public abstract void execute(final ChessGame chessGame);

    public Position source() {
        return new Position(parameters[INDEX_OF_SOURCE]);
    }

    public Position target() {
        return new Position(parameters[INDEX_OF_TARGET]);
    }
}
