package view.util;

import domain.piece.Bishop;
import domain.piece.BlackPawn;
import domain.piece.Blank;
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
    NONE(Blank.class, ".");

    private final Class<? extends ChessPiece> classType;
    private final String name;

    PieceTranslator(Class<? extends ChessPiece> classType, String name) {
        this.classType = classType;
        this.name = name;
    }

    public static PieceTranslator from(ChessPiece chessPiece) {
        return Arrays.stream(values())
                .filter(type -> type.isSameType(chessPiece))
                .findAny()
                .orElseThrow();
    }

    private boolean isSameType(ChessPiece chessPiece) {
        return this.classType.isInstance(chessPiece);
    }

    public String getName() {
        return name;
    }
}
