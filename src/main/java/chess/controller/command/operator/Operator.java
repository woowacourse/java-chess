package chess.controller.command.operator;

import chess.controller.ChessController;
import chess.domain.ChessGame;

import java.util.List;

public abstract class Operator {
    protected final ChessController chessController;
    protected final ChessGame chessGame;

    public Operator(ChessController chessController, ChessGame chessGame) {
        this.chessController = chessController;
        this.chessGame = chessGame;
    }

    abstract public boolean operate(List<String> command);
}
