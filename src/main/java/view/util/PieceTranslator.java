package view.util;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Color;
import domain.piece.King;
import domain.piece.Knight;
import domain.piece.Queen;
import domain.piece.Rook;
import domain.piece.WhitePawn;
import domain.piece.base.ChessPiece;
import java.util.Arrays;

public enum PieceTranslator {

    KING(King.class, "k"),
    QUEEN(Queen.class, "q"),
    ROOK(Rook.class, "r"),
    BISHOP(Bishop.class, "b"),
    KNIGHT(Knight.class, "n"),
    WHITE_PAWN(WhitePawn.class, "p"),
    BLACK_PAWN(BlackPawn.class, "P"),
    NONE(null, ".");

    private final Class<? extends ChessPiece> classType;
    private final String name;

    PieceTranslator(Class<? extends ChessPiece> classType, String name) {
        this.classType = classType;
        this.name = name;
    }

    public static String getName(ChessPiece piece) {
        if (piece == null) {
            return NONE.name;
        }

        PieceTranslator pieceTranslator = from(piece);
        if (piece.hasSameColor(Color.BLACK)) {
            return pieceTranslator.name.toUpperCase();
        }
        return pieceTranslator.name;
    }

    private static PieceTranslator from(ChessPiece piece) {
        return Arrays.stream(values())
                .filter(piece1 -> piece1.classType == piece.getClass())
                .findAny()
                .orElseThrow();
    }
}
