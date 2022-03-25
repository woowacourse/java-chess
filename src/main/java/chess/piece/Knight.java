package chess.piece;

import chess.utils.CheckerOfAllPossiblePosition;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

    private static final List<Pair<Integer, Integer>> COORDINATES_OF_MOVABLE = List.of(
            Pair.of(-2, 1),
            Pair.of(-1, 2),
            Pair.of(1, 2),
            Pair.of(1, 1),
            Pair.of(-2, -1),
            Pair.of(1, -2),
            Pair.of(-1, -2),
            Pair.of(-2, -1)
    );

    public Knight(Color color) {
        super(Type.KNIGHT, color);
    }

    @Override
    public List<Pair<Integer, Integer>> computeBetweenTwoPosition(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return new ArrayList<>();
    }

    @Override
    public boolean isMovable(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        return CheckerOfAllPossiblePosition.isMovableCoordinates(COORDINATES_OF_MOVABLE, source, target);
    }
}