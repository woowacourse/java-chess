package chess.domain.board;

import java.util.List;

public class Positions {
    private static final int SOURCE = 0;
    private static final int TARGET = 1;

    private final List<Position> positions;

    public Positions(List<Position> positions) {
        this.positions = positions;
    }

    public Position at(int index) {
        return positions.get(index);
    }

    public Position source() {
        return positions.get(SOURCE);
    }

    public Position target() {
        return positions.get(TARGET);
    }

    public int computeDistance() {
        return (int) Math.sqrt(
                (source().powAxisX(target())+ source().powAxisY(target())));
    }

    public boolean isDiagonal() {
        return Math.abs(source().calculateFileGap(target())) == Math.abs(source().calculateRankGap(target()));
    }

    public Direction computeDirection() {
        int x = source().calculateFilePoint2(target());
        int y = source().calculateRankPoint2(target());
        int divide = Math.abs(gcd(x, y));
        return Direction.of(x / divide, y / divide);
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public Position move(Direction direction, int step) {
        return source().move(direction, step);
    }

    public boolean targetEquals(Position position) {
        return target().equals(position);
    }
}
