package chess.domain.game;

import chess.domain.piece.Color;
import chess.domain.piece.Position;

import java.util.Optional;

public class End extends Finished {

    public End(final ChessGame chessGame) {
        super(chessGame);
    }

    @Override
    public void move(final Position source, final Position target) {
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