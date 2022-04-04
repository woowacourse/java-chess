package chess.console.service;

import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.pawn.Pawn;
import java.util.Arrays;
import java.util.function.Function;

public enum PieceLetter {
    KING(King.class, "k", "king"),
    QUEEN(Queen.class, "q", "queen"),
    KNIGHT(Knight.class, "n", "knight"),
    ROOK(Rook.class, "r", "rook"),
    BISHOP(Bishop.class, "b", "bishop"),
    PAWN(Pawn.class, "p", "pawn"),
    EMPTY(Empty.class, ".", "nothing");

    private final Class<? extends Piece> pieceClass;
    private final String letter;
    private final String name;

    PieceLetter(Class<? extends Piece> pieceClass, String letter, String name) {
        this.pieceClass = pieceClass;
        this.letter = letter;
        this.name = name;
    }

    public static String getLetterByColor(Piece piece) {
        return convertByColor(piece, getLetter(piece));
    }

    private static String convertByColor(Piece piece, String letter) {
        if (piece.isBlack()) {
            return letter.toUpperCase();
        }
        return letter;
    }

    private static String getLetter(Piece piece) {
        return getStringByPieceLetter(piece, pieceLetter -> pieceLetter.letter);
    }

    private static String getStringByPieceLetter(Piece piece, Function<PieceLetter, String> letterMapping) {
        return Arrays.stream(values())
                .filter(pieceLetter -> pieceLetter.pieceClass.isAssignableFrom(piece.getClass()))
                .map(letterMapping)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("표현할 수 없는 기물이 파악됐습니다. " + piece.getClass()));
    }

    public static String getName(Piece piece) {
        return getStringByPieceLetter(piece, pieceLetter -> pieceLetter.name);
    }
}
