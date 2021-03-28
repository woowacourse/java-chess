package chess.domain.piece.rook;

import chess.domain.piece.Owner;

public class BlackRook extends Rook {

    private static final BlackRook BLACK_ROOK = new BlackRook();

    private BlackRook() {
        super(Owner.BLACK);
    }

    public static BlackRook getInstance() {
        return BLACK_ROOK;
    }

    @Override
    public String getSymbol() {
        return "R";
    }
}
