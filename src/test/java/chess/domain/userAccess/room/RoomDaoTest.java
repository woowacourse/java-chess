package chess.domain.userAccess.room;

import chess.domain.userAccess.user.User;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static chess.domain.userAccess.DbConnection.getConnection;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestMethodOrder(MethodOrderer.DisplayName.class)
class RoomDaoTest {

    RoomDao roomDao;

    @BeforeEach
    void setUp() {
        roomDao = new RoomDao();
    }

    @Test
    @DisplayName("1. 커넥션 테스트")
    public void connection() {
        try (final Connection connection = getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
        }
    }

    @Test
    @DisplayName("2. 게임 방 만들기 테스트")
    void createRoom() {
        User user = new User("ehdgur");
        roomDao.createRoom(user);
        assertDoesNotThrow(() -> roomDao.findRoomByUserIdAndCommands(user.userId(), ""));
    }

    @Test
    @DisplayName("3. 게임 방 커맨드 변경 테스트")
    void updateRoomByRoomId() {
        Room room = roomDao.findRoomByUserIdAndCommands("ehdgur", "");
        roomDao.updateRoomByRoomId(room.roomId(), "b2b3c3c2");
        assertDoesNotThrow(() -> roomDao.findRoomByUserIdAndCommands("ehdgur", "b2b3c3c2"));
    }

    @Test
    @DisplayName("4. 게임 방 삭제 테스트")
    void deleteRoomByRoomId() {
        Room room = roomDao.findRoomByUserIdAndCommands("ehdgur", "b2b3c3c2");
        assertDoesNotThrow(() -> roomDao.deleteRoomByRoomId(room.roomId()));
    }
}
