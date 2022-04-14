package domain.dto;

public class PieceDto {

    private int gameId;
    private String type;
    private String player;
    private String position;

    public PieceDto(int gameId, String position, String type, String player) {
        this.gameId = gameId;
        this.type = type;
        this.player = player;
        this.position = position;
    }

    public int getGameId() {
        return gameId;
    }

    public String getType() {
        return type;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
