package chess.domain.piece;

import chess.domain.board.Point;
import chess.domain.piece.move.StraightDirection;

import java.util.Map;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color, PieceType.BISHOP);
    }

    @Override
    public void move(Map<Point, Piece> pointPieces, Point from, Point to) {
        StraightDirection direction = findDirection(from, to);

        Point next = direction.nextOf(from);
        while (!next.equals(to)) {
            validateEmpty(pointPieces, next);
            next = direction.nextOf(next);
        }
    }

    private StraightDirection findDirection(Point from, Point to) {
        StraightDirection direction = StraightDirection.find(from, to);
        validateDirection(direction);
        validateDiagonal(from, to);
        return direction;
    }

    private void validateDirection(StraightDirection direction) {
        if (direction.isCross()) {
            throw new IllegalArgumentException("[ERROR] 비숌은 대각선으로만 이동할 수 있습니다.");
        }
    }

    private void validateDiagonal(Point from, Point to) {
        if (Math.abs(from.subtractVertical(to)) != Math.abs(from.subtractHorizontal(to))) {
            throw new IllegalArgumentException("[ERROR] 비숌은 대각선으로만 이동할 수 있습니다.");
        }
    }

    private void validateEmpty(Map<Point, Piece> pointPieces, Point point) {
        if (!isEmptyPoint(pointPieces, point)) {
            throw new IllegalArgumentException("[ERROR] 이동 과정 중에 장애물이 있습니다.");
        }
    }
}
