package web.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

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
    void findById() {
        ChessGameDto chessGameDto = chessGameDao.findById(getChessGameId());

        assertAll(() -> {
            assertThat(chessGameDto.getStatus()).isEqualTo(GameStatus.RUNNING);
            assertThat(chessGameDto.getName()).isEqualTo("Chess Game");
            assertThat(chessGameDto.getCurrentColor()).isEqualTo(Color.WHITE);
            assertThat(chessGameDto.getBlackScore()).isEqualTo(new Score(new BigDecimal("10.0")));
            assertThat(chessGameDto.getWhiteScore()).isEqualTo(new Score(new BigDecimal("15.0")));
        });
    }

    @Test
    void updateChessGame() {
        ChessGameDto chessGameDto = chessGameDao.findById(getChessGameId());
        ChessGameDto newChessGameDto = new ChessGameDto(
                chessGameDto.getId(), chessGameDto.getName(), GameStatus.FINISHED,
                new Score(new BigDecimal("20.0")), new Score(new BigDecimal("12.5")), Color.BLACK);
        chessGameDao.updateChessGame(newChessGameDto);

        ChessGameDto expect = chessGameDao.findById(getChessGameId());

        assertAll(() -> {
            assertThat(expect.getStatus()).isEqualTo(newChessGameDto.getStatus());
            assertThat(expect.getCurrentColor()).isEqualTo(newChessGameDto.getCurrentColor());
            assertThat(expect.getBlackScore()).isEqualTo(newChessGameDto.getBlackScore());
            assertThat(expect.getWhiteScore()).isEqualTo(newChessGameDto.getWhiteScore());
        });
    }

    private int getChessGameId() {
        List<ChessGameDto> chessGameDtos = chessGameDao.findAll();
        return chessGameDtos.get(0).getId();
    }
}
