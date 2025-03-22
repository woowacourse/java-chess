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

    public void move(final Position startPosition, final Position arrivalPosition, final Team currentTeam) {
        Piece piece = getPiece(startPosition, currentTeam);
        Set<Route> routes = makeTotalRoute(piece, startPosition, arrivalPosition);
        // 경로 찾기
        Route route = getRoute(routes, arrivalPosition);
        if (piece.getMoveType() != PieceMoveType.KNIGHT && hasPiece(route)) {
            throw new IllegalArgumentException("중간에 장애물이 있습니다.");
        }
        catchPiece(arrivalPosition);
        pieces.remove(startPosition);
        pieces.put(arrivalPosition, piece);
    }

    private void catchPiece(final Position arrivalPosition) {
        if (pieces.containsKey(arrivalPosition)) {
            pieces.remove(arrivalPosition);
        }
    }

    private boolean hasPiece(final Route route) {
        return route.getPositions().stream()
                .anyMatch(position -> pieces.containsKey(position));
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
        if (piece.canMovePerpendicular()) {
            add(routes, piece.moveUp(startPosition));
            add(routes, piece.moveDown(startPosition));
            add(routes, piece.moveRight(startPosition));
            add(routes, piece.moveLeft(startPosition));
        }

        if (piece.canMoveDiagonal()) {
            add(routes, piece.moveRightUp(startPosition));
            add(routes, piece.moveRightDown(startPosition));
            add(routes, piece.moveLeftUp(startPosition));
            add(routes, piece.moveLeftDown(startPosition));
        }

        if (piece.canLMove()) {
            add(routes, piece.moveUpRightUp(startPosition));
            add(routes, piece.moveUpLeftUp(startPosition));
            add(routes, piece.moveRightUpRight(startPosition));
            add(routes, piece.moveUpRightUp(startPosition));
            add(routes, piece.moveUpLeftUp(startPosition));
            add(routes, piece.moveRightUpRight(startPosition));
            add(routes, piece.moveRightDownRight(startPosition));
            add(routes, piece.moveDownRightDown(startPosition));
            add(routes, piece.moveDownLeftDown(startPosition));
            add(routes, piece.moveLeftUpLeft(startPosition));
            add(routes, piece.moveLeftDownLeft(startPosition));
        }
        return routes;
    }

    private void add(final Set<Route> routes, final Route route) {
        if (route == null) {
            return;
        }
        routes.add(route);
    }

    private Piece getPiece(final Position position, final Team currentTeam) {
        if (pieces.containsKey(position)) {
            Piece piece = pieces.get(position);
            if (piece.getTeam() == currentTeam) {
                return piece;
            }
            throw new IllegalArgumentException("같은 팀의 기물을 잡을 수 없습니다.");
        }
        throw new IllegalArgumentException("해당 위치에 기물이 없습니다.");
    }

    public Map<Position, Piece> getPieces() {
        return Collections.unmodifiableMap(pieces);
    }

    public boolean isFinished() {
        int count = 0;
        for (Piece piece : pieces.values()) {
            if (piece.getMoveType() == PieceMoveType.KING) {
                count++;
            }
        }
        return count != 2;
    }
}
