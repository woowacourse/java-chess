package chess.piece;

import chess.chessgame.Position;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private static final List<Pair<Integer, Integer>> COORDINATES_OF_WHITE_DIAGONAL_MOVABLE = List.of(
            Pair.of(-1, -1),
            Pair.of(-1, 1));
    private static final List<Pair<Integer, Integer>> COORDINATES_OF_BLACK_DIAGONAL_MOVABLE = List.of(
            Pair.of(1, -1),
            Pair.of(1, 1));
    private static final List<Pair<Integer, Integer>> COORDINATES_OF_WHITE_FIRST_TURN_MOVABLE = List.of(
            Pair.of(-1, 0),
            Pair.of(-2, 0));
    private static final List<Pair<Integer, Integer>> COORDINATES_OF_BLACK_FIRST_TURN_MOVABLE = List.of(
            Pair.of(1, 0),
            Pair.of(2, 0));
    private static final List<Pair<Integer, Integer>> COORDINATES_OF_WHITE_TURN_MOVABLE = List.of(
            Pair.of(-1, 0));
    private static final List<Pair<Integer, Integer>> COORDINATES_OF_BLACK_TURN_MOVABLE = List.of(
            Pair.of(1, 0));

    public Pawn(Color color) {
        super(Type.PAWN, color);
    }

    @Override
    public boolean isMovable(Position position) {
        if (color == Color.WHITE) {
            return isMovableWhenWhite(position);
        }

        if (color == Color.BLACK) {
            return isMovableWhenBlack(position);
        }
        return false;
    }

    @Override
    public List<Pair<Integer, Integer>> computeMiddlePosition(Position position) {
        if (color == Color.WHITE && isFirstTurn(color, position.getFromX())) {
            return position.computeOneUp();
        }
        if (color == Color.BLACK && isFirstTurn(color, position.getFromX())) {
            return position.computeOneDown();
        }
        return new ArrayList<>();
    }

    public boolean isDiagonal(Position position) {
        if (color == Color.WHITE) {
            return position.isAnyPossible(COORDINATES_OF_WHITE_DIAGONAL_MOVABLE);
        }
        if (color == Color.BLACK) {
            return position.isAnyPossible(COORDINATES_OF_BLACK_DIAGONAL_MOVABLE);
        }
        return false;
    }

    private boolean isMovableWhenWhite(Position position) {
        if (isFirstTurn(color, position.getFromX())) {
            return position.isAnyPossible(COORDINATES_OF_WHITE_FIRST_TURN_MOVABLE);
        }
        return position.isAnyPossible(COORDINATES_OF_WHITE_TURN_MOVABLE);
    }

    private boolean isMovableWhenBlack(Position position) {
        if (isFirstTurn(color, position.getFromX())) {
            return position.isAnyPossible(COORDINATES_OF_BLACK_FIRST_TURN_MOVABLE);
        }

        return position.isAnyPossible(COORDINATES_OF_BLACK_TURN_MOVABLE);
    }

    private boolean isFirstTurn(Color color, int index) {
        if (color == Color.WHITE) {
            return index == 6;
        }
        if (color == Color.BLACK) {
            return index == 1;
        }
        return false;
    }

}