package chess.domain;

import chess.domain.movepatterns.PawnPattern;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Unit {
    public Pawn(Integer color) {
        super(color, new PawnPattern());
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
