package chess.domain;

import chess.domain.position.Position;

import java.util.Map;
import java.util.Optional;

public final class Board {

    private final Map<Position, Piece> pieces;

    public Board(final Initiator initiator) {
        pieces = initiator.initiate();
    }

    public Optional<Piece> piece(final Position position) {
        if (pieces.containsKey(position)) {
            return Optional.of(pieces.get(position));
        }
        return Optional.empty();
    }

    public void move(String source, String target) {
        Position sourcePosition = Position.of(source);
        Position targetPosition = Position.of(target);
        Optional<Piece> wrappedPiece = piece(sourcePosition);
        validateNotEquals(sourcePosition, targetPosition);
        validatePieceExists(wrappedPiece);
    }

    private void validatePieceExists(final Optional<Piece> wrappedPiece) {
        if (wrappedPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 말이 존재하지 않습니다.");
        }
    }

    private void validateNotEquals(Position sourcePosition, Position targetPosition) {
        if (sourcePosition.equals(targetPosition)) {
            throw new IllegalArgumentException("[ERROR] 출발지와 목적지가 동일할 수 없습니다.");
        }
    }
}
