package chess.domain.piece;

import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

public class Knight extends Piece {
    private Knight(Color color) {
        super(color, Symbol.KNIGHT);
    }

    public static Knight createBlack() {
        return new Knight(Color.BLACK);
    }

    public static Knight createWhite() {
        return new Knight(Color.WHITE);
    }
}
