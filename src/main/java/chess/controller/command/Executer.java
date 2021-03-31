package chess.controller.command;

import chess.controller.ChessController;

public abstract class Executer implements Command {

    protected final ChessController chessController;

    public Executer(final ChessController chessController) {
        this.chessController = chessController;
    }

    @Override
    public boolean isStart() {
        return false;
    }

    @Override
    public boolean isEnd() {
        return false;
    }
}
