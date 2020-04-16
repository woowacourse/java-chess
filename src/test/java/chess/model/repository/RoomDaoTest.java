package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.repository.exception.DataAccessException;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RoomDaoTest {

    private static final RoomDao ROOM_DAO = RoomDao.getInstance();
    private final String ROOM_NAME = "테스트";
    private final String ROOM_PASSWORD = "1234";
    private int roomId;

    @BeforeEach
    void setUp() {
        roomId = ROOM_DAO.insert(ROOM_NAME, ROOM_PASSWORD);
    }

    @AfterEach
    void tearDown() {
        ROOM_DAO.delete(roomId);
    }

    @Test
    void getInstance() {
        RoomDao roomDaoFirst = RoomDao.getInstance();
        RoomDao roomDaoSecond = RoomDao.getInstance();
        assertThat(roomDaoFirst == roomDaoSecond).isTrue();
    }

    @ParameterizedTest
    @CsvSource(value = {"방이름, 비밀번호", "방이름,"})
    void insert(String name, String password) {
        int roomId1 = ROOM_DAO.insert(name, password);
        assertThat(ROOM_DAO.select(roomId1).get("NM")).isEqualTo(name);
        assertThat(ROOM_DAO.select(roomId1).get("PW")).isEqualTo(password);
        ROOM_DAO.delete(roomId1);
    }

    @DisplayName("Insert/Delete 잘되는지 확인 - 방이름이 Null일 수 없음(Not Null)")
    @ParameterizedTest
    @CsvSource(value = {", 비밀번호", " , "})
    void insertNameNull(String name, String password) {
        assertThatThrownBy(() -> ROOM_DAO.insert(name, password))
            .isInstanceOf(DataAccessException.class)
            .hasMessageContaining("null");
    }

    @Test
    void updateUsedN() {
        assertThat(ROOM_DAO.select(roomId).get("USED_YN")).isEqualTo("Y");
        ROOM_DAO.updateUsedN(roomId);
        assertThat(ROOM_DAO.select(roomId).get("USED_YN")).isEqualTo("N");
    }

    @Test
    void selectUsedOnly() {
        assertThat(ROOM_DAO.select(roomId).get("USED_YN")).isEqualTo("Y");
        assertThat(ROOM_DAO.selectUsedOnly().containsKey(roomId)).isTrue();
        ROOM_DAO.updateUsedN(roomId);
        assertThat(ROOM_DAO.selectUsedOnly().containsKey(roomId)).isFalse();
    }

    @Test
    void select() {
        Map<String, String> selectResult = ROOM_DAO.select(roomId);
        assertThat(selectResult.get("NM")).isEqualTo(ROOM_NAME);
        assertThat(selectResult.get("PW")).isEqualTo(ROOM_PASSWORD);
    }

    @Test
    void delete() {
        ROOM_DAO.delete(roomId);
        assertThat(ROOM_DAO.select(roomId)).isEmpty();
    }
}