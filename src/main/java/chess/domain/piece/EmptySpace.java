package chess.domain.piece;

import chess.domain.board.Position;
import java.util.List;

public class EmptySpace {

    private static EmptyPiece emptyPiece;

    private EmptySpace() {
    }

    public static EmptyPiece getInstance() {
        if (emptyPiece == null) {
            synchronized (EmptySpace.class) {
                emptyPiece = new EmptyPiece();
            }
        }
        return emptyPiece;
    }

    private static class EmptyPiece extends Piece {

        private EmptyPiece() {
            super(PieceType.NONE, Color.NONE);
        }

        @Override
        protected Direction findValidDirection(final Position current, final Position target,
                                               final Direction direction) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected List<Direction> getPossibleDirection() {
            throw new UnsupportedOperationException();
        }
    }

}
