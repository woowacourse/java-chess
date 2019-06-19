package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractSingleMovePiece {
    private static final List<Direction> directions;

    static {
        directions = new ArrayList<>();
        directions.add(new Direction(1, 1));
        directions.add(new Direction(1, -1));
        directions.add(new Direction(-1, 1));
        directions.add(new Direction(-1, -1));
        directions.add(new Direction(0, 1));
        directions.add(new Direction(0, -1));
        directions.add(new Direction(1, 0));
        directions.add(new Direction(-1, 0));
    }

    public King(ChessTeam team) {
        super("King", team, directions);
    }
}
