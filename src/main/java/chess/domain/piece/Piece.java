package chess.domain.piece;

import chess.domain.Color;
import chess.domain.Distance;
import java.util.Objects;

public abstract class Piece {
    private final Color color;
    private final PieceType pieceType;

    public Piece(Color color, PieceType pieceType) {
        this.color = color;
        this.pieceType = pieceType;
    }

    public abstract boolean movable(Distance distance, Piece target);

    protected boolean isOpponent(Piece another) {
        return this.color != another.color;
    }

    public boolean matchColor(Color color) {
        return this.color == color;
    }

    public boolean matchType(PieceType pieceType) {
        return this.pieceType == pieceType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return color == piece.color;
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }
}
