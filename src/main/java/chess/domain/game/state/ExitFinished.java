package chess.domain.game.state;

import chess.domain.game.ChessGame;

public final class ExitFinished extends Finished {
    public ExitFinished(ChessGame chessGame) {
        super(chessGame);
    }
}
