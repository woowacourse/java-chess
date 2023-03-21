package view;

import domain.chessboard.EmptyType;
import domain.chessboard.Type;
import domain.piece.PieceType;

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

    public static ChessBoardElement from(Type type) {
        return Arrays.stream(values())
                .filter(value -> value.type == type)
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getElementName() {
        return elementName;
    }

}
