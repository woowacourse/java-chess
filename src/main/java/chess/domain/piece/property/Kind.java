package chess.domain.piece.property;

import chess.domain.piece.Empty;
import chess.domain.piece.Piece;
import chess.domain.piece.normal.*;
import chess.domain.piece.pawn.Pawn;

import java.util.function.Function;

public enum Kind {
    PAWN((Color color) -> new Pawn(color)),
    KING((Color color) -> new King(color)),
    QUEEN((Color color) -> new Queen(color)),
    BISHOP((Color color) -> new Bishop(color)),
    KNIGHT((Color color) -> new Knight(color)),
    ROOK((Color color) -> new Rook(color)),
    EMPTY((Color color) -> new Empty());

    private final Function<Color, Piece> generatePiece;

    Kind(final Function<Color, Piece> generatePiece) {
        this.generatePiece = generatePiece;
    }

    public Piece toPiece(final String color) {
        return generatePiece.apply(Color.valueOf(color));
    }
}
