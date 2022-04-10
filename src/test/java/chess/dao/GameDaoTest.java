package chess.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import chess.domain.Color;
import chess.domain.game.GameState;
import chess.domain.game.Ready;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameDaoTest {

    private static final String TEST_ROOM_NAME = "TESTING";

    private final JdbcGameDao dao = new JdbcGameDao();

    @Test
    @Order(1)
    @DisplayName("게임을 생성한다.")
    public void createGame() {
        assertThatCode(() -> dao.saveGame("READY", Color.WHITE.name(), TEST_ROOM_NAME))
            .doesNotThrowAnyException();
    }

    @Test
    @Order(2)
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
    @Order(3)
    @DisplayName("방 이름으로 게임 상태와 턴 색깔을 수정한다.")
    public void update() {
        // given
        GameState state = new Ready();
        GameState started = state.start();
        // when
        dao.updateState(started.getState(), started.getColor(), TEST_ROOM_NAME);
        List<String> stateAndColor = dao.readStateAndColor(TEST_ROOM_NAME);

        String stateString = stateAndColor.get(0);
        String colorString = stateAndColor.get(1);
        // then
        assertThat(stateString).isEqualTo("RUNNING");
        assertThat(colorString).isEqualTo("WHITE");
    }

    @AfterAll
    static void afterAll() {
        JdbcConnector.query("DELETE FROM game WHERE room_name = ?")
            .parameters(TEST_ROOM_NAME)
            .executeUpdate();
    }
}