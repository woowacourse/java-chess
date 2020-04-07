package chess.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.domain.piece.Color;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class ChessGameDAOTest {

    private static ChessGameDAO CHESS_GAME_DAO;

    @BeforeAll
    public static void setup() {
        CHESS_GAME_DAO = new ChessGameDAO();
    }

    @Test
    public void crud() throws SQLException, IllegalAccessException {
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String gameId = "T01-" + formatDate + "-002";
        Color gameTurn = Color.BLACK;
        Map<Color, String> userNames = new HashMap<>();
        userNames.put(Color.BLACK, "USER_BLACK");
        userNames.put(Color.WHITE, "USER_WHITE");

        // no data exception
        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameNumberLatest(null))
            .isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameNumberLatest("r2"))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameNumberLatest("r234"))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameTurn(gameId))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getUserNames(gameId))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.isProceeding(gameId))
            .isInstanceOf(IllegalAccessException.class);

        assertThat(CHESS_GAME_DAO.getGameNumberLatest("T01")).isEqualTo(0);

        // insert
        CHESS_GAME_DAO.insert(gameId, gameTurn, userNames);
        assertThat(CHESS_GAME_DAO.getGameNumberLatest("T01")).isEqualTo(2);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId)).isEqualTo(gameTurn);
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId)).isTrue();

        // update - turn
        CHESS_GAME_DAO.updateTurn(gameId, gameTurn.nextTurnIfEmptyMySelf());
        assertThat(CHESS_GAME_DAO.getGameNumberLatest("T01")).isEqualTo(2);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId)).isEqualTo(gameTurn.nextTurnIfEmptyMySelf());
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId)).isTrue();

        // update - proceed
        CHESS_GAME_DAO.updateProceedN(gameId);
        assertThat(CHESS_GAME_DAO.getGameNumberLatest("T01")).isEqualTo(2);
        assertThat(CHESS_GAME_DAO.getGameTurn(gameId)).isEqualTo(gameTurn.nextTurnIfEmptyMySelf());
        assertThat(CHESS_GAME_DAO.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(CHESS_GAME_DAO.isProceeding(gameId)).isFalse();

        // delete
        CHESS_GAME_DAO.delete(gameId);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameNumberLatest(null))
            .isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameNumberLatest("r2"))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameNumberLatest("r234"))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getGameTurn(gameId))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.getUserNames(gameId))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> CHESS_GAME_DAO.isProceeding(gameId))
            .isInstanceOf(IllegalAccessException.class);

        assertThat(CHESS_GAME_DAO.getGameNumberLatest("T01")).isEqualTo(0);
    }
}