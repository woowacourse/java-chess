package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.DOWN_DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_DOWN_RIGHT;
import static chess.domain.direction.Direction.DOWN_LEFT_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT_RIGHT;
import static chess.domain.direction.Direction.UP_LEFT_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT_RIGHT;
import static chess.domain.direction.Direction.UP_UP_LEFT;
import static chess.domain.direction.Direction.UP_UP_RIGHT;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public class KnightMovableStrategy implements PieceMovableStrategy {

    private static final int MOVABLE_COUNT = 1;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP_UP_RIGHT, UP_RIGHT_RIGHT, DOWN_DOWN_RIGHT, DOWN_RIGHT_RIGHT,
            UP_UP_LEFT, UP_LEFT_LEFT, DOWN_DOWN_LEFT, DOWN_LEFT_LEFT);

    @Override
    public boolean isMovable(final Position start, final Position target, final ChessBoard chessBoard) {
        return MOVE_DIRECTIONS.stream()
                .map(direction -> direction.route(start, target))
                .anyMatch(route -> route.size() == MOVABLE_COUNT);
    }
}
