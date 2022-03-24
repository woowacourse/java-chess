package chess.domain;

import chess.domain.piece.Color;
import chess.domain.piece.Piece;
import chess.domain.position.Position;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Position, Piece> value = new HashMap<>();

    public Board() {
        BoardInitializer.init(value);
    }

    public Map<Position, Piece> toMap() {
        return Collections.unmodifiableMap(value);
    }

    public void move(final Position from, final Position to) {

    }

    public boolean isMatchingColor(final Position from, final Color color) {
        Piece piece = value.get(from);
        return piece.isSameColor(color);
    }
}
