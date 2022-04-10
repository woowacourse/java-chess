package chess.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import chess.database.dao.FakeBoardDao;
import chess.database.dao.FakeGameDao;
import chess.database.dto.GameStateDto;
import chess.domain.game.GameState;
import chess.domain.game.Ready;
import chess.dto.Arguments;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class GameServiceTest {

    private static final String TEST_ROOM_NAME = "TESTING";
    private static final String TEST_CREATION_ROOM_NAME = "TESTING2";
    private static final FakeGameDao gameDao = new FakeGameDao();
    private static final FakeBoardDao boardDao = new FakeBoardDao();
    private static final GameService service = new GameService(gameDao, boardDao);

    @BeforeEach
    void setUp() {
        service.createNewGame(TEST_ROOM_NAME);
    }

    @Test
    @DisplayName("새로운 방을 만든다.")
    public void createNewGame() {
        assertThatCode(() -> service.createNewGame(TEST_CREATION_ROOM_NAME))
            .doesNotThrowAnyException();
    }

    @Test
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
    @DisplayName("게임을 시작한다.")
    public void startGame() {
        // given & when
        String roomName = TEST_ROOM_NAME;
        // then
        assertThatCode(() -> service.startGame(roomName))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("말을 움직인다.")
    public void moveBoard() {
        // given
        String roomName = TEST_ROOM_NAME;
        Arguments arguments = Arguments.ofArray(new String[] {"a2", "a4"}, 0);

        GameState gameState = service.readGameState(roomName);
        GameState started = gameState.start();
        gameDao.saveGame(GameStateDto.of(started), roomName);

        // when
        GameState moved = service.moveBoard(roomName, arguments);
        // then
        assertThat(moved).isNotNull();
    }

    @Test
    @DisplayName("게임을 종료한다.")
    public void finishGame() {
        // given & when
        String roomName = TEST_ROOM_NAME;

        // then
        assertThatCode(() -> service.finishGame(roomName))
            .doesNotThrowAnyException();
    }

    @AfterEach
    void setDown() {
        gameDao.removeGame(TEST_ROOM_NAME);
        boardDao.removeBoard(TEST_ROOM_NAME);
        gameDao.removeGame(TEST_CREATION_ROOM_NAME);
        boardDao.removeBoard(TEST_CREATION_ROOM_NAME);
    }
}