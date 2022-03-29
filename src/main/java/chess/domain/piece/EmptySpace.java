package chess.domain.piece;

import chess.domain.board.Position;

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
        public Direction findValidDirection(Position current, Position target) {
            throw new UnsupportedOperationException();
        }
    }

}
