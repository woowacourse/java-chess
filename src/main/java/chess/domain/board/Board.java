package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.notation.Color;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public final class Board {

    private final Map<Position, Piece> value;

    public Board(final Initializable initializable) {
        value = initializable.init();
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    public void move(final Position from, final Position to) {
        final var fromPiece = value.get(from);

        fromPiece.checkMoveRange(this, from, to);

        value.put(to, value.remove(from));
    }

    public boolean hasPiece(final Position position) {
        return value.get(position) != null;
    }

    public boolean hasPiece(final List<Position> positions) {
        return positions.stream()
                .anyMatch(this::hasPiece);
    }

    public boolean hasKing(final Color color) {
        return value.values().stream()
                .filter(Piece::isKing)
                .anyMatch(piece -> piece.isSameColor(color));
    }

    public Piece getPiece(final Position position) {
        return value.get(position);
    }
}
