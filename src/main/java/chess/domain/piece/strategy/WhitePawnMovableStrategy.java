package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.UP;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.List;

public class WhitePawnMovableStrategy implements PieceMovableStrategy {

    private static final int MOVABLE_COUNT = 1;
    private static final Direction MOVE_DIRECTION = UP;

    @Override
    public boolean isMovable(final Position start, final Position target, final ChessBoard chessBoard) {
        List<Position> route = MOVE_DIRECTION.route(start, target);
        return route.size() == MOVABLE_COUNT;
    }
}
