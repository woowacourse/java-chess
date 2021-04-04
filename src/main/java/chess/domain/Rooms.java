package chess.domain;

import java.util.HashMap;
import java.util.Map;

public final class Rooms {
    private final Map<String, ChessGame> rooms;

    public Rooms() {
        this.rooms = new HashMap<>();
    }

    public void addRoom(final String roomId, final ChessGame chessGame) {
        rooms.put(roomId, chessGame);
    }

    public ChessGame loadGameByRoomId(final String roomId) {
        return rooms.get(roomId);
    }
}
