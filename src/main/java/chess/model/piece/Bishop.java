package chess.model.piece;

import chess.model.position.Movement;

public class Bishop extends Piece {
    private static final Piece BLACK_BISHOP = new Bishop(Color.BLACK);
    private static final Piece WHITE_BISHOP = new Bishop(Color.WHITE);

    private Bishop(Color color) {
        super(color, Type.BISHOP);
    }

    public static Piece from(Color color) {
        if (Color.BLACK == color) {
            return BLACK_BISHOP;
        }
        return WHITE_BISHOP;
    }

    @Override
    public boolean isValid(Movement movement, Piece destination) {
        validateDestinationColor(destination);
        return movement.isDiagonal();
    }
}
