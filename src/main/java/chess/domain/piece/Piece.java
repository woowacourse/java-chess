package chess.domain.piece;

import chess.domain.position.Position;
import java.util.Objects;

public abstract class Piece {

    private final Camp camp;
    private final PieceSymbol pieceSymbol;

    Piece(Camp camp, PieceSymbol pieceSymbol) {
        this.camp = camp;
        this.pieceSymbol = pieceSymbol;
    }

    public boolean isBlack() {
        return camp == Camp.BLACK;
    }

    public abstract boolean isMovable(Position from, Position to);

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Piece piece = (Piece) o;
        return camp == piece.camp;
    }

    @Override
    public int hashCode() {
        return Objects.hash(camp);
    }

    public PieceSymbol getPieceSymbol() {
        return pieceSymbol;
    }
}
