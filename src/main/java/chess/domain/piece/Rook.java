package chess.domain.piece;

import chess.domain.board.Point;
import chess.domain.piece.move.StraightDirection;

import java.util.Map;

public class Rook extends Piece {

    public Rook(Color color) {
        super(color, PieceType.ROOK);
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
        return direction;
    }

    private void validateDirection(StraightDirection direction) {
        if (!direction.isCross()) {
            throw new IllegalArgumentException("[ERROR] 룩은 상하좌우로만 이동할 수 있습니다.");
        }
    }

    private void validateEmpty(Map<Point, Piece> pointPieces, Point point) {
        if (!isEmptyPoint(pointPieces, point)) {
            throw new IllegalArgumentException("[ERROR] 이동 과정 중에 장애물이 있습니다.");
        }
    }
}

