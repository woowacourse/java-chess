package chess.domain.game.state.running;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Started;

public abstract class Running extends Started {

    public static final String RUNNING = "running";

    public Running(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public String getStatus() {
        return RUNNING;
    }
}
