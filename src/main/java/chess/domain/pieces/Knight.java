package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
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

    @Override
    public Direction move(Point p1, Point p2){
        Direction direction = p1.direction(p2);
        if (directions.contains(direction)) {
            return direction;
        }
        throw new IllegalArgumentException();
    }
}
