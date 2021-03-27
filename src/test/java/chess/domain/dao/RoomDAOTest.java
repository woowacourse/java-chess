package chess.domain.dao;

import chess.dao.RoomDAO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomDAOTest {
    private RoomDAO roomDao = new RoomDAO();

    @Test
    @DisplayName("방을 정상적으로 조회 및 추가를 하는 지 테스트")
    public void createAndFindRoom() throws SQLException {
        String roomName = "abcd";
        long roomId = roomDao.createRoom(roomName);
        Object foundRoomId = roomDao.findRoomIdByName(roomName);
        assertThat(roomId).isEqualTo(foundRoomId);
    }
}
