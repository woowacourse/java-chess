package chess.domain;

import chess.domain.movepatterns.KnightPattern;
import chess.domain.movepatterns.MovePattern;

import java.util.ArrayList;
import java.util.List;

public class Knight {
    private final MovePattern movePattern = new KnightPattern();
    private final Integer color;

    public Knight(Integer color) {
        this.color = color;
    }

    public boolean isValidMovePattern(Point source, Point target) {
        return movePattern.canMove(source, target);
    }

    public List<Point> makePath(Point source, Point target) {
        return new ArrayList<>();
    }
}
