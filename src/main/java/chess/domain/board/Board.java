package chess.domain.board;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> value;

    public Board(final Initializable initializable) {
        value = initializable.init();
    }

    public boolean isInitialized(final Initializable initializable) {
        return value.equals(initializable.init());
    }

    public boolean isMatchedColor(final Position target, final Color color) {
        Piece piece = getPiece(target);
        return piece.isSameColor(color);
    }

    public void move(final Position from, final Position to) {
        final Piece piece = getPiece(from);
        piece.checkMovingRange(this, from, to);

        if (!piece.isKnight() && hasPieceInPath(from, to)) {
            throw new IllegalArgumentException("이동 경로에 기물이 존재합니다.");
        }
        value.put(to, value.remove(from));
    }

    public boolean hasKing(final Color color) {
        return value.values().stream()
                .filter(piece -> piece.isSameColor(color))
                .anyMatch(Piece::isKing);
    }

    public boolean hasPiece(final Position position) {
        return value.get(position) != null;
    }

    private boolean hasPieceInPath(final Position from, final Position to) {
        return getPath(from, to).stream()
                .map(this::hasPiece)
                .filter(hasPiece -> hasPiece)
                .findFirst()
                .orElse(false);
    }

    private List<Position> getPath(final Position from, final Position to) {
        List<Position> paths = new ArrayList<>();
        Position next = nextPosition(from, to);
        while (next != to) {
            paths.add(next);
            next = nextPosition(next, to);
        }
        return paths;
    }

    private Position nextPosition(final Position from, final Position to) {
        return from.next(to);
    }

    public Piece getPiece(final Position position) {
        final Piece piece = value.get(position);
        if (piece != null) {
            return piece;
        }
        throw new IllegalArgumentException("해당 위치에 기물이 존재하지 않습니다.");
    }

    public Map<Position, Piece> toMap() {
        return Collections.unmodifiableMap(value);
    }
}
