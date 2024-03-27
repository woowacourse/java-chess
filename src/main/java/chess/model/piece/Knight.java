package chess.model.piece;

import chess.model.position.Movement;

public class Knight extends Piece {
    private static final int LONG_MOVE_DISTANCE = 2;
    private static final int SHORT_MOVE_DISTANCE = 1;
    private static final Piece BLACK_KING = new Knight(Color.BLACK);
    private static final Piece WHITE_KING = new Knight(Color.WHITE);

    private Knight(Color color) {
        super(color, Type.KNIGHT);
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_KING;
        }
        return WHITE_KING;
    }

    @Override
    public boolean isValid(Movement movement, Piece destination) {
        validateDestinationColor(destination);
        int fileDistance = movement.getFileDistance();
        int rankDistance = movement.getRankDistance();
        return (fileDistance == LONG_MOVE_DISTANCE && rankDistance == SHORT_MOVE_DISTANCE)
                || (fileDistance == SHORT_MOVE_DISTANCE && rankDistance == LONG_MOVE_DISTANCE);
    }
}
