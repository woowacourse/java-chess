package chess.domain.piece.king;

import chess.domain.piece.Owner;

public class WhiteKing extends King {

    private static final WhiteKing WHITE_KING = new WhiteKing();

    public WhiteKing() {
        super(Owner.WHITE);
    }

    public static WhiteKing getInstance() {
        return WHITE_KING;
    }

    @Override
    public String getSymbol() {
        return "k";
    }
}
