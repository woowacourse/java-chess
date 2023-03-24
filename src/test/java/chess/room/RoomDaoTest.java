package chess.room;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class RoomDaoTest {
    @Test
    @DisplayName("방을 생성하는 테스트")
    void addRoomTest() throws SQLException {
        RoomDao.addRoom("test");
    }

    @Test
    @DisplayName("방을 찾는 테스트")
    void findByNameTest() throws SQLException {
        assertThat(RoomDao.FindByName("test").getRoomId()).isEqualTo(1);
    }
}
