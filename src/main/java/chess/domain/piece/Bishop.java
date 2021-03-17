package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "B";
    private static final int POSITIVE = 1;
    private static final int NEGATIVE = -1;

    public Bishop(Team team) {
        super(BISHOP_NAME, team);
    }

    @Override
    public List<Position> movablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(calculateBishopMovablePositions(target, POSITIVE, POSITIVE));
        movablePositions.addAll(calculateBishopMovablePositions(target, NEGATIVE, POSITIVE));
        movablePositions.addAll(calculateBishopMovablePositions(target, NEGATIVE, NEGATIVE));
        movablePositions.addAll(calculateBishopMovablePositions(target, POSITIVE, NEGATIVE));

        return movablePositions;
    }

    private List<Position> calculateBishopMovablePositions(Position target, int horizontalDirection, int verticalDirection) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();
        while (horizontalWeight + horizontalDirection >= Board.MIN_BORDER && horizontalWeight + horizontalDirection <= Board.MAX_BORDER
                && verticalWeight + verticalDirection >= Board.MIN_BORDER && verticalWeight + verticalDirection <= Board.MAX_BORDER) {
            horizontalWeight += horizontalDirection;
            verticalWeight += verticalDirection;
            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }
}
