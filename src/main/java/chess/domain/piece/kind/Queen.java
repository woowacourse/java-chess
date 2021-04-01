package chess.domain.piece.kind;

import chess.domain.piece.Color;
import chess.domain.piece.Direction;

public final class Queen extends Piece {
    public static final String QUEEN_NAME = "q";
    private static final int QUEEN_SCORE = 9;

    public Queen(Color color) {
        super(QUEEN_NAME, color);
        this.image = makeImage();
    }

    private String makeImage() {
        if (this.color.isSameAs(Color.BLACK)) {
            return "black_queen.png";
        }
        return "white_queen.png";
    }

    @Override
    public void checkCorrectDirection(Direction direction) {
        if (direction == null) {
            throw new IllegalArgumentException("옳지 않은 여왕의 움직임입니다.");
        }
    }

    @Override
    public double score() {
        return QUEEN_SCORE;
    }
}
