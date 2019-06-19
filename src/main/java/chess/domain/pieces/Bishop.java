package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractRangeMovePiece {
    private static final List<Direction> directions;

    static {
        directions = new ArrayList<>();
        directions.add(new Direction(1, 1));
        directions.add(new Direction(1, -1));
        directions.add(new Direction(-1, 1));
        directions.add(new Direction(-1, -1));
    }

    public Bishop(ChessTeam team) {
        super("Bishop", team, directions);
    }
}
