package chess.domain.piece;

import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

public class Queen extends Piece {
    private Queen(Color color) {
        super(color, Symbol.QUEEN);
    }

    public static Queen createBlack() {
        return new Queen(Color.BLACK);
    }

    public static Queen createWhite() {
        return new Queen(Color.WHITE);
    }
}
