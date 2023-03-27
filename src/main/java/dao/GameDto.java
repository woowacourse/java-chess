package dao;

public class GameDto {
    private final String gameId;
    private final String userName;
    private final String title;

    public GameDto(String gameId, String userName, String title) {
        this.gameId = gameId;
        this.userName = userName;
        this.title = title;
    }

    public String getGameId() {
        return gameId;
    }

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }
}
