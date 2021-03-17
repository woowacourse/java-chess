package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class King extends Piece {
    private static final String KING_NAME = "K";
    private static final List<List<Integer>> KING_MOVABLE_POSITIONS;

    static {
        KING_MOVABLE_POSITIONS = Arrays.asList(
                Arrays.asList(0, 1),
                Arrays.asList(1, 1),
                Arrays.asList(1, 0),
                Arrays.asList(1, -1),
                Arrays.asList(0, -1),
                Arrays.asList(-1, -1),
                Arrays.asList(-1, 0),
                Arrays.asList(-1, 1)
        );
    }

    public King(Team team) {
        super(KING_NAME, team);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        for (List<Integer> list : KING_MOVABLE_POSITIONS) {
            int changedHorizontalWeight = horizontalWeight + list.get(0);
            int changedVerticalWeight = verticalWeight + list.get(1);
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
