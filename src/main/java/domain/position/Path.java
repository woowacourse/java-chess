package domain.position;

import java.util.ArrayList;
import java.util.List;

public class Path {
    private final List<Position> positions = new ArrayList<>();

    public Path(Position start, Position end) {
        makePathTo(start, end);
    }

    public void makePathTo(Position start, Position end) {
        if (Direction.of(start, end) == Direction.OTHER) {
            positions.add(end);
            return;
        }
        calculatePath(start, end);
    }

    private void calculatePath(Position start, Position end) {
        int rowGap = start.calculateRowGap(end);
        int columnGap = start.calculateColumnGap(end);

        int unit = Math.max(Math.abs(rowGap), Math.abs(columnGap));
        int rowCoefficient = rowGap / unit;
        int columnCoefficient = columnGap / unit;
        for (int i = 1; i <= unit; i++) {
            positions.add(start.moveByGap(rowCoefficient * i, columnCoefficient * i));
        }
    }

    public int size() {
        return positions.size();
    }

    public List<Position> subListFirstTo(int exclusiveIndex) {
        return positions.subList(0, exclusiveIndex);
    }

    public Position getFirstPosition() {
        return positions.get(0);
    }

    public Position getEndPosition() {
        return positions.get(positions.size() - 1);
    }

    public List<Position> getPositions() {
        return positions;
    }
}
