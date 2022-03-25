package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Point;
import chess.domain.piece.move.StraightDirection;

public class Bishop extends Piece {

    public Bishop(Color color) {
        super(color);
    }

    @Override
    public void move(Board board, Point from, Point to) {
        StraightDirection direction = findDirection(from, to);

        Point next = from.next(direction);
        while (!next.equals(to)) {
            validateEmpty(board, next);
            next = next.next(direction);
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

    private void validateEmpty(Board board, Point point) {
        if (!board.isEmpty(point)) {
            throw new IllegalArgumentException("[ERROR] 이동 과정 중에 장애물이 있습니다.");
        }
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
