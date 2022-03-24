package chess.domain.piece.strategy;

import static chess.domain.direction.Direction.*;

import chess.domain.ChessBoard;
import chess.domain.Position;
import chess.domain.direction.Direction;
import chess.domain.piece.Piece;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BishopMovableStrategy implements PieceMovableStrategy {

    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP_RIGHT, UP_LEFT, DOWN_RIGHT, DOWN_LEFT);

    @Override
    public boolean isMovable(Position start, Position target, ChessBoard chessBoard) {
        List<Position> route = calculateRoute(start, target);
        if (route.isEmpty()) {
            return false;
        }
        return isEmptyRouteWithoutTargetPosition(target, chessBoard, route)
                && !isTargetSameColor(start, target, chessBoard);
    }

    private List<Position> calculateRoute(final Position start, final Position target) {
        return MOVE_DIRECTIONS.stream()
                .map(direction -> direction.route(start, target))
                .filter(route -> !route.isEmpty())
                .findAny()
                .orElse(Collections.emptyList());
    }

    private boolean isEmptyRouteWithoutTargetPosition(Position target, ChessBoard chessBoard, List<Position> route) {
        return route.stream()
                .filter(position -> !position.equals(target))
                .allMatch(chessBoard::isPositionEmpty);
    }

    private boolean isTargetSameColor(Position start, Position target, ChessBoard chessBoard) {
        Piece piece = chessBoard.pieceByPosition(start);
        return !chessBoard.isPositionEmpty(target) && chessBoard.pieceByPosition(target).isSameTeamPiece(piece);
    }
}
