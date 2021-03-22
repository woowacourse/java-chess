package chess.domain.board;

import java.util.List;

public class Path {
    private static final int SOURCE = 0;
    private static final int TARGET = 1;

    private final Position source;
    private final Position target;

    public Path(List<Position> positions) {
        this.source = positions.get(SOURCE);
        this.target = positions.get(TARGET);
    }

    public Position source() {
        return source;
    }

    public Position target() {
        return target;
    }

    public int computeDistance() {
        return (int) Math.sqrt(
                (source.powAxisX(target) + source.powAxisY(target)));
    }

    public boolean isDiagonal() {
        return Math.abs(source.calculateFileGap(target)) == Math.abs(source.calculateRankGap(target));
    }

    public Direction computeDirection() {
        int x = source.calculateFilePoint(target);
        int y = source.calculateRankPoint(target);
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
        return source.move(direction, step);
    }

    public boolean targetEquals(Position position) {
        return target.equals(position);
    }
}
