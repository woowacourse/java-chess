package chess.domain.piece.kind;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public class Queen extends Piece {
    private static final int QUEEN_SCORE = 9;
    private static final String QUEEN_NAME = "q";

    public Queen(Color color, Point point) {
        super(QUEEN_NAME, color, point);
    }

    @Override
    public Direction direction(Piece target) {
        return Direction.findDirection(this.point, target.point);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }

    @Override
    public boolean isEmptyPiece() {
        return false;
    }

    @Override
    public boolean isKing() {
        return false;
    }

    @Override
    public boolean isPawn() {
        return false;
    }

}
