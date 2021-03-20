package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.moveStrategy.PawnStrategy;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private static final String PAWN_NAME = "P";
    private static final double SCORE = 1;

    public Pawn(Team team) {
        super(PAWN_NAME, team, SCORE, new PawnStrategy());
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();
        Direction direction = findDirection();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        if (isFirstStep(target)) {
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

    private Direction findDirection() {
        if (isBlack()) {
            return Direction.BOTTOM;
        }
        return Direction.TOP;
    }

    private boolean isFirstStep(Position position) {
        if (isBlack() && position.isSameVertical(Vertical.SEVEN)) {
            return true;
        }
        return isWhite() && position.isSameVertical(Vertical.TWO);
    }
}
