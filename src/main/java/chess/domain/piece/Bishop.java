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

        movablePositions.addAll(diagonal(target, POSITIVE, POSITIVE));
        movablePositions.addAll(diagonal(target, NEGATIVE, POSITIVE));
        movablePositions.addAll(diagonal(target, NEGATIVE, NEGATIVE));
        movablePositions.addAll(diagonal(target, POSITIVE, NEGATIVE));

        return movablePositions;
    }

    private List<Position> diagonal(Position target, int horizontalDirection, int verticalDirection) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontal().getWeight();
        int verticalWeight = target.getVertical().getWeight();

        while (horizontalWeight > Board.MIN_BORDER && horizontalWeight < Board.MAX_BORDER
                && verticalWeight > Board.MIN_BORDER && verticalWeight < Board.MAX_BORDER) {
            horizontalWeight += horizontalDirection;
            verticalWeight += verticalDirection;

            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }
}
