package chess.domain.piece.kind;

import chess.domain.board.Point;
import chess.domain.piece.Color;

public final class Knight extends Piece {
    public static final String KNIGHT_NAME = "n";
    private static final double KNIGHT_SCORE = 2.5;
    private static final int POSSIBLE_DISTANCE_OF_KNIGHT = 5;

    public Knight(Color color) {
        super(KNIGHT_NAME, color);
    }

    @Override
    public void checkCorrectDistance(Point sourcePoint, Point targetPoint, Piece target) {
        int distance = sourcePoint.calculateDistance(targetPoint);
        if (distance != POSSIBLE_DISTANCE_OF_KNIGHT) {
            throw new IllegalArgumentException(IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
        }
    }

    @Override
    public double score() {
        return KNIGHT_SCORE;
    }

    public boolean isKnight() {
        return true;
    }
}
