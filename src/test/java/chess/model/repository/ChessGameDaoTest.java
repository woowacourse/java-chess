package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;

import chess.model.domain.piece.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private static final ChessGameDao CHESS_GAME_DAO = ChessGameDao.getInstance();
    private static final int ROOM_ID = 1;
    private static final Color GAME_TURN = Color.BLACK;
    private static final Map<Color, String> USER_NAMES;
    private static final String BLACK_NAME = "USER_BLACK";
    private static final String WHITE_NAME = "USER_WHITE";

    static {
        Map<Color, String> userNames = new HashMap<>();
        userNames.put(Color.BLACK, BLACK_NAME);
        userNames.put(Color.WHITE, WHITE_NAME);
        USER_NAMES = Collections.unmodifiableMap(new HashMap<>(userNames));
    }

    private int gameId;

    @BeforeEach
    public void setup() {
        gameId = CHESS_GAME_DAO.insert(ROOM_ID, GAME_TURN, USER_NAMES);
    }

    @AfterEach
    void tearDown() {
        CHESS_GAME_DAO.delete(gameId);
    }

    @Test
    void getInstance() {
        ChessGameDao chessGameDao1 = ChessGameDao.getInstance();
        ChessGameDao chessGameDao2 = ChessGameDao.getInstance();
        assertThat(chessGameDao1 == chessGameDao2).isTrue();
    }

    @Test
    void insert() {
        CHESS_GAME_DAO.delete(gameId);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).isPresent()).isFalse();
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEmpty();
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).isPresent()).isFalse();

        gameId = CHESS_GAME_DAO.insert(ROOM_ID, GAME_TURN, USER_NAMES);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).get()).isEqualTo(GAME_TURN);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).get()).isTrue();
    }

    @Test
    void updateProceedN() {
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(GAME_TURN);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).orElseThrow(IllegalArgumentException::new))
            .isTrue();

        CHESS_GAME_DAO.updateProceedN(gameId);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(GAME_TURN);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).orElseThrow(IllegalArgumentException::new))
            .isFalse();
    }

    @Test
    void updateTurn() {
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(GAME_TURN);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).orElseThrow(IllegalArgumentException::new))
            .isTrue();

        CHESS_GAME_DAO.updateTurn(gameId, GAME_TURN.nextTurnIfEmptyMySelf());
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(GAME_TURN.nextTurnIfEmptyMySelf());
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).orElseThrow(IllegalArgumentException::new))
            .isTrue();
    }

    @Test
    void delete() {
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).get()).isEqualTo(GAME_TURN);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).get()).isTrue();

        CHESS_GAME_DAO.delete(gameId);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).isPresent()).isFalse();
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEmpty();
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).isPresent()).isFalse();
    }

    @Test
    void getGameNumberLatest() {
        assertThat(
            CHESS_GAME_DAO.getGameNumberLatest(ROOM_ID).orElseThrow(IllegalArgumentException::new))
            .isGreaterThanOrEqualTo(0);
    }

    @Test
    void getGameTurn() {
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(GAME_TURN);
    }

    @Test
    void getUserNames() {
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
    }

    @Test
    void isProceeding() {
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).orElseThrow(IllegalArgumentException::new))
            .isTrue();
    }

    @Test
    void updateProceedNByRoomId() {
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(GAME_TURN);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).orElseThrow(IllegalArgumentException::new))
            .isTrue();

        CHESS_GAME_DAO.updateProceedNByRoomId(ROOM_ID);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(GAME_TURN);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(USER_NAMES);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId).orElseThrow(IllegalArgumentException::new))
            .isFalse();
    }

    @Test
    void getUsers() {
        assertThat(CHESS_GAME_DAO.getUsers().contains(BLACK_NAME)).isTrue();
        assertThat(CHESS_GAME_DAO.getUsers().contains(WHITE_NAME)).isTrue();
    }

    @Test
    void getRoomId() {
        assertThat(CHESS_GAME_DAO.getRoomId(gameId).orElseThrow(IllegalArgumentException::new))
            .isEqualTo(ROOM_ID);
    }
}