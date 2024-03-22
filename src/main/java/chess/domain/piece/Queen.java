package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Queen extends MultiStepPiece {
    private static final Queen BLACK_QUEEN = new Queen(Color.BLACK);
    private static final Queen WHITE_QUEEN = new Queen(Color.WHITE);

    private Queen(Color color) {
        super(color, PieceType.QUEEN, Direction.ofAll());
    }

    public static Queen ofBlack() {
        return BLACK_QUEEN;
    }

    public static Queen ofWhite() {
        return WHITE_QUEEN;
    }
}
