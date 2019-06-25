package chess.domain.piece;

import chess.domain.Position;

public class Empty extends Piece {
    private Empty(final Position position) {
        super(Type.EMPTY, Color.EMPTY, position);
    }

    public static Empty create(final Position position){
        return new Empty(position);
    }
}

