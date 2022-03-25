package chess.utils;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class MovingBetweenPositions {

    public static List<Pair<Integer, Integer>> computeBetweenPositionsOfRow(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (source.getRight() < target.getRight()) {
            return generateRowPositions(source.getLeft(), source.getRight(), target.getRight());
        }
        return generateColumnPositions(source.getLeft(), target.getRight(), source.getRight());
    }

    public static List<Pair<Integer, Integer>> computeBetweenPositionsOfColumn(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (source.getLeft() < target.getLeft()) {
            return generateRowPositions(source.getRight(), source.getLeft(), target.getLeft());
        }
        return generateColumnPositions(source.getRight(), target.getLeft(), source.getLeft());
    }

    public static List<Pair<Integer, Integer>> computeLeftDownRightUp(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (source.getLeft() < target.getLeft()) {
            return generateLeftDownRightUpPositions(source, target);
        }
        return generateLeftDownRightUpPositions(target, source);
    }

    public static List<Pair<Integer, Integer>> computeLeftUpRightDown(Pair<Integer, Integer> source, Pair<Integer, Integer> target) {
        if (source.getLeft() < target.getLeft()) {
            return generateLeftUpRightDownPositions(source, target);
        }
        return generateLeftUpRightDownPositions(target, source);
    }


    private static List<Pair<Integer, Integer>> generateLeftDownRightUpPositions(Pair<Integer, Integer> min, Pair<Integer, Integer> max) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        int count = max.getLeft() - min.getLeft();

        for (int i = 1; i < count; i++) {
            list.add(Pair.of(min.getLeft() + i, min.getRight() - i));
        }

        return list;
    }

    private static List<Pair<Integer, Integer>> generateLeftUpRightDownPositions(Pair<Integer, Integer> min, Pair<Integer, Integer> max) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        int count = max.getLeft() - min.getLeft();

        for (int i = 1; i < count; i++) {
            list.add(Pair.of(min.getLeft() + i, min.getRight() + i));
        }
        return list;
    }

    private static List<Pair<Integer, Integer>> generateRowPositions(int row, int min, int max) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();

        for (int i = min + 1; i < max; i++) {
            list.add(Pair.of(row, i));
        }
        return list;
    }

    private static List<Pair<Integer, Integer>> generateColumnPositions(int col, int min, int max) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();

        for (int i = min + 1; i < max; i++) {
            list.add(Pair.of(i, col));
        }
        return list;
    }
}
