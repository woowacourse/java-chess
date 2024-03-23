package chess.piece;

import chess.position.UnitDirection;
import java.util.Set;

public class Bishop extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Bishop(Color color) {
        super(color,
                Set.of(
                        UnitDirection.differencesOf(1, 1),
                        UnitDirection.differencesOf(1, -1),
                        UnitDirection.differencesOf(-1, 1),
                        UnitDirection.differencesOf(-1, -1)
                )
        );
    }

    @Override
    protected boolean isReachable(int distance) {
        return distance <= MAX_UNIT_MOVE;
    }
}
