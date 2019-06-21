package chess.domain.pieces;

import chess.domain.Point;
import chess.domain.movepatterns.PawnPattern;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    public Pawn(Color color) {
        super(color, new PawnPattern(color));
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
