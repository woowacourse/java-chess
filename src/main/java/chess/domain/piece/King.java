package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class King extends SingleStepPiece {
    private static final King BLACK_KING = new King(Color.BLACK);
    private static final King WHITE_KING = new King(Color.WHITE);

    private King(Color color) {
        super(color, PieceType.KING, Direction.ofAll);
    }

    public static King ofBlack() {
        return BLACK_KING;
    }

    public static King ofWhite() {
        return WHITE_KING;
    }
}
