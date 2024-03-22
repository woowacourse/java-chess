package chess.domain.chessboard.attribute.square;

import java.util.Optional;

import chess.domain.piece.Piece;

public class Square {

    private final Piece piece;

    public Square() {
        piece = null;
    }

    public Square(final Piece piece) {
        this.piece = piece;
    }

    public Optional<Piece> piece() {
        return Optional.ofNullable(piece);
    }
}
