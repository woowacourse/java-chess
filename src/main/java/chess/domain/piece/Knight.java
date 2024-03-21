package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Knight extends SingleStepPiece {
    private static final Knight blackKnight = new Knight(Color.BLACK);
    private static final Knight whiteKnight = new Knight(Color.WHITE);

    private Knight(Color color) {
        super(color, PieceType.KNIGHT, Direction.ofKnight());
    }

    public static Knight ofBlack() {
        return blackKnight;
    }

    public static Knight ofWhite() {
        return whiteKnight;
    }
}
