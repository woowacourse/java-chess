package chess.domain.piece;

import chess.domain.Position;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "K";
    private static final int KNIGHT_MOVABLE_DISTANCE = 3;

    public Knight(Color color, Position position) {
        super(color, KNIGHT_NAME, position);
    }

    public void move(Position position) {
        if (!isPossibleToMove(position)) {
            throw new IllegalStateException("나이트는 L자 형태로만 움직일 수 있습니다.");
        }
    }

    private boolean isPossibleToMove(Position position) {
        return position.equalsColumnOrRow(position) && position.calculateDistance(position) == KNIGHT_MOVABLE_DISTANCE;
    }
}
