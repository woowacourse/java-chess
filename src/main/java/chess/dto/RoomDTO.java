package chess.dto;

import chess.domain.Room;

public class RoomDTO {
    private int id;
    private String name;
    private String pw;
    private String gameId;

    private RoomDTO(int id, String name, String pw, String gameId) {
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

    @Override
    public String toString() {
        return "RoomDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pw='" + pw + '\'' +
                ", gameId='" + gameId + '\'' +
                '}';
    }

    public static RoomDTO from(Room room) {
        return new RoomDTO(room.getId(), room.getName(), room.getPw(), room.getGameId());
    }
}
