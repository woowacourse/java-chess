package chess.domain.piece.bishop;

import chess.domain.piece.Owner;

public class WhiteBishop extends Bishop {

    private static final WhiteBishop WHITE_BISHOP = new WhiteBishop();

    private WhiteBishop() {
        super(Owner.WHITE);
    }

    public static WhiteBishop getInstance() {
        return WHITE_BISHOP;
    }

    @Override
    public String getSymbol() {
        return "b";
    }
}
