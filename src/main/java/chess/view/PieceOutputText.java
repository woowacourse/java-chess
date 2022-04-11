package chess.view;

import chess.domain.board.Color;
import chess.domain.board.Piece;
import java.util.Arrays;
import java.util.function.Predicate;

public enum PieceOutputText {

    Pawn("P", piece -> piece.isSameType(chess.domain.board.Pawn.class)),
    Bishop("B", piece -> piece.isSameType(chess.domain.board.Bishop.class)),
    Knight("N", piece -> piece.isSameType(chess.domain.board.Knight.class)),
    ROOK("R", piece -> piece.isSameType(chess.domain.board.Rook.class)),
    Queen("Q", piece -> piece.isSameType(chess.domain.board.Queen.class)),
    KING("K", piece -> piece.isSameType(chess.domain.board.King.class)),
    ;

    private static final String EMPTY_TEXT = ".";

    private final String value;
    private final Predicate<Piece> condition;

    PieceOutputText(String value, Predicate<Piece> condition) {
        this.value = value;
        this.condition = condition;
    }

    public static String of(Piece piece) {
        if (piece == null) {
            return EMPTY_TEXT;
        }

        String result = findOutputTextBy(piece);
        if (piece.isSameColor(Color.WHITE)) {
            result = result.toLowerCase();
        }
        return result;
    }

    private static String findOutputTextBy(Piece piece) {
        return Arrays.stream(PieceOutputText.values())
                .filter(text -> text.condition.test(piece))
                .map(text -> text.value)
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("error"));
    }
}
