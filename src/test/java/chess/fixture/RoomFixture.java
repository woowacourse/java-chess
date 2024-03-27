package chess.fixture;

import chess.domain.room.Room;

public class RoomFixture {
    private RoomFixture() {
    }

    public static Room createChessRoom() {
        return new Room(1L, 1L, "chess");
    }

    public static Room createBigChessRoom() {
        return new Room(2L, 2L, "BigChess");
    }

    public static Room createSmallChessRoom() {
        return new Room(3L, 2L, "SmallChess");
    }
}
