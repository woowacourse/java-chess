package chess.domain.game.state;

import chess.domain.game.ChessGame;

public final class StatusEnd extends End {
    public StatusEnd(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public boolean isStatus() {
        return true;
    }
}
