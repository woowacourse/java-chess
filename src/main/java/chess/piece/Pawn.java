package chess.piece;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

import static chess.utils.CheckerOfAllPossiblePosition.isMovableCoordinates;

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

    public boolean isDiagonal(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (color == Color.WHITE) {
            return isMovableCoordinates(COORDINATES_OF_WHITE_DIAGONAL_MOVABLE, source, target);
        }
        return isMovableCoordinates(COORDINATES_OF_BLACK_DIAGONAL_MOVABLE, source, target);
    }

    @Override
    public List<Pair<Integer, Integer>> computeBetweenTwoPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (isFirstTurn(color, source.getLeft())) {
            if (color == Color.WHITE) {
                return List.of(Pair.of(source.getLeft() - 1, source.getRight()));
            }
            if (color == Color.BLACK) {
                return List.of(Pair.of(source.getLeft() + 1, source.getRight()));
            }
        }
        return new ArrayList<>();
    }

    @Override
    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (color == Color.WHITE) {
            return isMovableWhenWhite(source, target);
        }

        if (color == Color.BLACK) {
            return isMovableWhenBlack(source, target);
        }
        return false;
    }

    private boolean isMovableWhenWhite(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (isFirstTurn(color, source.getLeft())) {
            return isMovableCoordinates(COORDINATES_OF_WHITE_FIRST_TURN_MOVABLE, source, target);
        }
        return isMovableCoordinates(COORDINATES_OF_WHITE_TURN_MOVABLE, source, target);
    }

    private boolean isMovableWhenBlack(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (isFirstTurn(color, source.getLeft())) {
            return isMovableCoordinates(COORDINATES_OF_BLACK_FIRST_TURN_MOVABLE, source, target);
        }

        return isMovableCoordinates(COORDINATES_OF_BLACK_TURN_MOVABLE, source, target);
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