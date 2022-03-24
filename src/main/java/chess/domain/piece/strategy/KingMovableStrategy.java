package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.DOWN_LEFT;
import static chess.domain.direction.Direction.DOWN_RIGHT;
import static chess.domain.direction.Direction.LEFT;
import static chess.domain.direction.Direction.RIGHT;
import static chess.domain.direction.Direction.UP;
import static chess.domain.direction.Direction.UP_LEFT;
import static chess.domain.direction.Direction.UP_RIGHT;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.List;

public class KingMovableStrategy implements PieceMovableStrategy {

    private static final int MOVABLE_COUNT = 1;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(
            UP, DOWN, RIGHT, LEFT, UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    @Override
    public boolean isMovable(Position start, Position target, ChessBoard chessBoard) {
        return !existSameColorPiece(start, target, chessBoard) && isMovableByDirection(start, target);
    }

    private boolean existSameColorPiece(Position start, Position target, ChessBoard chessBoard) {
        Piece piece = chessBoard.pieceByPosition(start);
        return !chessBoard.isPositionEmpty(target) && piece.isSameTeamPiece(chessBoard.pieceByPosition(target));
    }

    private boolean isMovableByDirection(final Position start, final Position target) {
        return MOVE_DIRECTIONS.stream()
                .map(direction -> direction.route(start, target))
                .anyMatch(route -> route.size() == MOVABLE_COUNT);
    }
}
