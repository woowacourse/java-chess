package chess.domain.piece;

import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

public class King extends Piece {
    private King(Color color) {
        super(color, Symbol.KING);
    }

    public static King createBlack() {
        return new King(Color.BLACK);
    }

    public static King createWhite() {
        return new King(Color.WHITE);
    }
}
