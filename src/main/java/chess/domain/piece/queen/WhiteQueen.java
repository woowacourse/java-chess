package chess.domain.piece.queen;

import chess.domain.piece.Owner;

public class WhiteQueen extends Queen {

    private static final WhiteQueen WHITE_QUEEN = new WhiteQueen();

    private WhiteQueen() {
        super(Owner.WHITE);
    }

    public static WhiteQueen getInstance() {
        return WHITE_QUEEN;
    }

    @Override
    public String getSymbol() {
        return "q";
    }
}
