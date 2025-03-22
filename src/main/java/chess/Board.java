package chess;

import chess.piece.Piece;
import java.util.List;
import java.util.Optional;

public class Board {
    private final List<Piece> pieces;

    public Board(List<Piece> pieces) {
        this.pieces = pieces;
    }


    public Optional<Piece> findByPosition(Position newPosition) {
        return pieces.stream()
                .filter(piece -> piece.isPositionEquals(newPosition))
                .findFirst();
    }
}
