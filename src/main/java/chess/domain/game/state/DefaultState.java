package chess.domain.game.state;

import chess.domain.game.ChessGame;

public abstract class DefaultState implements State {
    protected final ChessGame chessGame;

    public DefaultState(ChessGame chessGame) {
        this.chessGame = chessGame;
    }

    public boolean isRun() {
        return true;
    }

    public boolean isPlay() {
        return false;
    }

    public boolean isStatus() {
        return false;
    }
}
