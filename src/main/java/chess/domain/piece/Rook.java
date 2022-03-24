package chess.domain.piece;

import static chess.domain.direction.Direction.DOWN;
import static chess.domain.direction.Direction.LEFT;
import static chess.domain.direction.Direction.RIGHT;
import static chess.domain.direction.Direction.UP;

import chess.domain.ChessBoard;
import chess.domain.Color;
import chess.domain.Position;
import chess.domain.direction.Direction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Rook extends Piece {

    private static final String ROOK_NAME = "R";
    private static final double ROOK_SCORE = 5;
    private static final List<Direction> MOVE_DIRECTIONS = Arrays.asList(UP, DOWN, RIGHT, LEFT);

    public Rook(Color color) {
        super(color, ROOK_NAME);
    }

    @Override
    public boolean isMovable(Position source, Position target, ChessBoard chessBoard) {
        List<Position> route = calculateRoute(source, target);
        if (route.isEmpty()) {
            return false;
        }
        return isEmptyRouteWithoutTargetPosition(target, chessBoard, route)
                && !isTargetSameColor(source, target, chessBoard);
    }

    private List<Position> calculateRoute(Position source, Position target) {
        return MOVE_DIRECTIONS.stream()
                .map(direction -> direction.route(source, target))
                .filter(route -> !route.isEmpty())
                .findAny()
                .orElse(Collections.emptyList());
    }

    private boolean isEmptyRouteWithoutTargetPosition(Position target, ChessBoard chessBoard, List<Position> route) {
        return route.stream()
                .filter(position -> !position.equals(target))
                .allMatch(chessBoard::isPositionEmpty);
    }

    private boolean isTargetSameColor(Position source, Position target, ChessBoard chessBoard) {
        Piece piece = chessBoard.pieceByPosition(source);
        return !chessBoard.isPositionEmpty(target) && chessBoard.pieceByPosition(target).isSameTeamPiece(piece);
    }

    @Override
    public final double score() {
        return ROOK_SCORE;
    }

    @Override
    public final boolean isPawn() {
        return false;
    }

    @Override
    public final boolean isKing() {
        return false;
    }
}
