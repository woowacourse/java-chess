package chess.domain.game.state.idle;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Started;

public abstract class Idle extends Started {

    public Idle(final ChessGame chessGame) {
        super(chessGame);
    }

}
