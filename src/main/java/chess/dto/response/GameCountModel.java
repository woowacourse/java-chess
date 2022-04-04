package chess.dto.response;

public class GameCountModel {

    private final int totalCount;
    private final int runningCount;

    public GameCountModel(int totalCount, int runningCount) {
        this.totalCount = totalCount;
        this.runningCount = runningCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getRunningCount() {
        return runningCount;
    }
}
