package view;

import domain.type.EmptyType;
import domain.type.Type;
import domain.type.PieceType;

import java.util.Arrays;

public enum ChessBoardElement {

    KING(PieceType.KING, "k"),
    QUEEN(PieceType.QUEEN, "q"),
    KNIGHT(PieceType.KNIGHT, "n"),
    BISHOP(PieceType.BISHOP, "b"),
    ROOK(PieceType.ROOK, "r"),
    PAWN(PieceType.PAWN, "p"),
    EMPTY(EmptyType.EMPTY, ".");

    private final Type type;
    private final String elementName;

    ChessBoardElement(final Type type, final String elementName) {
        this.type = type;
        this.elementName = elementName;
    }

    private static ChessBoardElement from(final Type type) {
        return Arrays.stream(values())
                .filter(value -> value.type == type)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public static String getElementName(final Type type) {
        return from(type).elementName;
    }

}
