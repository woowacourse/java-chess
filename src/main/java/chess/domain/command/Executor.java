package chess.domain.command;

import chess.domain.manager.ChessManager;

public abstract class Executor implements Command {

    protected final ChessManager chessManager;

    public Executor(ChessManager chessManager) {
        this.chessManager = chessManager;
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public boolean isMove() {
        return false;
    }

    @Override
    public boolean isStatus() {
        return false;
    }

    @Override
    public boolean isShow() {
        return false;
    }

    @Override
    public boolean isRestart() {
        return false;
    }
}
