package chess.database.room;

import com.google.gson.JsonObject;

import java.util.Objects;

public class Room {
    private final String roomId;
    private final String turn;
    private final JsonObject state;

    public Room(String roomId, String turn, JsonObject state) {
        this.roomId = roomId;
        this.turn = turn;
        this.state = state;
    }

    public String getRoomId() {
        return roomId;
    }

    public String getTurn() {
        return turn;
    }

    public JsonObject getState() {
        return state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(roomId, room.roomId) && Objects.equals(turn, room.turn) && Objects.equals(state, room.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, turn, state);
    }
}
