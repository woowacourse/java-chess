package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Rook extends MultiStepPiece {
    private static final Rook BLACK_ROOK = new Rook(Color.BLACK);
    private static final Rook WHITE_ROOK = new Rook(Color.WHITE);

    private Rook(Color color) {
        super(color, PieceType.ROOK, Direction.ofStraight);
    }

    public static Rook ofBlack() {
        return BLACK_ROOK;
    }

    public static Rook ofWhite() {
        return WHITE_ROOK;
    }
}
