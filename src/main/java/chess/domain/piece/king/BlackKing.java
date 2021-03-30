package chess.domain.piece.king;

import chess.domain.piece.Owner;

public class BlackKing extends King {

    private static final BlackKing BLACK_KING = new BlackKing();

    public BlackKing() {
        super(Owner.BLACK);
    }

    public static BlackKing getInstance() {
        return BLACK_KING;
    }

    @Override
    public String getSymbol() {
        return "K";
    }
}
