package chess.domain.pieces;

import chess.domain.ChessTeam;
import chess.domain.Direction;
import chess.domain.Point;

import java.util.ArrayList;
import java.util.List;

public class Rook extends AbstractPiece {
    List<Direction> directions;

    public Rook(ChessTeam team) {
        super("Rook", team);
        directions = new ArrayList<>();
        directions.add(new Direction(0, 1));
        directions.add(new Direction(0, -1));
        directions.add(new Direction(1, 0));
        directions.add(new Direction(-1, 0));
    }

    @Override
    public Direction move(Point p1, Point p2) {
        Direction vectorDirection = p1.direction(p2).vector();
        if (directions.contains(vectorDirection)) {
            return vectorDirection;
        }
        throw new IllegalArgumentException();
    }

    @Override
    public Direction attack(Point p1, Point p2) {
        return move(p1, p2);
    }
}
