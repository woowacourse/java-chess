package chess.domain.board;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Position {


    private final FileCoordinate fileCoordinate;
    private final RankCoordinate rankCoordinate;

    public Position(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        this.fileCoordinate = fileCoordinate;
        this.rankCoordinate = rankCoordinate;
    }

    public List<Position> findPath(Position targetPosition) {
        int nowFileCoordinate = this.getColumn();
        int nowRankCoordinate = this.getRow();
        int targetFileCoordinate = targetPosition.getColumn();
        int targetRankCoordinate = targetPosition.getRow();

        if (nowFileCoordinate == targetFileCoordinate) {
            return getPathByRank(targetRankCoordinate);
        }
        if (nowRankCoordinate == targetRankCoordinate) {
            return getPathByFile(targetFileCoordinate);
        }
        if (Math.abs(nowFileCoordinate - targetFileCoordinate) == Math.abs(nowRankCoordinate - targetRankCoordinate)) {
            return getPathByDiagonal(targetFileCoordinate, targetRankCoordinate);
        }
        return Collections.emptyList();
    }

    private List<Position> getPathByDiagonal(int targetFileCoordinate, int targetRankCoordinate) {
        List<Position> paths = new ArrayList<>();
        int nowFileCoordinate = this.getColumn();
        int nowRankCoordinate = this.getRow();

        int columnStep = getStep(nowFileCoordinate, targetFileCoordinate);
        int rowStep = getStep(nowRankCoordinate, targetRankCoordinate);

        while (nowFileCoordinate + columnStep != targetFileCoordinate) {
            nowFileCoordinate += columnStep;
            nowRankCoordinate += rowStep;
            Position position = new Position(FileCoordinate.findBy(nowFileCoordinate),
                    RankCoordinate.findBy(nowRankCoordinate));
            paths.add(position);
        }
        return paths;
    }

    private List<Position> getPathByFile(int targetFileCoordinate) {
        List<Position> paths = new ArrayList<>();
        int nowFileCoordinate = this.getColumn();
        int columnStep = getStep(nowFileCoordinate, targetFileCoordinate);
        while (nowFileCoordinate + columnStep != targetFileCoordinate) {
            nowFileCoordinate += columnStep;
            Position position = new Position(FileCoordinate.findBy(nowFileCoordinate),
                    this.getRankCoordinate());
            paths.add(position);
        }
        return paths;
    }

    private List<Position> getPathByRank(int targetRankCoordinate) {
        List<Position> paths = new ArrayList<>();
        int nowRankCoordinate = this.getRow();
        int rowStep = getStep(nowRankCoordinate, targetRankCoordinate);
        while (nowRankCoordinate + rowStep != targetRankCoordinate) {
            nowRankCoordinate += rowStep;
            Position position = new Position(this.getFileCoordinate(),
                    RankCoordinate.findBy(nowRankCoordinate));
            paths.add(position);
        }
        return paths;
    }

    private int getStep(int nowCoordinate, int targetCoordinate) {
        if (nowCoordinate - targetCoordinate > 0) {
            return -1;
        }
        return 1;
    }

    public FileCoordinate getFileCoordinate() {
        return fileCoordinate;
    }

    public RankCoordinate getRankCoordinate() {
        return rankCoordinate;
    }

    public int getColumn() {
        return fileCoordinate.getColumnNumber();
    }

    public int getRow() {
        return rankCoordinate.getRowNumber();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Position position = (Position) o;
        return fileCoordinate == position.fileCoordinate && rankCoordinate == position.rankCoordinate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileCoordinate, rankCoordinate);
    }
}
