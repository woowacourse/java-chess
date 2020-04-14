package chess.model.dto;

public class GameResultDto {

    private final int winCount;
    private final int drawCount;
    private final int loseCount;
    private final int totalCount;

    public GameResultDto(int winCount, int drawCount, int lose) {
        this.winCount = winCount;
        this.drawCount = drawCount;
        this.loseCount = lose;
        this.totalCount = winCount + drawCount + lose;
    }

    public int getWinCount() {
        return winCount;
    }

    public int getDrawCount() {
        return drawCount;
    }

    public int getLoseCount() {
        return loseCount;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
