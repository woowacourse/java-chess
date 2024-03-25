package domain.piece;

import domain.piece.nonpawn.Bishop;
import domain.piece.nonpawn.King;
import domain.piece.nonpawn.Knight;
import domain.piece.nonpawn.Queen;
import domain.piece.nonpawn.Rook;
import domain.piece.pawn.BlackPawn;
import domain.piece.pawn.WhitePawn;

import java.util.function.Function;

public enum Type {
    KING(0.0, King::new),
    QUEEN(9.0, Queen::new),
    ROOK(5.0, Rook::new),
    BISHOP(3.0, Bishop::new),
    KNIGHT(2.5, Knight::new),
    PAWN(1.0, (color) -> {
        if (color.isBlack()) {
            return new BlackPawn();
        }
        return new WhitePawn();
    });

    final double score;
    final Function<Color, Piece> getInstance;

    Type(final double score, final Function<Color, Piece> getInstance) {
        this.score = score;
        this.getInstance = getInstance;
    }

    public Piece getPiece(final Color color) {
        return getInstance.apply(color);
    }

    public double score() {
        return score;
    }
}
