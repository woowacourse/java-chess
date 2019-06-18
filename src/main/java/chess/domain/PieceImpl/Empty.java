package chess.domain.PieceImpl;

import chess.domain.Position;

public class Empty extends AbstractPiece {
    private static Empty INSTANCE = new Empty();

    private Empty() {

    }

    public static Empty getInstance() {
        return INSTANCE;
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return false;
    }
}
