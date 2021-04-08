package web.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomDaoTest {
    private RoomDao roomDao;

    @BeforeEach
    public void setup() {
        roomDao = new RoomDao();
    }

    @Test
    @DisplayName("roomId 반환 확인")
    public void addRoom() throws Exception {
        assertNotNull(roomDao.newRoomId());
    }
}
