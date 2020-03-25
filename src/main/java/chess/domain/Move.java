package chess.domain;

import chess.domain.chesspiece.Direction;
import chess.domain.chesspiece.MoveRules;

import java.util.ArrayList;
import java.util.List;

public class Move {

    public static List<Position> makePassablePathLengthOne(MoveRules moveRules, Position position) {
        List<Direction> directions = moveRules.getDirections();
        List<Position> positions = new ArrayList<>();
        for (Direction dir : directions) {
            positions.add(addOne(dir, position));
        }
        return positions;
    }

    private static Position addOne(Direction dir, Position position) {
        int x = position.getX() + dir.getX();
        int y = position.getY() + dir.getY();
        return new Position(x, y);
    }

    public static List<Position> makePassablePath(MoveRules moveRules, Position position) {
        List<Direction> directions = moveRules.getDirections();
        List<Position> positions = new ArrayList<>();
        for (Direction dir : directions) {
            positions.addAll(makePath(dir, position));
        }
        return positions;
    }

    private static List<Position> makePath(Direction dir, Position position) {
        int x = position.getX() + dir.getX();
        int y = position.getY() + dir.getY();

        List<Position> positions = new ArrayList<>();
        while (x <= 8 && x > 0 && y > 0 && y <= 8) {
            positions.add(new Position(x, y));
            x += dir.getX();
            y += dir.getY();
        }
        return positions;
    }

    public static List<Position> makeKnightPath(Position position) {
        List<Position> positions = new ArrayList<>();
        int x = position.getX();
        int y = position.getY();

        if (x + 3 <= 8) {
            if (y + 1 <= 8) {
                positions.add(new Position(x + 3, y + 1));
            }
            if (y - 1 >= 1) {
                positions.add(new Position(x + 3, y - 1));
            }
        }
        if (y - 3 >= 1) {
            if (x + 1 <= 8) {
                positions.add(new Position(x + 1, y - 3));
            }
            if (x - 1 >= 1) {
                positions.add(new Position(x - 1, y - 3));
            }
        }
        if (x - 3 >= 1) {
            if (y + 1 <= 8) {
                positions.add(new Position(x - 3, y + 1));
            }
            if (y - 1 >= 1) {
                positions.add(new Position(x - 3, y - 1));
            }
        }
        if (y + 3 <= 8) {
            if (x + 1 <= 8) {
                positions.add(new Position(x + 1, y + 3));
            }
            if (x - 1 >= 1) {
                positions.add(new Position(x - 1, y + 3));
            }
        }
        return positions;
    }
}
