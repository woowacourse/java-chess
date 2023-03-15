package chess.domain.piece;

import chess.domain.Color;

public class King extends Piece {
    
    private King(final Color color) {
        super(color);
    }
    
    public static King create(final Color color) {
        return new King(color);
    }
}
