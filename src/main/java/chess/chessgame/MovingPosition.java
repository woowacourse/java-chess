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

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    private void validateSamePosition(String from, String to) {
        if (from.equals(to)) {
            throw new IllegalArgumentException("현재 위치와 같은 위치로 이동할 수 없습니다.");
        }
    }

    public boolean isLinear() {
        return (from.isSameX(to) || from.isSameY(to));
    }

    public boolean isCross() {
        return from.isCross(to);
    }

    public boolean isSameFromX(int x) {
        return from.isSameX(x);
    }

    public boolean isAnyPossible(List<Position> coordinates) {
        return coordinates.stream()
                .anyMatch(this::isTarget);
    }

    public List<Position> computeLinearMiddle() {
        if (from.isSameX(to)) {
            return x();
        }
        if (from.isSameY(to)) {
            return y();
        }
        return new ArrayList<>();
    }

    public List<Position> computeCrossMiddle() {
        if (from.computeInclination(to) == -1) {
            return rightUp();
        }
        if (from.computeInclination(to) == 1) {
            return rightDown();
        }
        return new ArrayList<>();
    }

    private List<Position> x() {
        return from.computeBetweenSameX(to);
    }

    private List<Position> y() {
        return from.computeBetweenSameY(to);
    }

    private List<Position> rightUp() {
        return from.computeBetweenRightUp(to);
    }

    private List<Position> rightDown() {
        return from.computeBetweenRightDown(to);
    }

    private boolean isTarget(Position coordinate) {
        return from.isTarget(coordinate, to);
    }
}
