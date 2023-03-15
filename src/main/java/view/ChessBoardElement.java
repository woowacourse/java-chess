package view;

import domain.chessboard.Empty;
import domain.chessboard.SquareStatus;
import domain.piece.*;

import java.util.Arrays;

public enum ChessBoardElement {

    KING(King.class, "k"),
    QUEEN(Queen.class, "q"),
    KNIGHT(Knight.class, "n"),
    BISHOP(Bishop.class, "b"),
    ROOK(Rook.class, "r"),
    PAWN(Pawn.class, "p"),
    EMPTY(Empty.class, "."),
    ;

    private final Class squareStatusClass;
    private final String elementName;

    ChessBoardElement(final Class squareStatusClass, final String elementName) {
        this.squareStatusClass = squareStatusClass;
        this.elementName = elementName;
    }

    public static ChessBoardElement from(SquareStatus squareStatus) {
        return Arrays.stream(values())
                .filter(value -> value.squareStatusClass == squareStatus.getClass())
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    public String getElementName() {
        return elementName;
    }

}
