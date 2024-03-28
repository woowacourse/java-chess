package chess.fixture;

import chess.domain.room.Room;

public class RoomFixture {
    private RoomFixture() {
    }

    public static Room createChessRoom() {
        return Room.of(1L, 1L, "chess");
    }

    public static Room createBigChessRoom() {
        return Room.of(2L, 2L, "BigChess");
    }

    public static Room createSmallChessRoom() {
        return Room.of(3L, 2L, "SmallChess");
    }
}
