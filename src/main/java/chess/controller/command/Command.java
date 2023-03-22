package chess.controller.command;

import chess.controller.ChessController;
import chess.domain.ChessGame;

public abstract class Command {
    protected final ChessController chessController;

    public Command(ChessController chessController) {
        this.chessController = chessController;
    }

    abstract public boolean operate(ChessGame chessGame);
}
