package dto;

public class NavigatorDto {
    private final int gameId;
    private final String startPosition;
    private final String endPosition;

    public NavigatorDto(int gameId, String startPosition, String endPosition) {
        this.gameId = gameId;
        this.startPosition = startPosition;
        this.endPosition = endPosition;
    }

    public int getGameId() {
        return gameId;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }
}
