package chess.domain.chessboard.attribute;

import java.util.Objects;

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
        return new Square(Objects.requireNonNull(piece));
    }

    public boolean isEmpty() {
        return piece == null;
    }

    public Piece piece() {
        return Objects.requireNonNull(piece, "해당 칸에 기물이 없습니다.");
    }
}
