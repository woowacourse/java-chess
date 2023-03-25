package chess.room;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class RoomDaoTest {
    private static final String name = "test";

    //@BeforeEach
    void delete() throws SQLException {
        Room room = RoomDao.FindByName("test");
        room.deleteRoom();
    }

    @Test
    @DisplayName("방을 생성하는 테스트")
    void addRoomTest() throws SQLException {
        RoomDao.addRoom(name);
    }

    @Test
    void deleteRoomTest() throws SQLException {
        RoomDao.deleteRoom(11);
    }
}
