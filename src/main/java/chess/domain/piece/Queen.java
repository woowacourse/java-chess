package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Queen extends MultiStepPiece {
    private static final Queen blackQueen = new Queen(Color.BLACK);
    private static final Queen whiteQueen = new Queen(Color.WHITE);

    private Queen(Color color) {
        super(color, PieceType.QUEEN, Direction.ofAll());
    }

    public static Queen ofBlack() {
        return blackQueen;
    }

    public static Queen ofWhite() {
        return whiteQueen;
    }
}
