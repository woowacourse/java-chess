package chess.domain.board;

import chess.domain.piece.Piece;
import chess.domain.piece.notation.Color;
import chess.domain.piece.notation.PieceNotation;
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

    public void checkHasPiece(final List<Position> positions) {
        for (final Position position : positions) {
            checkHasPiece(position);
        }
    }

    private void checkHasPiece(final Position position) {
        if (value.get(position) != null) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
    }

    public Piece getPiece(final Position position) {
        return value.get(position);
    }

    public boolean hasKing(final Color color) {
        return value.values().stream()
                .filter(piece -> piece.getNotation() == PieceNotation.KING)
                .anyMatch(piece -> piece.isSameColor(color));
    }
}
