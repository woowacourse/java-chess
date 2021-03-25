package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;
    private static final String QUEEN_NAME = "q";

    public Queen(Color color) {
        super(QUEEN_NAME, color);
    }

    @Override
    public void validateMovableRoute(Point source, Point target, Piece targetPiece) {
        int initialRowDifference = target.subtractRow(source);
        int initialColumnDifference = target.subtractColumn(source);

        if ((Math.abs(initialRowDifference) != Math.abs(initialColumnDifference))
                && initialRowDifference != 0 && initialColumnDifference != 0) {
            throw new IllegalArgumentException("기물이 움직일 수 없는 방향입니다.");
        }
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }
}
