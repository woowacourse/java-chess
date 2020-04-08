package chess.model.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import chess.model.domain.piece.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ChessGameDaoTest {

    private ChessGameDao chessGameDao;

    @BeforeEach
    public void setup() {
        chessGameDao = ChessGameDao.getInstance();
    }

    @DisplayName("Connection Test")
    @Test
    public void connection() {
        Connection con = chessGameDao.getConnection();
        assertThat(Objects.nonNull(con)).isTrue();
    }

    @Test
    void closeConnection() {
    }


    @Test
    public void crud() throws SQLException {
        String formatDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String gameId = "T01-" + formatDate + "-002";
        Color gameTurn = Color.BLACK;
        Map<Color, String> userNames = new HashMap<>();
        userNames.put(Color.BLACK, "USER_BLACK");
        userNames.put(Color.WHITE, "USER_WHITE");

        // no data exception
        assertThatThrownBy(() -> chessGameDao.getGameNumberLatest(null))
            .isInstanceOf(NullPointerException.class);
        assertThatThrownBy(() -> chessGameDao.getGameNumberLatest("r2"))
            .isInstanceOf(IllegalAccessException.class);

        assertThat(chessGameDao.getGameTurn(gameId).isPresent()).isFalse();
        assertThat(chessGameDao.getUserNames(gameId)).isEmpty();
        assertThat(chessGameDao.isProceeding(gameId).isPresent()).isFalse();

        // insert
        chessGameDao.insert(gameId, gameTurn, userNames);
        assertThat(chessGameDao.getGameTurn(gameId).get()).isEqualTo(gameTurn);
        assertThat(chessGameDao.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(chessGameDao.isProceeding(gameId).get()).isTrue();

        // update - turn
        chessGameDao.updateTurn(gameId, gameTurn.nextTurnIfEmptyMySelf());
        assertThat(chessGameDao.getGameTurn(gameId).get())
            .isEqualTo(gameTurn.nextTurnIfEmptyMySelf());
        assertThat(chessGameDao.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(chessGameDao.isProceeding(gameId).get()).isTrue();

        // update - proceed
        chessGameDao.updateProceedN(gameId);
        assertThat(chessGameDao.getGameTurn(gameId).get())
            .isEqualTo(gameTurn.nextTurnIfEmptyMySelf());
        assertThat(chessGameDao.getUserNames(gameId)).isEqualTo(userNames);
        assertThat(chessGameDao.isProceeding(gameId).get()).isFalse();

        // delete
        chessGameDao.delete(gameId);

        assertThatThrownBy(() -> chessGameDao.getGameNumberLatest(null))
            .isInstanceOf(NullPointerException.class);

        assertThatThrownBy(() -> chessGameDao.getGameNumberLatest("r2"))
            .isInstanceOf(IllegalAccessException.class);

        assertThatThrownBy(() -> chessGameDao.getGameNumberLatest("r234"))
            .isInstanceOf(IllegalAccessException.class);

        assertThat(chessGameDao.getGameTurn(gameId).isPresent()).isFalse();
        assertThat(chessGameDao.getUserNames(gameId)).isEmpty();
        assertThat(chessGameDao.isProceeding(gameId).isPresent()).isFalse();

    }

}