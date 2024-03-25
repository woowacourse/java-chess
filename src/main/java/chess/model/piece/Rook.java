package chess.model.piece;

import chess.model.position.Movement;

public class Rook extends Piece {
    private static final Piece BLACK_ROOK = new Rook(Color.BLACK);
    private static final Piece WHITE_ROOK = new Rook(Color.WHITE);

    private Rook(Color color) {
        super(color);
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_ROOK;
        }
        return WHITE_ROOK;
    }

    @Override
    public boolean canMove(Movement movement, Piece piece) {
        validateTargetColor(piece);
        return movement.isSameFileOrRank();
    }
}
