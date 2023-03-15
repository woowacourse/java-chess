package chess.domain.piece;

import chess.domain.Color;

public class Empty extends Piece {
    
    private Empty() {
        super(Color.NONE);
    }
    
    public static Empty create() {
        return new Empty();
    }
}
