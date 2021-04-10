package chess.domain.piece;

import chess.domain.piece.movement.Distance;

import java.util.Collections;

public class Empty extends Piece {
    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(
                Owner.NONE,
                Score.EMPTY,
                Collections.emptyList(),
                Distance.ONE,
                Symbol.EMPTY
        );
    }

    public static Empty of() {
        return EMPTY;
    }
}
