package chess.domain.chesspiece;

import chess.domain.move.Direction;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static chess.domain.move.Direction.*;

public enum ChessPieceInfo {
    KING("k", 0, Arrays.asList(
            UP,
            DOWN,
            LEFT,
            RIGHT,
            LEFT_DOWN,
            LEFT_UP,
            RIGHT_DOWN,
            RIGHT_UP
    )),
    QUEEN("q", 9, Arrays.asList(
            UP,
            DOWN,
            LEFT,
            RIGHT,
            LEFT_DOWN,
            LEFT_UP,
            RIGHT_DOWN,
            RIGHT_UP
    )),
    KNIGHT("n", 2.5, Collections.EMPTY_LIST),
    BISHOP("b", 3, Arrays.asList(
            LEFT_DOWN,
            LEFT_UP,
            RIGHT_DOWN,
            RIGHT_UP
    )),
    ROOK("r", 5, Arrays.asList(
            UP,
            DOWN,
            LEFT,
            RIGHT
    )),
    PAWN("p", 1, Arrays.asList(
            UP,
            LEFT_UP,
            RIGHT_UP
    )),
    BLANK(".", 0, Collections.EMPTY_LIST);

    private String name;
    private double point;
    List<Direction> moveDirections;

    ChessPieceInfo(String name, double point, List<Direction> moveDirections) {
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

    public List<Direction> getMoveDirections() {
        return moveDirections;
    }
}
