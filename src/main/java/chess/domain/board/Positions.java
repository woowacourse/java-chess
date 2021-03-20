package chess.domain.board;

import java.util.Arrays;
import java.util.List;

public class Positions {
    private static final int SOURCE = 0;
    private static final int TARGET = 1;

    private final List<Position> positions;

    public Positions(List<Position> positions) {
        this.positions = positions;
    }

    public Positions(String input) {
        this.positions = splitSourceAndTarget(input);
    }

    private List<Position> splitSourceAndTarget(String command) {
        String[] commandParameters = command.split(" ");
        String source = commandParameters[SOURCE + 1];
        String target = commandParameters[TARGET + 1];

        return Arrays
                .asList(Position.of(convertFileToCoordinate(source), convertRankToCoordinate(source))
                        , Position.of(convertFileToCoordinate(target), convertRankToCoordinate(target)));
    }

    private Rank convertRankToCoordinate(String coordinate) {
        return Rank.of(Integer.parseInt(String.valueOf(coordinate.charAt(1))));
    }

    private File convertFileToCoordinate(String coordinate) {
        return File.of(String.valueOf(coordinate.charAt(0)));
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
