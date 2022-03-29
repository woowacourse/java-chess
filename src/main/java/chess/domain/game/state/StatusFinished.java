package chess.domain.game.state;

import chess.domain.game.ChessGame;

public final class StatusFinished extends Finished {
    public StatusFinished(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public boolean isStatusFinished() {
        return true;
    }
}
