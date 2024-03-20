package view.util;

import java.util.Arrays;
import piece.Bishop;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public enum PieceTranslator {

    KING(King.class, "k"),
    QUEEN(Queen.class, "q"),
    ROOK(Rook.class, "r"),
    BISHOP(Bishop.class, "b"),
    KNIGHT(Knight.class, "n"),
    PAWN(Pawn.class, "p"),
    NONE(null, ".");

    private final Class<? extends Piece> classType;
    private final String name;

    PieceTranslator(Class<? extends Piece> classType, String name) {
        this.classType = classType;
        this.name = name;
    }

    public static String getName(Piece piece) {
        if (piece == null) {
            return NONE.name;
        }

        PieceTranslator pieceTranslator = from(piece);
        if (piece.isBlack()) {
            return pieceTranslator.name.toUpperCase();
        }
        return pieceTranslator.name;
    }

    private static PieceTranslator from(Piece piece) {
        return Arrays.stream(values())
                .filter(piece1 -> piece1.classType == piece.getClass())
                .findAny()
                .orElseThrow();
    }
}
