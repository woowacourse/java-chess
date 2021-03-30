package chess.domain.piece.kind;

import chess.domain.board.Point;
import chess.domain.piece.Color;

public final class King extends Piece {
    private static final int KING_SCORE = 0;
    private static final String KING_NAME = "k";

    public King(Color color) {
        super(KING_NAME, color);
        this.image = makeImage();
    }

    private String makeImage() {
        if(this.color.isSameAs(Color.BLACK)) {
            return "black_king.png";
        }
        return "white_king.png";
    }

    @Override
    public void checkCorrectDistance(Point sourcePoint, Point targetPoint, Piece target) {
        int distance = sourcePoint.calculateDistance(targetPoint);

        if (distance != MOVE_STRAIGHT_ONE_SQUARE && distance != MOVE_DIAGONAL_ONE_SQUARE) {
            throw new IllegalArgumentException(IMPOSSIBLE_ROUTE_ERROR_MESSAGE);
        }
    }

    @Override
    public double score() {
        return KING_SCORE;
    }

    @Override
    public boolean isKing() {
        return true;
    }
}
