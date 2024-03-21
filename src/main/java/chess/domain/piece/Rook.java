package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Direction;

public class Rook extends MultiStepPiece {
    private static final Rook blackRook = new Rook(Color.BLACK);
    private static final Rook whiteRook = new Rook(Color.WHITE);

    private Rook(Color color) {
        super(color, PieceType.ROOK, Direction.ofStraight());
    }

    public static Rook ofBlack() {
        return blackRook;
    }

    public static Rook ofWhite() {
        return whiteRook;
    }
}
