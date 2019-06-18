package chess.domain.PieceImpl;

import chess.domain.Position;

public class Rook extends AbstractPiece {

    private Rook() {

    }

    public static Rook getInstance() {
        return new Rook();
    }

    @Override
    public boolean isValidMove(final Position origin, final Position target) {
        return origin.isLevel(target) || origin.isPerpendicular(target);
    }

}
