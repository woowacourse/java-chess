package chess.domain;

public class Room {
    private int id;
    private String name;
    private String pw;
    private String gameId;

    public Room(int id, String name, String pw, String gameId) {
        this.id = id;
        this.name = name;
        this.pw = pw;
        this.gameId = gameId;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPw() {
        return pw;
    }

    public String getGameId() {
        return gameId;
    }
}
