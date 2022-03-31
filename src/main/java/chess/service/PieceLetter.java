package chess.service;

import chess.model.piece.Bishop;
import chess.model.piece.Empty;
import chess.model.piece.King;
import chess.model.piece.Knight;
import chess.model.piece.Piece;
import chess.model.piece.Queen;
import chess.model.piece.Rook;
import chess.model.piece.pawn.Pawn;
import java.util.Arrays;

public enum PieceLetter {
    KING(King.class, "k"),
    QUEEN(Queen.class, "q"),
    KNIGHT(Knight.class, "n"),
    ROOK(Rook.class, "r"),
    BISHOP(Bishop.class, "b"),
    PAWN(Pawn.class, "p"),
    EMPTY(Empty.class, ".");

    private final Class<? extends Piece> pieceClass;
    private final String letter;

    PieceLetter(Class<? extends Piece> pieceClass, String letter) {
        this.pieceClass = pieceClass;
        this.letter = letter;
    }

    public static String getLetter(Piece piece) {
        return Arrays.stream(values())
                .filter(pieceLetter -> pieceLetter.pieceClass.isAssignableFrom(piece.getClass()))
                .map(pieceLetter -> convertByColor(piece, pieceLetter.letter))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("표현할 수 없는 기물이 파악됐습니다. " + piece.getClass()));
    }

    private static String convertByColor(Piece piece, String letter) {
        if (piece.isBlack()) {
            return letter.toUpperCase();
        }
        return letter;
    }
}
