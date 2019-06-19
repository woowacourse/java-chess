package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Knight extends AbstractPiece {
    private List<Direction> directions;

    public Knight(ChessTeam team) {
        super("Knight", team);
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

    @Override
    public Direction move(Point p1, Point p2) {
        Direction vector = p1.direction(p2);
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
