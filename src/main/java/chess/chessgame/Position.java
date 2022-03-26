package chess.chessgame;

import chess.utils.PositionParser;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class Position {
    private final int fromX;
    private final int fromY;
    private final int toX;
    private final int toY;

    public Position(String from, String to) {
        validateSamePosition(from, to);
        Pair<Integer, Integer> parsedFrom = PositionParser.parse(from.charAt(0), from.charAt(1));
        Pair<Integer, Integer> parsedTo = PositionParser.parse(to.charAt(0), to.charAt(1));

        fromX = parsedFrom.getLeft();
        fromY = parsedFrom.getRight();
        toX = parsedTo.getLeft();
        toY = parsedTo.getRight();
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }


    private void validateSamePosition(String from, String to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("현재 위치와 같은 위치로 이동할 수 없습니다.");
        }
    }

    public boolean isLinear() {
        return (fromX == toX) || (fromY == toY);
    }

    public boolean isCross() {
        return Math.abs(fromX - toX) == Math.abs(fromY - toY);
    }

    public boolean isAnyPossible(List<Pair<Integer, Integer>> coordinates) {
        for (Pair<Integer, Integer> coordinate : coordinates) {
            if (fromX + coordinate.getLeft() == toX && fromY + coordinate.getRight() == toY) {
                return true;
            }
        }
        return false;
    }

    public List<Pair<Integer, Integer>> computeLinearMiddle() {
        if (fromX == toX) {
            return row();
        }
        if (fromY == toY) {
            return col();
        }
        return new ArrayList<>();
    }

    public List<Pair<Integer, Integer>> computeCrossMiddle() {
        if ((fromX - toX) == (-1) * (fromY - toY)) {
            return rightUp();
        }
        if ((fromX - toX) == (fromY - toY)) {
            return rightDown();
        }
        return new ArrayList<>();
    }

    public List<Pair<Integer, Integer>> computeOneUp() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if (fromX - 1 > toX) {
            list.add(Pair.of(fromX - 1, fromY));
        }
        return list;
    }

    public List<Pair<Integer, Integer>> computeOneDown() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        if (fromX + 1 < toX) {
            list.add(Pair.of(fromX + 1, fromY));
        }
        return list;
    }


    private List<Pair<Integer, Integer>> row() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = Math.min(fromY, toY) + 1; i < Math.max(fromY, toY); i++) {
            list.add(Pair.of(fromX, i));
        }
        return list;
    }

    private List<Pair<Integer, Integer>> col() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = Math.min(fromX, toX) + 1; i < Math.max(fromX, toX); i++) {
            list.add(Pair.of(i, fromY));
        }
        return list;
    }

    private List<Pair<Integer, Integer>> rightUp() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        int startX = Math.max(fromX, toX);
        int startY = Math.min(fromY, toY);

        for (int i = 1; i < Math.abs(fromX - toX); i++) {
            list.add(Pair.of(startX - i, startY + i));
        }
        return list;
    }

    private List<Pair<Integer, Integer>> rightDown() {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        int startX = Math.min(fromX, toX);
        int startY = Math.min(fromY, toY);

        for (int i = 1; i < Math.abs(fromX - toX); i++) {
            list.add(Pair.of(startX + i, startY + i));
        }
        return list;
    }
}
