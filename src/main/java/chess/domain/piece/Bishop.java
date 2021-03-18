package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    private static final String BISHOP_NAME = "B";

    public Bishop(Team team) {
        super(BISHOP_NAME, team);
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.RIGHT_TOP));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.LEFT_TOP));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.LEFT_BOTTOM));
        movablePositions.addAll(calculateBishopMovablePositions(target, Direction.RIGHT_BOTTOM));

        return movablePositions;
    }

    private List<Position> calculateBishopMovablePositions(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();
        while (horizontalWeight + direction.getX() >= Board.MIN_BORDER && horizontalWeight + direction.getX() <= Board.MAX_BORDER
                && verticalWeight + direction.getY() >= Board.MIN_BORDER && verticalWeight + direction.getY() <= Board.MAX_BORDER) {
            horizontalWeight += direction.getX();
            verticalWeight += direction.getY();
            result.add(
                    Position.of(Horizontal.findFromWeight(horizontalWeight), Vertical.findFromWeight(verticalWeight))
            );
        }
        return result;
    }
}
