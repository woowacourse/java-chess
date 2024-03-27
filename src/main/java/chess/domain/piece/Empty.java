package chess.domain.piece;

import chess.domain.position.Direction;
import java.util.Collections;
import java.util.List;

public class Empty extends Piece {
    public Empty(Color color, List<Direction> directions) {
        super(Color.EMPTY, Collections.emptyList());
    }

    @Override
    public int getMaxUnitMove() {
        return 0;
    }
}
