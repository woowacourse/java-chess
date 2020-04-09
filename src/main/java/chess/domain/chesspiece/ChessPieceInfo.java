package chess.domain.chesspiece;

import chess.domain.move.Direction;

import static chess.domain.move.Direction.*;

public enum ChessPieceInfo {
    KING("k", 0,
            UP,
            DOWN,
            LEFT,
            RIGHT,
            LEFT_DOWN,
            LEFT_UP,
            RIGHT_DOWN,
            RIGHT_UP
    ),
    QUEEN("q", 9,
            UP,
            DOWN,
            LEFT,
            RIGHT,
            LEFT_DOWN,
            LEFT_UP,
            RIGHT_DOWN,
            RIGHT_UP
    ),
    KNIGHT("n", 2.5),
    BISHOP("b", 3,
            LEFT_DOWN,
            LEFT_UP,
            RIGHT_DOWN,
            RIGHT_UP
    ),
    ROOK("r", 5,
            UP,
            DOWN,
            LEFT,
            RIGHT
    ),
    PAWN("p", 1,
            UP,
            LEFT_UP,
            RIGHT_UP
    ),
    BLANK(".", 0);

    private final String name;
    private final double point;
    private final Direction[] moveDirections;

    ChessPieceInfo(String name, double point, Direction... moveDirections) {
        this.name = name;
        this.point = point;
        this.moveDirections = moveDirections;
    }

    public String getName() {
        return name;
    }

    public double getPoint() {
        return point;
    }

    public Direction[] getMoveDirections() {
        return moveDirections;
    }
}
