package chess.dao;

import chess.dto.GameRoomDto;
import org.junit.jupiter.api.*;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class GameRoomDaoTest {
    private final String ROOM_NAME = "test";
    private final GameRoomDao gameRoomDao = new GameRoomDao();

    @BeforeEach
    void setup() {
        gameRoomDao.deleteByRoomName(ROOM_NAME);
    }

    @AfterAll()
    void deleteAll() {
        gameRoomDao.deleteByRoomName(ROOM_NAME);
    }

    @DisplayName("DB에 연결할 수 있다.")
    @Test
    void connectionTest() {
        try (Connection connection = gameRoomDao.getConnection()) {
            assertThat(connection).isNotNull();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DisplayName("DB에 게임 방을 저장할 수 있다.")
    @Test
    void saveBoardTest() {
        GameRoomDto gameRoomDto = new GameRoomDto(ROOM_NAME, true);

        assertDoesNotThrow(() -> gameRoomDao.save(gameRoomDto));
    }

    @DisplayName("DB에서 게임방을 불러올 수 있다.")
    @Test
    void loadBoardTest() {
        assertDoesNotThrow(() -> gameRoomDao.findByRoomName(ROOM_NAME));
    }

    @DisplayName("DB에서 게임방을 업데이트할 수 있다.")
    @Test
    void updateBoardTest() {
        GameRoomDto gameRoomDto = new GameRoomDto(ROOM_NAME, true);
        gameRoomDao.save(gameRoomDto);

        assertDoesNotThrow(() -> gameRoomDao.update(new GameRoomDto(ROOM_NAME, false)));
    }
}
