package chess.domain.chessboard.attribute;

import java.util.Optional;

import chess.domain.piece.Piece;

public class Square {

    private final Piece piece;

    private Square(final Piece piece) {
        this.piece = piece;
    }

    public static Square empty() {
        return new Square(null);
    }

    public static Square from(final Piece piece) {
        return new Square(piece);
    }

    public Optional<Piece> piece() {
        return Optional.ofNullable(piece);
    }
}
