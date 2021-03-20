package chess.domain.piece;

import chess.domain.piece.team.Color;
import chess.domain.piece.team.Symbol;

public class Empty extends Piece {
    private Empty(Color color) {
        super(color, Symbol.EMPTY);
    }

    public static Empty create() {
        return new Empty(Color.EMPTY);
    }
}
