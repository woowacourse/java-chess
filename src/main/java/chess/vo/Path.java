package chess.vo;

import java.util.List;
import java.util.stream.Collectors;

public class Path {

    private static final int UNIT_DISTANCE = 1;

    private final Position source;
    private final Position target;

    public Path(Position source, Position target) {
        this.source = source;
        this.target = target;
    }

    public boolean isUpStraight(int amount) {
        return isForward(source, target, amount);
    }

    public boolean isDownStraight(int amount) {
        return isForward(target, source, amount);
    }

    public boolean isUpDiagonal() {
        return isDiagonal() && source.rankDisplacement(target) == UNIT_DISTANCE
            && source.fileDistance(target) == UNIT_DISTANCE;
    }

    public boolean isDownDiagonal() {
        return isDiagonal() && target.rankDisplacement(source) == UNIT_DISTANCE
            && source.fileDistance(target) == UNIT_DISTANCE;
    }

    private boolean isForward(Position source, Position target, int amount) {
        return source.rankDisplacement(target) >= UNIT_DISTANCE
            && source.rankDisplacement(target) <= amount && source.isSameFile(target);
    }

    public boolean isSourceRankOf(Rank sourceRank) {
        return source.isRankOf(sourceRank);
    }

    public int fileDistance() {
        return source.fileDistance(target);
    }

    public int rankDistance() {
        return source.rankDistance(target);
    }

    public boolean isDiagonal() {
        return fileDistance() == rankDistance();
    }

    public boolean isCross() {
        return source.isSameRank(target) || source.isSameFile(target);
    }

    public boolean isAllDirectional() {
        return isDiagonal() || isCross();
    }

    public List<Position> possiblePositions() {
        return source.between(target).stream()
            .filter(position -> isAllDirectional())
            .collect(Collectors.toUnmodifiableList());
    }
}
