package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.*;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.List;

public class RookMovableStrategy implements PieceMovableStrategy {

    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP, DOWN, RIGHT, LEFT);

    @Override
    public boolean isMovable(Position start, Position target, ChessBoard chessBoard) {
        return MOVE_DIRECTIONS.stream()
                .map(direction -> direction.route(start, target))
                .anyMatch(route -> !route.isEmpty());
    }
}
