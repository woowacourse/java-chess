package chess.domain;

import java.util.ArrayList;
import java.util.List;

public class Path {

    public static final int PAWN_START_PATH_COUNT = 2;
    private final List<Position> positions;

    public Path(final List<Position> positions) {
        this.positions = positions;
    }

    public static Path ofSinglePath(final Position current, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        Position next = current.findNextPosition(direction);
        if (next != null) {
            positions.add(next);
        }
        return new Path(positions);
    }

    public static Path ofPawnStartPath(Position current, Direction direction) {
        List<Position> positions = new ArrayList<>();
        while (positions.size() < PAWN_START_PATH_COUNT) {
            current = current.findNextPosition(direction);
            positions.add(current);
        }
        return new Path(positions);
    }

    public static Path ofMultiPath(Position current, final Direction direction) {
        List<Position> positions = new ArrayList<>();
        while ((current = current.findNextPosition(direction)) != null) {
            positions.add(current);
        }
        return new Path(positions);
    }

    public boolean hasPosition(Position position) {
        return positions.contains(position);
    }

    public int findPositionIndex(Position position) {
        if (positions.contains(position)) {
            return positions.indexOf(position);
        }
        throw new IllegalArgumentException("경로에 존재하지 않는 위치입니다.");
    }

    public Position findByIndex(int index) {
        if (index < 0 || index >= positions.size()) {
            throw new IllegalArgumentException("잘못된 범위의 인덱스로 위치를 조회할 수 없습니다.");
        }
        return positions.get(index);
    }

    public List<Position> positions() {
        return new ArrayList<>(positions);
    }

    public int size() {
        return positions.size();
    }

}
