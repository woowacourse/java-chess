package chess.domain.piece.kind;

import static chess.domain.piece.Direction.*;

import java.util.Arrays;
import java.util.List;

import chess.domain.board.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Rook extends Piece {
    private static final int ROOK_SCORE = 5;
    private static final String ROOK_NAME = "r";

    private static final List<Direction> rookDirection = Arrays.asList(NORTH, SOUTH, EAST, WEST);

    public Rook(Color color) {
        super(ROOK_NAME, color);
    }

    @Override
    public void checkCorrectDistance(Point sourcePoint, Point targetPoint, Piece target) {
    }

    @Override
    public void checkCorrectDirection(Direction direction) {
        if (!rookDirection.contains(direction)) {
            throw new IllegalArgumentException("이동할 수 없는 방향입니다.");
        }
    }

    @Override
    public double score() {
        return ROOK_SCORE;
    }
}
