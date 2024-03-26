package chess.model.piece;

import chess.model.position.Movement;

public class Queen extends Piece {
    private static final Piece BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Piece WHITE_QUEEN = new Queen(Color.WHITE);

    private Queen(Color color) {
        super(color);
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_QUEEN;
        }
        return WHITE_QUEEN;
    }

    @Override
    public boolean canMove(Movement movement, Piece target) {
        validateTargetColor(target);
        return movement.isDiagonal() || movement.isSameFileOrRank();
    }
}
