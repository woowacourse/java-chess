package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

import java.util.Optional;

public class Empty extends Piece {
    private static final int EMPTY_SCORE = 0;
    private static final String EMPTY_PIECE_NAME = ".";

    public Empty(Color color, Point point) {
        super(EMPTY_PIECE_NAME, color, point);
    }

    @Override
    public Optional<Direction> direction(Piece target) {
        throw new IllegalArgumentException("비어 있는 칸입니다.");
    }

    @Override
    public Point moveOneStep(Point target, Direction direction) {
        return this.point.createNextPoint(direction);
    }

    @Override
    public double score() {
        return EMPTY_SCORE;
    }
}
