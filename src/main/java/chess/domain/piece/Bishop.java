package chess.domain.piece;

import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

public class Bishop extends Piece {
    private Bishop(Color color) {
        super(color, Symbol.BISHOP);
    }

    public static Bishop createBlack() {
        return new Bishop(Color.BLACK);
    }

    public static Bishop createWhite() {
        return new Bishop(Color.WHITE);
    }
}
