package domain.dto;

public class ChessGameDto {

    private String name;
    private String player;

    public ChessGameDto(String name, String player) {
        this.name = name;
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }
}
