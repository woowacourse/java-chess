package chess.domain.movepatterns;


import chess.domain.Point;
import chess.domain.pieces.Color;
import chess.domain.pieces.Direction;

import java.util.List;

public class PawnPattern implements MovePattern {

    private final Color color;
    private boolean isFirstTurn;

    public PawnPattern(Color color) {
        this.color = color;
        isFirstTurn = true;
    }

    @Override
    public boolean canMove(Point source, Point target) {
        if (isFirstTurn && source.plusPoint(Direction.pawnFirstTurnDirection(color)).equals(target)) {
            isFirstTurn = false;
            return true;
        }
        List<Direction> pawnDirections = Direction.pawnDirection(color);
        return pawnDirections.stream()
                .anyMatch(direction -> source.plusPoint(direction).equals(target));
    }
}
