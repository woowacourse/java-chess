package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class King extends AbstractPiece {
    private List<Direction> directions;

    public King(ChessTeam team) {
        super("King", team);
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

    @Override
    public Direction move(Point p1, Point p2) {
        Direction direction = p1.direction(p2);
        if (directions.contains(direction)) {
            return direction;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        return move(p1, p2);
    }
}
