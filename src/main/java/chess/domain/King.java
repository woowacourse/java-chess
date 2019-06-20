package chess.domain;

import chess.domain.movepatterns.KingPattern;

import java.util.ArrayList;
import java.util.List;

public class King extends Unit {

    public King(Integer color) {
        super(color, new KingPattern());
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
