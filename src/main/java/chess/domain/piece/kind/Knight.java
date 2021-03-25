package chess.domain.piece.kind;

import chess.domain.Point;
import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Knight extends Piece {
    private static final double KNIGHT_SCORE = 2.5;
    private static final int POSSIBLE_DISTANCE_OF_KNIGHT = 5;
    private static final String KNIGHT_NAME = "n";

    public Knight(Color color) {
        super(KNIGHT_NAME, color);
    }

    @Override
    public void validateMovableRoute(Point source, Point target, Piece targetPiece) {
        int distance = source.calculateDistance(target);

        if (distance != POSSIBLE_DISTANCE_OF_KNIGHT) {
            throw new IllegalArgumentException("기물이 이동할 수 없는 경로입니다.");
        }
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }
}
