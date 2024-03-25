package chess.model.piece;

import chess.model.position.Movement;

public class King extends Piece {
    private static final int MAX_MOVE_DISTANCE = 1;
    private static final Piece BLACK_KING = new King(Color.BLACK);
    private static final Piece WHITE_KING = new King(Color.WHITE);

    private King(Color color) {
        super(color);
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_KING;
        }
        return WHITE_KING;
    }

    @Override
    public boolean canMove(Movement movement, Piece target) {
        validateTargetColor(target);
        int fileDistance = movement.getFileDistance();
        int rankDistance = movement.getRankDistance();
        return fileDistance <= MAX_MOVE_DISTANCE && rankDistance <= MAX_MOVE_DISTANCE;
    }
}
