package chess.dto;

import java.util.Objects;

public class GameCountDto {

    private final int totalCount;
    private final int runningCount;

    public GameCountDto(int totalCount, int runningCount) {
        this.totalCount = totalCount;
        this.runningCount = runningCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getRunningCount() {
        return runningCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameCountDto that = (GameCountDto) o;
        return totalCount == that.totalCount
                && runningCount == that.runningCount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(totalCount, runningCount);
    }

    @Override
    public String toString() {
        return "GameCountDto{" + "totalCount=" + totalCount + ", runningCount=" + runningCount + '}';
    }
}
