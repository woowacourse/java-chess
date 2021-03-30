package chess.domain.piece.queen;

import chess.domain.piece.Owner;

public class BlackQueen extends Queen {

    private static final BlackQueen BLACK_QUEEN = new BlackQueen();

    private BlackQueen() {
        super(Owner.BLACK);
    }

    public static BlackQueen getInstance() {
        return BLACK_QUEEN;
    }

    @Override
    public String getSymbol() {
        return "Q";
    }
}
