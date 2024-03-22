package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Knight extends SingleStepPiece {
    private static final Knight BLACK_KNIGHT = new Knight(Color.BLACK);
    private static final Knight WHITE_KNIGHT = new Knight(Color.WHITE);

    private Knight(Color color) {
        super(color, PieceType.KNIGHT, Direction.ofKnight());
    }

    public static Knight ofBlack() {
        return BLACK_KNIGHT;
    }

    public static Knight ofWhite() {
        return WHITE_KNIGHT;
    }
}
