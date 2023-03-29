package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.ChessGame;

import java.sql.SQLException;

public abstract class Command {
    protected final ChessController chessController;

    public Command(final ChessController chessController) {
        this.chessController = chessController;
    }

    abstract public boolean operate(final ChessGame chessGame) throws SQLException;
}
