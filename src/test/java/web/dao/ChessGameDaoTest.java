package web.dao;

import static org.assertj.core.api.Assertions.assertThat;

import chess.Score;
import chess.piece.Color;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import web.dto.ChessGameDto;
import web.dto.GameStatus;

public class ChessGameDaoTest {

    private ChessGameDao dao;

    @BeforeEach
    void setUp() {
        dao = new ChessGameDao(new JdbcTemplate());
        dao.deleteAll();
        dao.saveChessGame("Chess Game", GameStatus.RUNNING, Color.WHITE, new Score(new BigDecimal("10.0")),
                new Score(new BigDecimal("15.0")));
    }

    @AfterEach
    void tearDown() {
        dao.deleteAll();
    }

    @Test
    void findGameStatus() {
        assertThat(dao.findGameStatus(getChessGameId())).isEqualTo(GameStatus.RUNNING);
    }

    private int getChessGameId() {
        List<ChessGameDto> chessGameDtos = dao.findAll();
        return chessGameDtos.get(0).getId();
    }

    @Test
    void updateGameStatus() {
        dao.updateGameStatus(getChessGameId(), GameStatus.RUNNING);

        assertThat(dao.findGameStatus(getChessGameId())).isEqualTo(GameStatus.RUNNING);
    }

    @Test
    void updateWinner() {
        dao.updateWinner(getChessGameId(), Color.BLACK);

        assertThat(dao.findWinner(getChessGameId())).isEqualTo(Color.BLACK);
    }

    @Test
    void findCurrentColor() {
        assertThat(dao.findCurrentColor(getChessGameId())).isEqualTo(Color.WHITE);
    }

    @Test
    void updateCurrentColor() {
        dao.updateCurrentColor(getChessGameId(), Color.BLACK);

        assertThat(dao.findCurrentColor(getChessGameId())).isEqualTo(Color.BLACK);
    }

    @Test
    void findScoreByColor() {
        assertThat(dao.findScoreByColor(getChessGameId(), Color.BLACK)).isEqualTo(new Score(new BigDecimal("10.0")));
        assertThat(dao.findScoreByColor(getChessGameId(), Color.WHITE)).isEqualTo(new Score(new BigDecimal("15.0")));
    }

    @Test
    void updateScoreByColor() {
        dao.updateScoreByColor(getChessGameId(), new Score(new BigDecimal("20.6")), Color.WHITE);
        assertThat(dao.findScoreByColor(getChessGameId(), Color.WHITE)).isEqualTo(new Score(new BigDecimal("20.6")));
    }
}
