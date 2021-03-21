package chess.domain.pieces;

import chess.domain.position.Position;

import java.util.ArrayList;
import java.util.List;

public class Pieces {
    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = new ArrayList<>(pieces);
    }

    public boolean containByPosition(final Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.samePosition(position));
    }

    public Piece getPieceByPosition(final Position position) {
        return pieces.stream()
                .filter(piece -> piece.samePosition(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 위치에 말이 없습니다."));
    }

    public void removePieceByPosition(final Position position) {
        pieces.stream()
                .filter(piece -> piece.samePosition(position))
                .findFirst()
                .ifPresent(pieces::remove);
    }

    public List<Piece> toList() {
        return new ArrayList<>(pieces);
    }

    public boolean kingAlive() {
        return pieces.stream()
                .anyMatch(Piece::isKing);
    }
}
