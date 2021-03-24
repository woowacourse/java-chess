package chess.Strategy;

import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;

import java.util.ArrayList;
import java.util.List;

public class KingMoveStrategy extends SpecifiedMoveStrategy {
    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        for (Direction direction : Direction.values()) {
            int changedHorizontalWeight = horizontalWeight + direction.getX();
            int changedVerticalWeight = verticalWeight + direction.getY();
            movablePositions.addAll(calculateBoardPosition(changedHorizontalWeight, changedVerticalWeight));
        }

        return movablePositions;
    }

    private List<Position> calculateBoardPosition(int changedHorizontalWeight, int changedVerticalWeight) {
        List<Position> result = new ArrayList<>();
        if (isInBorder(changedHorizontalWeight, changedVerticalWeight)) {
            result.add(Position.of(
                    Horizontal.findFromWeight(changedHorizontalWeight),
                    Vertical.findFromWeight(changedVerticalWeight))
            );
        }
        return result;
    }
}
