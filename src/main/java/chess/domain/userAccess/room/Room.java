package chess.domain.userAccess.room;

import java.util.Objects;

public class Room {

    private final int roomId;
    private final String userId;
    private final String commands;

    public Room(int roomId, String userId, String commands) {
        this.roomId = roomId;
        this.userId = userId;
        this.commands = commands;
    }

    public int roomId() {
        return roomId;
    }

    public String userId() {
        return userId;
    }

    public String commands() {
        return commands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId && Objects.equals(userId, room.userId) && Objects.equals(commands, room.commands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, userId, commands);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", userId='" + userId + '\'' +
                ", commands='" + commands + '\'' +
                '}';
    }
}
