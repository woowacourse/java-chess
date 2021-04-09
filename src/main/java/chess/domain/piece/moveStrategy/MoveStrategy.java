package chess.domain.piece.moveStrategy;

import chess.domain.board.Board;
import chess.domain.board.Horizontal;
import chess.domain.board.Position;
import chess.domain.board.Vertical;
import chess.domain.piece.Direction;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class MoveStrategy {
    public List<Position> searchMovablePositions(Position target, List<Direction> directions) {
        return directions.stream()
                .flatMap(direction -> calculateBoardPosition(target, direction).stream())
                .collect(Collectors.toList());
    }

    public List<Position> calculateBoardPosition(Position target, Direction direction) {
        List<Position> result = new ArrayList<>();
        int x = target.movedHorizontalWeight(direction.getX());
        int y = target.movedVerticalWeight(direction.getY());

        if (isInBorder(x, y)) {
            result.add(Position.of(Horizontal.findFromWeight(x), Vertical.findFromWeight(y)));
        }

        return result;
    }

    private boolean isInBorder(int horizontalWeight, int verticalWeight) {
        return horizontalWeight >= Board.MIN_BORDER && horizontalWeight <= Board.MAX_BORDER
                && verticalWeight >= Board.MIN_BORDER && verticalWeight <= Board.MAX_BORDER;
    }

    public abstract boolean canMove(Position target, Position destination, Board board);
}
