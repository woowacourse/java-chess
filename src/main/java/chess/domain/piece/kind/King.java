package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public class King extends Piece {
    private static final int KING_SCORE = 0;
    private static final String KING_NAME = "k";

    public King(Color color, Point point) {
        super(KING_NAME, color, point);
    }

    @Override
    public void validateMovable(Direction direction, Piece targetPiece) {
        int distance = this.point.calculateDistance(targetPiece.point);

        if (distance != MOVE_STRAIGHT_ONE_SQUARE && distance != MOVE_DIAGONAL_ONE_SQUARE) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 경로입니다.");
        }
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return target;
    }

    @Override
    public double score() {
        return KING_SCORE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
