package chess.piece;

import chess.position.Position;
import java.util.Collections;
import java.util.List;

public class Pieces {

    private final List<Piece> pieces;

    public Pieces(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public Piece findByPosition(Position position) {
        return pieces.stream()
                .filter(piece -> piece.getPosition().equals(position))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("말이 존재하지 않습니다."));
    }

    public boolean existsByPosition(Position position) {
        return pieces.stream()
                .anyMatch(piece -> piece.getPosition().equals(position));
    }

    public List<Piece> getPieces() {
        return Collections.unmodifiableList(pieces);
    }
}
