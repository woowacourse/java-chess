package chess.domain.piece;

import chess.domain.piece.rule.Distance;
import chess.domain.piece.rule.Score;

import java.util.Collections;

public class Empty extends Piece {
    private static final Empty EMPTY = new Empty();

    private Empty() {
        super(
                Owner.NONE,
                Score.EMPTY,
                Collections.emptyList(),
                Distance.ONE,
                "."
        );
    }

    public static Empty getInstance() {
        return EMPTY;
    }
}
