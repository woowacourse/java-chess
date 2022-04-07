package domain.dto;

public class PieceDto {

    private String gameName;
    private String type;
    private String player;
    private String position;

    public PieceDto(String gameName, String position, String type, String player) {
        this.gameName = gameName;
        this.type = type;
        this.player = player;
        this.position = position;
    }

    public String getGameName() {
        return gameName;
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
