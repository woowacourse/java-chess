package chess.domain.piece.property;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.normal.*;
import chess.domain.piece.pawn.Pawn;

import java.util.function.Function;

public enum Kind {
    PAWN(1.0, (Color color) -> new Pawn(color)),
    KING(0.0, (Color color) -> new King(color)),
    QUEEN(9.0, (Color color) -> new Queen(color)),
    BISHOP(3.0, (Color color) -> new Bishop(color)),
    KNIGHT(2.5, (Color color) -> new Knight(color)),
    ROOK(5.0, (Color color) -> new Rook(color)),
    EMPTY(0.0, (Color color) -> new Empty());

    private final double score;
    private final Function<Color, Piece> generatePiece;

    Kind(final double score, final Function<Color, Piece> generatePiece) {
        this.score = score;
        this.generatePiece = generatePiece;
    }

    public Piece toPiece(final String color) {
        return generatePiece.apply(Color.valueOf(color));
    }

    public double getScore() {
        return score;
    }
}
