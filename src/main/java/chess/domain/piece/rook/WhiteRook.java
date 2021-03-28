package chess.domain.piece.rook;

import chess.domain.piece.Owner;

public class WhiteRook extends Rook {

    private static final WhiteRook WHITE_ROOK = new WhiteRook();

    private WhiteRook() {
        super(Owner.WHITE);
    }

    public static WhiteRook getInstance() {
        return WHITE_ROOK;
    }

    @Override
    public String getSymbol() {
        return "r";
    }
}
