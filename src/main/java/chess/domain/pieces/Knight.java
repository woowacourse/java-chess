package chess.domain.pieces;

import chess.domain.Point;
import chess.domain.movepatterns.KnightPattern;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    public Knight(Color color) {
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
