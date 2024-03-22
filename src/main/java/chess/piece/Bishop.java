package chess.piece;

import chess.position.UnitDirection;
import java.util.Set;

public class Bishop extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Bishop(Color color) {
        super(color,
                MAX_UNIT_MOVE,
                Set.of(
                        UnitDirection.differencesOf(1, 1),
                        UnitDirection.differencesOf(1, -1),
                        UnitDirection.differencesOf(-1, 1),
                        UnitDirection.differencesOf(-1, -1)
                )
        );
    }
}
