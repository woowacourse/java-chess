package chess.view.dto;

import java.util.Arrays;
import java.util.function.Predicate;

import chess.domain.attribute.Color;
import chess.domain.piece.AbstractPawn;
import chess.domain.piece.Bishop;
import chess.domain.piece.King;
import chess.domain.piece.Knight;
import chess.domain.piece.Piece;
import chess.domain.piece.Queen;
import chess.domain.piece.Rook;

public enum PieceParser {
    KING("k", piece -> piece instanceof King),
    QUEEN("q", piece -> piece instanceof Queen),
    BISHOP("b", piece -> piece instanceof Bishop),
    KNIGHT("n", piece -> piece instanceof Knight),
    ROOK("r", piece -> piece instanceof Rook),
    PAWN("p", piece -> piece instanceof AbstractPawn)
    ;

    private final static String NONE = ".";

    private final String value;
    private final Predicate<Object> pieceTypeChecker;

    PieceParser(final String value, final Predicate<Object> pieceTypeChecker) {
        this.value = value;
        this.pieceTypeChecker = pieceTypeChecker;
    }

    public static String parse(final Color color, final Piece other) {
        return Arrays.stream(values())
                .filter(piece -> piece.pieceTypeChecker.test(other))
                .map(piece -> valueOf(color, piece.value))
                .findFirst()
                .orElse(NONE);
    }

    private static String valueOf(final Color color, final String value) {
        if (color == Color.BLACK) {
            return value.toUpperCase();
        }
        return value;
    }
}
