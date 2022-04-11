package chess.database.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import chess.database.dto.GameStateDto;
import chess.domain.game.GameState;
import chess.domain.game.Ready;

class GameDaoTest {

    private static final String TEST_ROOM_NAME = "TESTING";
    private static final String TEST_CREATION_ROOM_NAME = "TESTING2";

    private final JdbcGameDao dao = new JdbcGameDao();

    @BeforeEach
    void setUp() {
        GameState state = new Ready();
        dao.saveGame(GameStateDto.of(state), TEST_ROOM_NAME);
    }

    @Test
    @DisplayName("게임을 생성한다.")
    public void createGame() {
        GameState state = new Ready();
        assertThatCode(() -> dao.saveGame(GameStateDto.of(state), TEST_CREATION_ROOM_NAME))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("방 이름으로 게임 상태와 턴 색깔을 조회한다.")
    public void insert() {
        // given
        List<String> stateAndColor = dao.readStateAndColor(TEST_ROOM_NAME);
        // when

        String stateString = stateAndColor.get(0);
        String colorString = stateAndColor.get(1);
        // then
        Assertions.assertAll(
            () -> assertThat(stateString).isEqualTo("READY"),
            () -> assertThat(colorString).isEqualTo("WHITE")
        );
    }

    @Test
    @DisplayName("방 이름으로 게임 상태와 턴 색깔을 수정한다.")
    public void update() {
        // given
        GameState state = new Ready();
        GameState started = state.start();
        // when
        dao.updateState(GameStateDto.of(started), TEST_ROOM_NAME);
        List<String> stateAndColor = dao.readStateAndColor(TEST_ROOM_NAME);

        String stateString = stateAndColor.get(0);
        String colorString = stateAndColor.get(1);
        // then
        assertThat(stateString).isEqualTo("RUNNING");
        assertThat(colorString).isEqualTo("WHITE");
    }

    @AfterEach
    void afterAll() {
        JdbcConnector.query("DELETE FROM game WHERE room_name = ?")
            .parameters(TEST_ROOM_NAME)
            .executeUpdate();
        JdbcConnector.query("DELETE FROM game WHERE room_name = ?")
            .parameters(TEST_CREATION_ROOM_NAME)
            .executeUpdate();
    }
}