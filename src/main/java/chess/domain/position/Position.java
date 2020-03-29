package chess.domain.position;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Position {

    private static final Map<String, Position> CACHE;
    public static final String UNSUPPORT_NOT_ON_DIAGONAL_OR_STRAIGHT_MESSAGE
        = "직선 혹은 대각선상에 있지 않은 두 위치에 대해 경로 찾기 기능을 지원하지 않습니다.";
    public static final String OUT_OF_STRAIGHT_PATH_MESSAGE = "같은 직선상에 있는 두 지점은 X경로,Y경로 중 하나가 반드시 1이어야 합니다.";

    private X x;
    private Y y;

    private Position(X x, Y y) {
        this.x = x;
        this.y = y;
    }

    static {
        Map<String, Position> positions = new HashMap<>();

        for (X x : X.values()) {
            createPositionsByY(positions, x);
        }
        CACHE = Collections.unmodifiableMap(positions);
    }

    private static void createPositionsByY(Map<String, Position> positions, X x) {
        for (Y y : Y.values()) {
            positions.put(getKey(x, y), new Position(x, y));
        }
    }

    private static String getKey(X x, Y y) {
        return x.toString() + y.toString();
    }

    public static List<Position> values() {
        return Collections.unmodifiableList(new ArrayList<>(CACHE.values()));
    }

    public static Position of(X x, Y y) {
        return of(x.toString() + y.toString());
    }

    public static Position of(String expression) {
        if (!CACHE.containsKey(expression)) {
            throw new IllegalArgumentException("존재하지 않는 Position 입니다.");
        }
        return CACHE.get(expression);
    }

    public boolean canIncrease(int x, int y) {
        return this.x.canIncrease(x) && this.y.canIncrease(y);
    }

    public boolean canIncrease(X x, Y y) {
        return this.x.canIncrease(x) && this.y.canIncrease(y);
    }

    public Position increase(int x, int y) {
        if (!canIncrease(x, y)) {
            throw new IllegalArgumentException(
                "현재 위치에서 x 를" + x + "만큼, y를" + y + "만큼 증가할 수 없습니다."
            );
        }
        return Position.of(this.x.increase(x), this.y.increase(y));
    }

    public boolean canDecrease(Position position) {
        return canDecrease(position.x, position.y);
    }

    public boolean canDecrease(X x, Y y) {
        return this.x.canDecrease(x) && this.y.canDecrease(y);
    }

    public boolean canDecrease(int x, int y) {
        return this.x.canDecrease(x) && this.y.canDecrease(y);
    }

    public Position decrease(X x, Y y) {
        if (this.canDecrease(x, y)) {
            throw new IllegalArgumentException(
                "현재 위치에서 x 를" + x + "만큼, y를" + y + "만큼 증가할 수 없습니다."
            );
        }
        return Position.of(this.x.decrease(x), this.y.decrease(y));
    }

    public Position decrease(Position position) {
        return decrease(position.x, position.y);
    }

    public Position decrease(int x, int y) {
        return increase(-x, -y);
    }

    public boolean isOnDiagonalOf(Position position) {
        int xDistance = this.calculateXDistance(position);
        int yDistance = this.calculateYDistance(position);

        return xDistance == yDistance;
    }

    public boolean isOnStraightOf(Position to) {
        return this.x.getInt() == to.x.getInt() || this.y.getInt() == to.y.getInt();
    }

    public int calculateXDistance(Position position) {
        return this.x.calculateDistance(position.x);
    }

    public int calculateYDistance(Position position) {
        return this.y.calculateDistance(position.y);
    }

    /**
     * 직선 혹은 대각선상에 있는 두 Position 에 대해서만 지원
     */
    public static List<Position> getPath(Position from, Position to) {
        List<X> xPath = X.getPathFromTo(from.x, to.x);
        List<Y> yPath = Y.getPathFromTo(from.y, to.y);

        if (xPath.size() == yPath.size()) {    /* diagonal */
            return makeDiagonalPathTo(xPath, yPath);
        }
        if (from.isOnStraightOf(to)) {
            return makeStraightPathTo(xPath, yPath);
        }
        throw new UnsupportedOperationException(UNSUPPORT_NOT_ON_DIAGONAL_OR_STRAIGHT_MESSAGE);
    }

    private static List<Position> makeDiagonalPathTo(List<X> xPath, List<Y> yPath) {
        List<Position> path = new ArrayList<>();

        for (int index = 0; index < xPath.size(); index++) {
            path.add(Position.of(xPath.get(index), yPath.get(index)));
        }
        return path;
    }

    private static List<Position> makeStraightPathTo(List<X> xPath, List<Y> yPath) {
        if (xPath.size() == 1) {    /* 두 Position 이 같은 가로줄 상에 있음 */
            return makeRowStraightPath(xPath, yPath);
        }
        if (yPath.size() == 1) {    /* 두 Position 이 같은 세로줄 상에 있음 */
            return makeColumnStraightPath(xPath, yPath);
        }
        throw new IllegalArgumentException(OUT_OF_STRAIGHT_PATH_MESSAGE);
    }

    private static List<Position> makeRowStraightPath(List<X> xPath, List<Y> yPath) {
        return yPath.stream()
            .map(y -> Position.of(xPath.get(0), y))
            .collect(Collectors.toList());
    }

    private static List<Position> makeColumnStraightPath(List<X> xPath, List<Y> yPath) {
        return xPath.stream()
            .map(x -> Position.of(x, yPath.get(0)))
            .collect(Collectors.toList());
    }

    public boolean isYLargerThan(Position from) {
        return this.y.isLargerThan(from.y);
    }

    public boolean isY(Y y) {
        return this.y == y;
    }

    @Override
    public String toString() {
        return x.toString() + y.toString();
    }
}
