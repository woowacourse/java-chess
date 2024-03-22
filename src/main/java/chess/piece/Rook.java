package chess.piece;

import chess.position.UnitDirection;
import java.util.Set;

public class Rook extends Piece {

    private static final int MAX_UNIT_MOVE = 7;

    public Rook(Color color) {
        super(color,
                MAX_UNIT_MOVE,
                Set.of(
                        UnitDirection.differencesOf(1, 0),
                        UnitDirection.differencesOf(0, 1),
                        UnitDirection.differencesOf(-1, 0),
                        UnitDirection.differencesOf(0, -1)
                )
        );
    }
}
