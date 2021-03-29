package chess.domain.order;

import chess.domain.piece.Piece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;

public class MoveRoute {
    private static final int INDEX_FROM_POSITION = 0;
    private static final int SHORTEST_LENGTH = 2;

    private final List<RouteEntry> moveRoute;

    public MoveRoute(List<RouteEntry> moveRoute) {
        this.moveRoute = moveRoute;
    }

    public Direction getDirection() {
        return Direction.of(getFromPosition(), getToPosition());
    }

    public Position getFromPosition() {
        return this.moveRoute.get(INDEX_FROM_POSITION).getPosition();
    }

    public Position getToPosition() {
        return this.moveRoute.get(this.moveRoute.size() - 1).getPosition();
    }

    public Piece getPieceAtFromPosition() {
        return this.moveRoute.get(INDEX_FROM_POSITION).getPiece();
    }

    public Piece getPieceAtToPosition() {
        return this.moveRoute.get(this.moveRoute.size() - 1).getPiece();
    }

    public boolean hasPieceAtToPosition() {
        return this.moveRoute.get(this.moveRoute.size() - 1).getPiece().isNotBlank();
    }

    public boolean isBlocked() {
        if (this.moveRoute.size() == SHORTEST_LENGTH) {
            return false;
        }
        return this.moveRoute.subList(1, this.moveRoute.size() - 1).stream()
                .map(RouteEntry::getPiece)
                .anyMatch(Piece::isNotBlank);
    }

    public int length() {
        return this.moveRoute.size();
    }
}
