package chess.domain.piece.bishop;

import chess.domain.piece.Owner;

public class BlackBishop extends Bishop {

    private static final BlackBishop BLACK_BISHOP = new BlackBishop();

    private BlackBishop() {
        super(Owner.BLACK);
    }

    public static BlackBishop getInstance() {
        return BLACK_BISHOP;
    }

    @Override
    public String getSymbol() {
        return "B";
    }
}
