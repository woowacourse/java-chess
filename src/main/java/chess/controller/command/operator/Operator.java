package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.domain.ChessGame;

import java.sql.SQLException;
import java.util.List;

public abstract class Operator {
    protected final ChessController chessController;
    protected final ChessGame chessGame;

    public Operator(final ChessController chessController, final ChessGame chessGame) {
        this.chessController = chessController;
        this.chessGame = chessGame;
    }

    abstract public boolean operate(final List<String> command) throws SQLException;
}
