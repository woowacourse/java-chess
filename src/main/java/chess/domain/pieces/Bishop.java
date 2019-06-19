package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends AbstractPiece {
    private List<Direction> directions;

    public Bishop(ChessTeam team) {
        super("Bishop", team);
        directions = new ArrayList<>();
        directions.add(new Direction(1, 1));
        directions.add(new Direction(1, -1));
        directions.add(new Direction(-1, 1));
        directions.add(new Direction(-1, -1));
    }

    @Override
    public Direction move(Point p1, Point p2) {
        Direction vector = p1.direction(p2).vector();
        if (directions.contains(vector)) {
            return vector;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        return move(p1, p2);
    }
}
