package web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.piece.Color;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.dto.GameStatus;

public class ChessGameDaoTest {

    private ChessGameDao dao;

    @BeforeEach
    void setUp() {
        dao = new ChessGameDao(new JdbcTemplate());
        dao.deleteAll();
        dao.saveGameStatus(GameStatus.FINISHED, Color.WHITE);
    }

    @AfterEach
    void tearDown() {
        dao.deleteAll();
    }

    @Test
    void findGameStatus() {
        assertThat(dao.findGameStatus()).isEqualTo(GameStatus.FINISHED);
    }

    @Test
    void updateGameStatus() {
        dao.updateGameStatus(GameStatus.RUNNING);
        assertThat(dao.findGameStatus()).isEqualTo(GameStatus.RUNNING);
    }

    @Test
    void updateWinner() {
        dao.updateWinner(Color.BLACK);
        assertThat(dao.findWinner()).isEqualTo(Color.BLACK);
    }

    @Test
    void findCurrentColor() {
        assertThat(dao.findCurrentColor()).isEqualTo(Color.WHITE);
    }

    @Test
    void updateCurrentColor() {
        dao.updateCurrentColor(Color.BLACK);
        assertThat(dao.findCurrentColor()).isEqualTo(Color.BLACK);
    }
}
