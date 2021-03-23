package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;
    private static final String QUEEN_NAME = "q";

    public Queen(Color color, Point point) {
        super(QUEEN_NAME, color, point);
    }

    @Override
    public void validateMovable(Direction direction, Piece targetPiece) {
        int initialRowDifference = targetPiece.point.subtractRow(this.point);
        int initialColumnDifference = targetPiece.point.subtractColumn(this.point);

        if ((Math.abs(initialRowDifference) != Math.abs(initialColumnDifference))
                && initialRowDifference != 0 && initialColumnDifference != 0) {
            throw new IllegalArgumentException("기물이 움직일 수 없는 방향입니다.");
        }
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }
}
