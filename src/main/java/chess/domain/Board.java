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
        Piece piece = value.get(from);
        piece.checkPieceMoveRange(this, from, to);
        value.put(to, piece);
    }

    public boolean isMatchingColor(final Position from, final Color color) {
        Piece piece = value.get(from);
        return piece.isSameColor(color);
    }

    public Piece getPiece(final Position position) {
        return value.get(position);
    }

    public boolean hasPieceInFile(final Position from, final Position to) {
        return value.keySet().stream()
                .filter(position -> position.getFile().equals(from.getFile()))
                .filter(position -> position.getRankNumber() > from.getRankNumber())
                .anyMatch(position -> position.getRankNumber() < to.getRankNumber());
    }

    public boolean hasPieceInRank(final Position from, final Position to) {
        return value.keySet().stream()
                .filter(position -> position.getRankNumber() == from.getRankNumber())
                .filter(position -> position.getFileChar() > from.getFileChar())
                .anyMatch(position -> position.getFileChar() < to.getFileChar());
    }
}
