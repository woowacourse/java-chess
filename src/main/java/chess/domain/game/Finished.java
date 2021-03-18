package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

import java.util.Optional;

public abstract class Finished extends Started {
    public Finished(ChessGame chessGame) {
        super(chessGame);
    }

    abstract public Optional<Color> getWinnerColor();

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
