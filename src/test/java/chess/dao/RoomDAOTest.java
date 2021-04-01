package chess.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class RoomDAOTest {
    private final RoomDAO roomDAO = new RoomDAO();

    @Test
    @DisplayName("방 생성 테스트")
    void create() throws SQLException {
        roomDAO.createRoom("테스트");
    }

    @Test
    @DisplayName("모든 방 불러오기")
    void allRooms() throws SQLException {
        roomDAO.allRooms();
    }

    @Test
    @DisplayName("방 status 변경")
    void changeStatus() throws SQLException {
        roomDAO.changeStatusEndByRoomId("5");
    }
}
