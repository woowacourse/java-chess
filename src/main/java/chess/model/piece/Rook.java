package chess.model.piece;

import chess.model.position.Movement;

public final class Rook extends SlidingPiece {
    private static final Piece BLACK_ROOK = new Rook(Color.BLACK);
    private static final Piece WHITE_ROOK = new Rook(Color.WHITE);

    private Rook(Color color) {
        super(color, Type.ROOK);
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_ROOK;
        }
        return WHITE_ROOK;
    }

    @Override
    public boolean isValid(Movement movement, Piece destination) {
        validateDestinationColor(destination);
        return movement.isSameFileOrRank();
    }
}
