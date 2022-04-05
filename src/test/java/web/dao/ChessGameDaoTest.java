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

    private ChessGameDao chessGameDao;
    private PieceDao pieceDao;

    @BeforeEach
    void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();

        pieceDao = new PieceDao(jdbcTemplate);
        pieceDao.deleteAll();

        chessGameDao = new ChessGameDao(jdbcTemplate);
        chessGameDao.deleteAll();
        chessGameDao.saveChessGame("Chess Game", GameStatus.RUNNING, Color.WHITE, new Score(new BigDecimal("10.0")),
                new Score(new BigDecimal("15.0")));
    }

    @AfterEach
    void tearDown() {
        pieceDao.deleteAll();
        chessGameDao.deleteAll();
    }

    @Test
    void findGameStatus() {
        assertThat(chessGameDao.findGameStatus(getChessGameId())).isEqualTo(GameStatus.RUNNING);
    }

    private int getChessGameId() {
        List<ChessGameDto> chessGameDtos = chessGameDao.findAll();
        return chessGameDtos.get(0).getId();
    }

    @Test
    void updateGameStatus() {
        chessGameDao.updateGameStatus(getChessGameId(), GameStatus.RUNNING);

        assertThat(chessGameDao.findGameStatus(getChessGameId())).isEqualTo(GameStatus.RUNNING);
    }

    @Test
    void updateWinner() {
        chessGameDao.updateWinner(getChessGameId(), Color.BLACK);

        assertThat(chessGameDao.findWinner(getChessGameId())).isEqualTo(Color.BLACK);
    }

    @Test
    void findCurrentColor() {
        assertThat(chessGameDao.findCurrentColor(getChessGameId())).isEqualTo(Color.WHITE);
    }

    @Test
    void updateCurrentColor() {
        chessGameDao.updateCurrentColor(getChessGameId(), Color.BLACK);

        assertThat(chessGameDao.findCurrentColor(getChessGameId())).isEqualTo(Color.BLACK);
    }

    @Test
    void findScoreByColor() {
        assertThat(chessGameDao.findScoreByColor(getChessGameId(), Color.BLACK)).isEqualTo(new Score(new BigDecimal("10.0")));
        assertThat(chessGameDao.findScoreByColor(getChessGameId(), Color.WHITE)).isEqualTo(new Score(new BigDecimal("15.0")));
    }

    @Test
    void updateScoreByColor() {
        chessGameDao.updateScoreByColor(getChessGameId(), new Score(new BigDecimal("20.6")), Color.WHITE);
        assertThat(chessGameDao.findScoreByColor(getChessGameId(), Color.WHITE)).isEqualTo(new Score(new BigDecimal("20.6")));
    }
}
