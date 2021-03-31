package chess.dto;

import chess.domain.room.Room;

public class RoomDTO {

    private final int id;
    private final String name;

    public RoomDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static RoomDTO from(Room room) {
        return new RoomDTO(room.getId(), room.getName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
