package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class King extends SingleStepPiece {
    private static final King blackKing = new King(Color.BLACK);
    private static final King whiteKing = new King(Color.WHITE);

    private King(Color color) {
        super(color, PieceType.KING, Direction.ofAll());
    }

    public static King ofBlack() {
        return blackKing;
    }

    public static King ofWhite() {
        return whiteKing;
    }
}
