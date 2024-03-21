package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Bishop extends MultiStepPiece {
    private static final Bishop blackBishop = new Bishop(Color.BLACK);
    private static final Bishop whiteBishop = new Bishop(Color.WHITE);

    private Bishop(Color color) {
        super(color, PieceType.BISHOP, Direction.ofDiagonal());
    }

    public static Bishop ofBlack() {
        return blackBishop;
    }

    public static Bishop ofWhite() {
        return whiteBishop;
    }
}
