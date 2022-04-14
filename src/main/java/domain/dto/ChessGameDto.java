package domain.dto;

public class ChessGameDto {

    private int id;
    private String name;
    private String player;

    public ChessGameDto(String name, String player) {
        this.name = name;
        this.player = player;
    }

    public ChessGameDto(int id, String name, String player) {
        this.id = id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
