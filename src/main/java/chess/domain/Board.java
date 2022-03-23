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
        Optional<Piece> wrappedPiece = piece(sourcePosition);
        if (wrappedPiece.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 말이 존재하지 않습니다.");
        }
    }
}
