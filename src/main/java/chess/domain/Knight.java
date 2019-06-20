package chess.domain;

import chess.domain.movepatterns.KnightPattern;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Unit {

    public Knight(Integer color) {
        super(color, new KnightPattern());
    }

    @Override
    public boolean isValidMovePattern(Point source, Point target) {
        return movePattern.canMove(source, target);
    }

    @Override
    public List<Point> makePath(Point source, Point target) {
        return new ArrayList<>();
    }
}
