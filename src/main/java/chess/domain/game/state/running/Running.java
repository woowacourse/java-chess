package chess.domain.game.state.running;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Started;

public abstract class Running extends Started {

    public Running(final ChessGame chessGame) {
        super(chessGame);
    }

}
