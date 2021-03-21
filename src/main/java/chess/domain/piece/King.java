package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.moveStrategy.SpecifiedLocationStrategy;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece {
    private static final String KING_NAME = "K";
    private static final double SCORE = 0;

    public King(Team team) {
        super(KING_NAME, team, SCORE, new SpecifiedLocationStrategy());
    }

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

    private boolean isInBorder(int horizontalWeight, int verticalWeight) {
        return horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER;
    }
}
