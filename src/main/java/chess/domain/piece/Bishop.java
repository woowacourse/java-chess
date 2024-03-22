package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Bishop extends MultiStepPiece {
    private static final Bishop BLACK_BISHOP = new Bishop(Color.BLACK);
    private static final Bishop WHITE_BISHOP = new Bishop(Color.WHITE);

    private Bishop(Color color) {
        super(color, PieceType.BISHOP, Direction.ofDiagonal);
    }

    public static Bishop ofBlack() {
        return BLACK_BISHOP;
    }

    public static Bishop ofWhite() {
        return WHITE_BISHOP;
    }
}
