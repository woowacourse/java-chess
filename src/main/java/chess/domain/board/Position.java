package chess.domain.board;

import java.util.Objects;

public class Position {

    private final FileCoordinate fileCoordinate;
    private final RankCoordinate rankCoordinate;

    public Position(FileCoordinate fileCoordinate, RankCoordinate rankCoordinate) {
        this.fileCoordinate = fileCoordinate;
        this.rankCoordinate = rankCoordinate;
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
