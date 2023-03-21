package chess.domain.position;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Move {

    private final Position source;
    private final Position target;

    public Move(Position source, Position target) {
        validate(source, target);
        this.source = source;
        this.target = target;
    }

    private void validate(Position source, Position target) {
        if (source.equals(target)) {
            throw new IllegalArgumentException("동일한 포지션으로의 움직임을 생성할 수 없습니다.");
        }
    }

    public boolean isStraight() {
        return getDeltaFile() * getDeltaRank() == 0;
    }

    public boolean isDiagonal() {
        return Math.abs(getDeltaFile()) == Math.abs(getDeltaRank());
    }

    public int getDeltaFile() {
        return target.getFileIndex() - source.getFileIndex();
    }

    public int getDeltaRank() {
        return target.getRankIndex() - source.getRankIndex();
    }

    public Set<Position> findRoute() {
        if (!(isStraight() || isDiagonal())) {
            return Collections.emptySet();
        }
        int unit = findUnit();
        Set<Position> route = new HashSet<>();
        for (int i = 1; i < unit; i++) {
            route.add(calculateEachRoute(unit, i));
        }
        return route;
    }

    private int findUnit() {
        return Math.max(Math.abs(getDeltaFile()), Math.abs(getDeltaRank()));
    }

    private Position calculateEachRoute(int unit, int index) {
        int deltaFile = getDeltaFile() / unit * index;
        int deltaRank = getDeltaRank() / unit * index;
        return source.move(deltaFile, deltaRank);
    }

    public Position getSource() {
        return source;
    }
}
