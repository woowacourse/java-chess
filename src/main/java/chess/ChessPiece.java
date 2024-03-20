package chess;

import java.util.List;
import java.util.function.BiPredicate;

import static chess.Direction.*;

public enum ChessPiece {
    BLACK_KING("K",List.of(UP,DOWN,LEFT,RIGHT,UP_LEFT,UP_RIGHT,DOWN_LEFT,DOWN_RIGHT)),
    WHITE_KING("k",List.of(UP,DOWN,LEFT,RIGHT,UP_LEFT,UP_RIGHT,DOWN_LEFT,DOWN_RIGHT)),
    BLACK_QUEEN("Q",List.of(UP,DOWN,LEFT,RIGHT,UP_LEFT,UP_RIGHT,DOWN_LEFT,DOWN_RIGHT)),
    WHITE_QUEEN("q",List.of(UP,DOWN,LEFT,RIGHT,UP_LEFT,UP_RIGHT,DOWN_LEFT,DOWN_RIGHT)),
    BLACK_ROOK("R",List.of(UP,DOWN,LEFT,RIGHT)),
    WHITE_ROOK("r",List.of(UP,DOWN,LEFT,RIGHT)),
    BLACK_BISHOP("B",List.of(UP_LEFT,UP_RIGHT,DOWN_LEFT,DOWN_RIGHT)),
    WHITE_BISHOP("b",List.of(UP_LEFT,UP_RIGHT,DOWN_LEFT,DOWN_RIGHT)),
    BLACK_KNIGHT("N",List.of()),
    WHITE_KNIGHT("n",List.of()),
    BLACK_PAWN("P",List.of(DOWN,DOWN_LEFT,DOWN_RIGHT)),
    WHITE_PAWN("p",List.of(UP,UP_LEFT,UP_RIGHT)),
    NONE(".",List.of());

    private final String name;
    private final List<Direction> movableDirection;
    ChessPiece(String name,List<Direction> movableDirection) {
        this.name = name;
        this.movableDirection=movableDirection;
    }

    public String getName() {
        return name;
    }


}
