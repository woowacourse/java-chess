package view;

import domain.Type;

import java.util.Arrays;

public enum ChessBoardElement {

    KING(Type.KING, "k"),
    QUEEN(Type.QUEEN, "q"),
    KNIGHT(Type.KNIGHT, "n"),
    BISHOP(Type.BISHOP, "b"),
    ROOK(Type.ROOK, "r"),
    PAWN(Type.PAWN, "p"),
    NONE(Type.NONE, "."),
    ;

    private final Type type;
    private final String elementName;

    ChessBoardElement(final Type type, final String elementName) {
        this.type = type;
        this.elementName = elementName;
    }

    public static ChessBoardElement from(Type type) {
        return Arrays.stream(values())
                .filter(value -> type == value.type)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getElementName() {
        return elementName;
    }

}
