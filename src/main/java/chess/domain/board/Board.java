package chess.domain.board;

import chess.domain.piece.MovedPawn;
import chess.domain.piece.Piece;
import chess.domain.position.Position;
import java.util.Collections;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> pieces;

    public Board(Map<Position, Piece> pieces) {
        this.pieces = pieces;
    }

    public void move(Position source, Position destination) {
        validatePieceExistsAt(source);
        validateAllyPieceNotOnDestination(source, destination);
        validateNoPiecesBetween(source, destination);

        Piece piece = pieces.get(source);
        if (hasEnemyPieceOn(destination, piece)) {
            validateAttackable(source, destination, piece);
            replacePiece(source, destination, piece);
            return;
        }
        validateMovable(source, destination, piece);
        replacePiece(source, destination, piece);
    }

    private void validatePieceExistsAt(Position source) {
        if (!pieces.containsKey(source)) {
            throw new IllegalArgumentException("출발 칸에 기물이 없습니다.");
        }
    }

    private void validateAllyPieceNotOnDestination(Position source, Position destination) {
        Piece sourcePiece = pieces.get(source);
        if (!pieces.containsKey(destination)) {
            return;
        }
        Piece destinationPiece = pieces.get(destination);
        if (destinationPiece.hasSameColorWith(sourcePiece)) {
            throw new IllegalArgumentException("도착 칸에 자신의 기물이 있습니다.");
        }
    }

    private void validateNoPiecesBetween(Position source, Position destination) {
        if (source.isOnKnightRoute(destination)) {
            return;
        }
        Path path = Path.createExcludingBothEnds(source, destination);
        if (path.hasPieceOnRoute(pieces)) {
            throw new IllegalArgumentException("경로에 기물이 있습니다.");
        }
    }

    private boolean hasEnemyPieceOn(Position destination, Piece piece) {
        return pieces.containsKey(destination) &&
                pieces.get(destination).hasDifferentColorWith(piece);
    }

    private void validateAttackable(Position source, Position destination, Piece piece) {
        if (!piece.isAttackable(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
    }

    private void validateMovable(Position source, Position destination, Piece piece) {
        if (!piece.isMovable(source, destination)) {
            throw new IllegalArgumentException("이동할 수 없는 경로입니다.");
        }
    }

    private void replacePiece(Position source, Position destination, Piece piece) {
        pieces.remove(source);
        if (piece.isInitPawn()) {
            pieces.put(destination, new MovedPawn(piece.getColor()));
            return;
        }
        pieces.put(destination, piece);
    }

    public Map<Position, Piece> pieces() {
        return Collections.unmodifiableMap(pieces);
    }
}
