package chess;

import chess.piece.Piece;
import chess.piece.PieceMoveType;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board(final Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(final Position startPosition, final Position arrivalPosition) {
        Piece piece = getPiece(startPosition);
        Set<Route> routes = makeTotalRoute(piece, startPosition, arrivalPosition);
        // 경로 찾기
        Route route = getRoute(routes, arrivalPosition);
        if (piece.getMoveType() != PieceMoveType.KNIGHT && hasPiece(route)) {
            throw new IllegalArgumentException("중간에 장애물이 있습니다.");
        }
    }

    private boolean hasPiece(final Route route) {
        return route.getPositions().stream()
                .anyMatch(position -> pieces.containsKey(position));
    }

    private boolean canArrive(final Set<Route> routes, final Position arrivalPosition) {
        return routes.stream()
                .map(Route::getLast)
                .anyMatch(position -> position.equals(arrivalPosition));
    }

    private Route getRoute(final Set<Route> routes, final Position arrivalPosition) {
        return routes.stream()
                .filter(route -> route.getLast().equals(arrivalPosition))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("움직일 수 없는 위치입니다."));
    }

    private Set<Route> makeTotalRoute(final Piece piece, final Position startPosition,
                                      final Position arrivalPosition) {
        Set<Route> routes = new HashSet<>();
        if (piece.canMoveDiagonal()) {
            routes.add(piece.moveUp(startPosition));
            routes.add(piece.moveDown(startPosition));
            routes.add(piece.moveRight(startPosition));
            routes.add(piece.moveLeft(startPosition));
        }

        if (piece.canMovePerpendicular()) {
            routes.add(piece.moveRightUp(startPosition));
            routes.add(piece.moveRightDown(startPosition));
            routes.add(piece.moveLeftUp(startPosition));
            routes.add(piece.moveLeftDown(startPosition));
        }

        if (piece.canLMove()) {
            routes.add(piece.moveUpRightUp(startPosition));
            routes.add(piece.moveUpLeftUp(startPosition));
            routes.add(piece.moveRightUpRight(startPosition));
            routes.add(piece.moveUpRightUp(startPosition));
            routes.add(piece.moveUpLeftUp(startPosition));
            routes.add(piece.moveRightUpRight(startPosition));
            routes.add(piece.moveRightDownRight(startPosition));
            routes.add(piece.moveDownRightDown(startPosition));
            routes.add(piece.moveDownLeftDown(startPosition));
            routes.add(piece.moveLeftUpLeft(startPosition));
            routes.add(piece.moveLeftDownLeft(startPosition));
        }
        return routes;
    }

    private Piece getPiece(final Position position) {
        if (pieces.containsKey(position)) {
            return pieces.get(position);
        }
        throw new IllegalArgumentException();
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
