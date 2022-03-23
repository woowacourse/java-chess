package chess.domain.piece;

import chess.domain.Position;

public final class Knight extends Piece {

    private static final String KNIGHT_NAME = "K";
    private static final int KNIGHT_MOVABLE_DISTANCE = 3;

    public Knight(Color color, Position position) {
        super(color, KNIGHT_NAME, position);
    }

    public Knight(Knight knight, Position position) {
        super(knight, position);
    }

    public Knight move(Position position) {
        if (!isPossibleToMove(position)) {
            throw new IllegalStateException("나이트는 L자 형태로만 움직일 수 있습니다.");
        }
        return new Knight(this, position);
    }

    private boolean isPossibleToMove(Position position) {
        return !position.equalsColumnOrRow(getPosition())
                && position.calculateDistance(getPosition()) == KNIGHT_MOVABLE_DISTANCE;
    }
}
