package chess.domain.piece;

import chess.domain.square.Square;

import java.util.Objects;

public abstract class Piece {
    private final PieceColor color;
    private final Square square;

    public Piece(PieceColor color, Square square) {
        this.color = color;
        this.square = square;
    }

    public abstract void move(Square target);

    public abstract PieceType getType();

    public boolean isLocated(Square other) {
        return square.equals(other);
    }

    public PieceColor getColor() {
        return color;
    }

    public Square getSquare() {
        return square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Piece piece = (Piece) o;
        return color == piece.color && Objects.equals(square, piece.square);
    }
}
