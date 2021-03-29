package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;

import java.util.ArrayList;
import java.util.List;

public class PawnMovement {
    public List<Position> searchMovablePositions(Position target, Direction direction, boolean isFirstStep) {
        List<Position> movablePositions = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        if (isFirstStep) {
            movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight, verticalWeight + direction.getY() * 2));
        }
        movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight, verticalWeight + direction.getY()));

        movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight - 1, verticalWeight + direction.getY()));
        movablePositions.addAll(calculatePawnMovablePosition(horizontalWeight + 1, verticalWeight + direction.getY()));

        return movablePositions;
    }

    private List<Position> calculatePawnMovablePosition(int horizontalWeight, int verticalWeight) {
        List<Position> result = new ArrayList<>();
        if (horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER) {
            result.add(Position.of(Horizontal.findFromWeight(horizontalWeight),
                    Vertical.findFromWeight(verticalWeight)));
        }
        return result;
    }
}
