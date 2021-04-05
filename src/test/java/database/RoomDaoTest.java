package database;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomDaoTest {
    private RoomDao roomDao;

    @BeforeEach
    public void setup() {
        roomDao = new RoomDao();
    }

    @Test
    @DisplayName("db 연결 확인")
    public void connection() throws SQLException {
        try (Connection con = roomDao.getConnection()) {
            assertNotNull(con);
        }
    }

    @Test
    @DisplayName("roomId 반환 확인")
    public void addRoom() throws Exception {
        assertNotNull(roomDao.newRoomId());
    }
}
