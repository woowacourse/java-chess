package chess.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import chess.dao.BoardDao;
import chess.dao.FakeBoardDao;
import chess.dao.FakeGameDao;
import chess.dao.GameDao;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.Arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameServiceTest {

    private static final String TEST_ROOM_NAME = "TESTING";
    private static final GameService service = new GameService(new FakeGameDao(), new FakeBoardDao());

    @Test
    @Order(1)
    @DisplayName("새로운 방을 만든다.")
    public void createNewGame() {
        assertThatCode(() -> service.createNewGame(TEST_ROOM_NAME))
            .doesNotThrowAnyException();
    }

    @Test
    @Order(2)
    @DisplayName("게임 상태를 얻는다.")
    public void readGameState() {
        // given
        String roomName = TEST_ROOM_NAME;
        // when
        GameState gameState = service.readGameState(roomName);
        // then
        assertThat(gameState).isInstanceOf(Ready.class);
    }

    @Test
    @Order(3)
    @DisplayName("게임을 시작한다.")
    public void startGame() {
        // given & when
        String roomName = TEST_ROOM_NAME;
        // then
        assertThatCode(() -> service.startGame(roomName))
            .doesNotThrowAnyException();
    }

    @Test
    @Order(4)
    @DisplayName("말을 움직인다.")
    public void moveBoard() {
        // given
        String roomName = TEST_ROOM_NAME;
        Arguments arguments = Arguments.ofArray(new String[] {"a2", "a4"}, 0);
        // when
        GameState moved = service.moveBoard(roomName, arguments);
        // then
        assertThat(moved).isNotNull();
    }

    @Test
    @Order(5)
    @DisplayName("게임을 종료한다.")
    public void finishGame() {
        // given & when
        String roomName = TEST_ROOM_NAME;

        // then
        assertThatCode(() -> service.finishGame(roomName))
            .doesNotThrowAnyException();
    }
}