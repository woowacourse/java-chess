package chess.domain.piece;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    private static final String KNIGHT_NAME = "N";
    private final double score = 2.5;

    public Knight(Team team) {
        super(KNIGHT_NAME, team);
    }

    @Override
    public boolean canMove(Position target, Position destination, Board board) {
        return false;
    }

    @Override
    public List<Position> searchMovablePositions(Position target) {
        List<Position> movablePositions = new ArrayList<>();

        movablePositions.addAll(calculateKnightMovablePositions(target, Direction.RIGHT_TOP));
        movablePositions.addAll(calculateKnightMovablePositions(target, Direction.LEFT_TOP));
        movablePositions.addAll(calculateKnightMovablePositions(target, Direction.LEFT_BOTTOM));
        movablePositions.addAll(calculateKnightMovablePositions(target, Direction.RIGHT_BOTTOM));

        return movablePositions;
    }

    private List<Position> calculateKnightMovablePositions(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int horizontalWeight = target.getHorizontalWeight();
        int verticalWeight = target.getVerticalWeight();

        result.addAll(findKnightDestination(horizontalWeight + direction.getX() * 2,
                verticalWeight + direction.getY()));
        result.addAll(findKnightDestination(horizontalWeight + direction.getX(),
                verticalWeight + direction.getY() * 2));

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

    public double getScore() {
        return score;
    }
}
