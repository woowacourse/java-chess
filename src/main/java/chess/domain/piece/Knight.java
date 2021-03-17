package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

import static chess.domain.board.Board.NEGATIVE;
import static chess.domain.board.Board.POSITIVE;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";

    public Knight(Team team) {
        super(KNIGHT_NAME, team);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(calculateKnightMovablePositions(target, POSITIVE, POSITIVE));
        movablePositions.addAll(calculateKnightMovablePositions(target, NEGATIVE, POSITIVE));
        movablePositions.addAll(calculateKnightMovablePositions(target, NEGATIVE, NEGATIVE));
        movablePositions.addAll(calculateKnightMovablePositions(target, POSITIVE, NEGATIVE));

        return movablePositions;
    }

    private List<Position> calculateKnightMovablePositions(Position target,
                                                           int horizontalDirection, int verticalDirection) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        result.addAll(findKnightDestination(horizontalWeight + horizontalDirection * 2,
                verticalWeight + verticalDirection));
        result.addAll(findKnightDestination(horizontalWeight + horizontalDirection,
                verticalWeight + verticalDirection * 2));

        return result;
    }

    private List<Position> findKnightDestination(int horizontalWeight, int verticalWeight) {
        List<Position> result = new ArrayList<>();
        if (horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER) {
            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }
}
