package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractSingleMovePiece {
    private static final List<Direction> directions;

    static {
        directions = new ArrayList<>();
        directions.add(new Direction(2, 1));
        directions.add(new Direction(2, -1));
        directions.add(new Direction(1, 2));
        directions.add(new Direction(1, -2));
        directions.add(new Direction(-2, 1));
        directions.add(new Direction(-2, -1));
        directions.add(new Direction(-1, 2));
        directions.add(new Direction(-1, -2));
    }

    public Knight(ChessTeam team) {
        super(team, PieceInfo.Knight, directions);
    }
}
