package chess.domain.piece.strategy;

import chess.domain.position.Position;
import java.util.Arrays;
import java.util.List;

public class PawnStrategy implements MoveStrategy {

    @Override
    public boolean canMove(Position from, Position to) {
        List<Double> ableSquareDistance = Arrays.asList(1.0,2.0,4.0);
        return ableSquareDistance.contains(from.diagonalDistance(to)) && from.rowGap(to) == 0;
    }
}
