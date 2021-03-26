package chess.domain.order;

import chess.domain.piece.Piece;
import chess.domain.piece.RealPiece;
import chess.domain.position.Direction;
import chess.domain.position.Position;

import java.util.List;
import java.util.NoSuchElementException;

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

    public boolean hasPieceAtToPosition() {
        return this.moveRoute.get(this.moveRoute.size() - 1).getPiece().isNotBlank();

    }

    public Position getFromPosition() {
        return this.moveRoute.get(INDEX_FROM_POSITION).getPosition();
    }

    public Position getToPosition() {
        return this.moveRoute.get(this.moveRoute.size() - 1).getPosition();
    }

    public RealPiece getPieceAtFromPosition() {
        Piece piece = this.moveRoute.get(INDEX_FROM_POSITION).getPiece();
        if (piece.isBlank()) {
            throw new NoSuchElementException("해당 칸에는 기물이 없습니다.");
        }
        return (RealPiece) piece;
    }

    public RealPiece getPieceAtToPosition() {
        Piece piece = this.moveRoute.get(this.moveRoute.size() - 1).getPiece();
        if (piece.isBlank()) {
            throw new NoSuchElementException("해당 칸에는 기물이 없습니다.");
        }
        return (RealPiece) piece;
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
