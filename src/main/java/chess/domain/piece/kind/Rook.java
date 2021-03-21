package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.List;
import java.util.Optional;

public class Rook extends Piece {
    private static final int ROOK_SCORE = 5;
    private static final String ROOK_NAME = "r";

    private static final List<Direction> rookDirection = Direction.rookDirection();

    public Rook(Color color, Point point) {
        super(ROOK_NAME, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        Direction direction = Direction.findDirection(this.point, target.point);
        if (!rookDirection.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
        return Optional.of(direction);
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return ROOK_SCORE;
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
