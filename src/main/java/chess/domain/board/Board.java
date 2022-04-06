package chess.domain.board;

import chess.domain.piece.Notation;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.Map;

public final class Board {

    private static final int INIT_KING_COUNT = 2;

    private final Map<Position, Piece> value;

    public Board(final Initializable initializable) {
        value = initializable.init();
    }

    public Map<Position, Piece> getValue() {
        return Collections.unmodifiableMap(value);
    }

    public void move(final Position from, final Position to) {
        final var fromPiece = value.get(from);

        fromPiece.checkMoveRange(new MoveOrder(value.keySet(), from, to));

        value.put(to, value.remove(from));
    }

    public boolean isRemovedKing() {
        final var kingCount = value.values().stream()
                .filter(piece -> piece.getNotation() == Notation.KING)
                .count();
        return kingCount != INIT_KING_COUNT;
    }

    public Piece getPiece(final Position position) {
        return value.get(position);
    }
}
