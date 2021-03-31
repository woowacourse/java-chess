package chess.domain.room;

import com.google.gson.JsonObject;

import java.util.Objects;

public class Room {
    private String roomId;
    private JsonObject state;

    public Room(String roomId, JsonObject state) {
        this.roomId = roomId;
        this.state = state;
    }

    public String getRoomId() {
        return roomId;
    }

    public JsonObject getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId) && Objects.equals(state, room.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, state);
    }
}
