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

    private File file;
    private Rank rank;

    private Position(File file, Rank rank) {
        this.file = file;
        this.rank = rank;
    }

    static {
        Map<String, Position> positions = new HashMap<>();

        for (File file : File.values()) {
            createPositionsByY(positions, file);
        }
        CACHE = Collections.unmodifiableMap(positions);
    }

    private static void createPositionsByY(Map<String, Position> positions, File file) {
        for (Rank rank : Rank.values()) {
            positions.put(getKey(file, rank), new Position(file, rank));
        }
    }

    private static String getKey(File file, Rank rank) {
        return file.toString() + rank.toString();
    }

    public static List<Position> values() {
        return Collections.unmodifiableList(new ArrayList<>(CACHE.values()));
    }

    public static Position of(File file, Rank rank) {
        return of(file.toString() + rank.toString());
    }

    public static Position of(String expression) {
        if (!CACHE.containsKey(expression)) {
            throw new IllegalArgumentException("존재하지 않는 Position 입니다.");
        }
        return CACHE.get(expression);
    }

    public boolean canIncrease(int x, int y) {
        return this.file.canIncrease(x) && this.rank.canIncrease(y);
    }

    public boolean canIncrease(File file, Rank rank) {
        return this.file.canIncrease(file) && this.rank.canIncrease(rank);
    }

    public Position increase(int x, int y) {
        if (!canIncrease(x, y)) {
            throw new IllegalArgumentException(
                "현재 위치에서 x 를" + x + "만큼, y를" + y + "만큼 증가할 수 없습니다."
            );
        }
        return Position.of(this.file.increase(x), this.rank.increase(y));
    }

    public boolean canDecrease(Position position) {
        return canDecrease(position.file, position.rank);
    }

    public boolean canDecrease(File file, Rank rank) {
        return this.file.canDecrease(file) && this.rank.canDecrease(rank);
    }

    public boolean canDecrease(int x, int y) {
        return this.file.canDecrease(x) && this.rank.canDecrease(y);
    }

    public Position decrease(File file, Rank rank) {
        if (this.canDecrease(file, rank)) {
            throw new IllegalArgumentException(
                "현재 위치에서 x 를" + file + "만큼, y를" + rank + "만큼 증가할 수 없습니다."
            );
        }
        return Position.of(this.file.decrease(file), this.rank.decrease(rank));
    }

    public Position decrease(Position position) {
        return decrease(position.file, position.rank);
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
        return this.file.getInt() == to.file.getInt() || this.rank.getInt() == to.rank.getInt();
    }

    public int calculateXDistance(Position position) {
        return this.file.calculateDistance(position.file);
    }

    public int calculateYDistance(Position position) {
        return this.rank.calculateDistance(position.rank);
    }

    /**
     * 직선 혹은 대각선상에 있는 두 Position 에 대해서만 지원
     */
    public static List<Position> getPath(Position from, Position to) {
        List<File> filePath = File.getPathFromTo(from.file, to.file);
        List<Rank> rankPath = Rank.getPathFromTo(from.rank, to.rank);

        if (filePath.size() == rankPath.size()) {    /* diagonal */
            return makeDiagonalPathTo(filePath, rankPath);
        }
        if (from.isOnStraightOf(to)) {
            return makeStraightPathTo(filePath, rankPath);
        }
        throw new UnsupportedOperationException(UNSUPPORT_NOT_ON_DIAGONAL_OR_STRAIGHT_MESSAGE);
    }

    private static List<Position> makeDiagonalPathTo(List<File> filePath, List<Rank> rankPath) {
        List<Position> path = new ArrayList<>();

        for (int index = 0; index < filePath.size(); index++) {
            path.add(Position.of(filePath.get(index), rankPath.get(index)));
        }
        return path;
    }

    private static List<Position> makeStraightPathTo(List<File> filePath, List<Rank> rankPath) {
        if (filePath.size() == 1) {    /* 두 Position 이 같은 가로줄 상에 있음 */
            return makeRowStraightPath(filePath, rankPath);
        }
        if (rankPath.size() == 1) {    /* 두 Position 이 같은 세로줄 상에 있음 */
            return makeColumnStraightPath(filePath, rankPath);
        }
        throw new IllegalArgumentException(OUT_OF_STRAIGHT_PATH_MESSAGE);
    }

    private static List<Position> makeRowStraightPath(List<File> filePath, List<Rank> rankPath) {
        return rankPath.stream()
            .map(y -> Position.of(filePath.get(0), y))
            .collect(Collectors.toList());
    }

    private static List<Position> makeColumnStraightPath(List<File> filePath, List<Rank> rankPath) {
        return filePath.stream()
            .map(x -> Position.of(x, rankPath.get(0)))
            .collect(Collectors.toList());
    }

    public boolean isYLargerThan(Position from) {
        return this.rank.isLargerThan(from.rank);
    }

    public boolean isY(Rank rank) {
        return this.rank == rank;
    }

    public boolean isX(File file) {
        return this.file == file;
    }


    public List<Position> getSameColumnPositions() {
        return CACHE.values().stream()
            .filter(position -> position.isX(this.file))
            .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return file.toString() + rank.toString();
    }
}
