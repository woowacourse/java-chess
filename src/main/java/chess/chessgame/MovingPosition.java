package chess.chessgame;

import chess.utils.PositionParser;

import java.util.ArrayList;
import java.util.List;

public class MovingPosition {

    private final Position from;
    private final Position to;

    public MovingPosition(String from, String to) {
        validateSamePosition(from, to);
        this.from = PositionParser.parse(from.charAt(0), from.charAt(1));
        this.to = PositionParser.parse(to.charAt(0), to.charAt(1));
    }

    public int getFromX() {
        return from.getX();
    }

    public int getFromY() {
        return from.getY();
    }

    public int getToX() {
        return to.getX();
    }

    public int getToY() {
        return to.getY();
    }

    private void validateSamePosition(String from, String to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("현재 위치와 같은 위치로 이동할 수 없습니다.");
        }
    }

    public boolean isLinear() {
        return (from.getX() == to.getX()) || (from.getY() == to.getY());
    }

    public boolean isCross() {
        return Math.abs(from.getX() - to.getX()) == Math.abs(from.getY() - to.getY());
    }

    public boolean isAnyPossible(List<Position> coordinates) {
        return coordinates.stream()
                .anyMatch(this::isTarget);
    }

    public List<Position> computeLinearMiddle() {
        if (from.getX() == to.getX()) {
            return row();
        }
        if (from.getY() == to.getY()) {
            return col();
        }
        return new ArrayList<>();
    }

    public List<Position> computeCrossMiddle() {
        if ((from.getX() - to.getX()) == (-1) * (from.getY() - to.getY())) {
            return rightUp();
        }
        if ((from.getX() - to.getX()) == (from.getY() - to.getY())) {
            return rightDown();
        }
        return new ArrayList<>();
    }

    public List<Position> computeOneUp() {
        List<Position> list = new ArrayList<>();
        if (from.getX() - 1 > to.getX()) {
            list.add(new Position(from.getX() - 1, from.getY()));
        }
        return list;
    }

    public List<Position> computeOneDown() {
        List<Position> list = new ArrayList<>();
        if (from.getX() + 1 < to.getX()) {
            list.add(new Position(from.getX() + 1, from.getY()));
        }
        return list;
    }


    private List<Position> row() {
        List<Position> list = new ArrayList<>();
        for (int i = Math.min(from.getY(), to.getY()) + 1; i < Math.max(from.getY(), to.getY()); i++) {
            list.add(new Position(from.getX(), i));
        }
        return list;
    }

    private List<Position> col() {
        List<Position> list = new ArrayList<>();
        for (int i = Math.min(from.getX(), to.getX()) + 1; i < Math.max(from.getX(), to.getX()); i++) {
            list.add(new Position(i, from.getY()));
        }
        return list;
    }

    private List<Position> rightUp() {
        List<Position> list = new ArrayList<>();
        int startX = Math.max(from.getX(), to.getX());
        int startY = Math.min(from.getY(), to.getY());

        for (int i = 1; i < Math.abs(from.getX() - to.getX()); i++) {
            list.add(new Position(startX - i, startY + i));
        }
        return list;
    }

    private List<Position> rightDown() {
        List<Position> list = new ArrayList<>();
        int startX = Math.min(from.getX(), to.getX());
        int startY = Math.min(from.getY(), to.getY());

        for (int i = 1; i < Math.abs(from.getX() - to.getX()); i++) {
            list.add(new Position(startX + i, startY + i));
        }
        return list;
    }

    private boolean isTarget(Position coordinate) {
        return from.getX() + coordinate.getX() == to.getX() && from.getY() + coordinate.getY() == to.getY();
    }
}
