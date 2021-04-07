package chess.domain.game.state.finished;

import chess.domain.game.ChessGame;
import chess.domain.game.state.Started;
import chess.domain.piece.Position;

public abstract class Finished extends Started {

    public Finished(ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(Position source, Position target) {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void start() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public void end() {
        throw new UnsupportedOperationException(MESSAGE_UNSUPPORTED);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
